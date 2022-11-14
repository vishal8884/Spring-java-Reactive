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
	
	
	
	public Mono<ServerResponse> loadCustomers(ServerRequest request){
		Flux<Customer> customerList = customerDao.getCustomersStreamWithoutDelay();
		return ServerResponse.ok().body(customerList,Customer.class);
	}
}
