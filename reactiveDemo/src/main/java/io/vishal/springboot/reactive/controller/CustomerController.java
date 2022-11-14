package io.vishal.springboot.reactive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.vishal.springboot.reactive.dto.Customer;
import io.vishal.springboot.reactive.service.CustomerService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("/normal")
	private List<Customer> getAllCustomers(){
		return customerService.loadAllCustomers();
	}
	
	//We need to send data as event stream instead of direct object IN REACTIVE PROGRAMMING
//	@GetMapping("/reactive")
//	private Flux<Customer> getAllCustomersStream(){ 
//		return customerService.loadAllCustomersStream();
//	}
	
	/*  
	 * * browser is subscriber and db is publisher
	 */
	@GetMapping(value = "/reactive",produces = MediaType.TEXT_EVENT_STREAM_VALUE)   //run it from browser to see it coming one by one per second
	private Flux<Customer> getAllCustomersStream(){ 
		return customerService.loadAllCustomersStream();
	}
}
