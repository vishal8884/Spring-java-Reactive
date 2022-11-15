package io.vishal.springboot.reactive.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.vishal.springboot.reactive.dao.CustomerDao;
import io.vishal.springboot.reactive.dto.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

	@Autowired
	private CustomerDao customerDao;

	public Mono<ServerResponse> loadCustomers(ServerRequest request) {
		Flux<Customer> customerList = customerDao.getCustomersStreamWithoutDelay();
		return ServerResponse.ok()
				.body(customerList, Customer.class);
	}

	public Mono<ServerResponse> findCustomer(ServerRequest request) {
		int customerId = Integer.valueOf(request.pathVariable("input")); // in the serverRequest obj we can set header,body,queryParam,pathVariable etc

		// customerDao.getCustomersStreamWithoutDelay().filter(c -> c.getId()==customerId).take(1).single();
		Mono<Customer> customerMono = customerDao.getCustomersStreamWithoutDelay().filter(c -> c.getId() == customerId)
				.next();
		return ServerResponse.ok().body(customerMono, Customer.class);
	}
	
	
	public Mono<ServerResponse> saveCustomer(ServerRequest request){
		Mono<Customer> customerMono = request.bodyToMono(Customer.class);
		Mono<String> saveResponse = customerMono.map(customer -> customer.getId()+":"+customer.getName());
		
		return ServerResponse.ok().body(saveResponse,String.class);
	}
}
