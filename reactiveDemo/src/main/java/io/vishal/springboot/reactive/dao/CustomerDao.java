package io.vishal.springboot.reactive.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import io.vishal.springboot.reactive.dto.Customer;
import reactor.core.publisher.Flux;

@Component
public class CustomerDao {

	public List<Customer> getCustomers(){
		return IntStream.rangeClosed(1, 10)
				 .peek(CustomerDao::sleepExecution)
				 .peek(i -> System.out.println("processing count normal:: "+i))
		         .mapToObj(i -> new Customer(i,"Customer"+i))
		         .collect(Collectors.toList());
	}
	
	public Flux<Customer> getCustomersStream(){
		return Flux.range(1, 10)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i -> System.out.println("processing count in stream:: "+i))
				.map(i -> new Customer(i,"Customer"+i));
				
	}
	
	
	private static void sleepExecution(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
