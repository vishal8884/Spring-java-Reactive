package io.vishal.springboot.reactive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.vishal.springboot.reactive.dao.CustomerDao;
import io.vishal.springboot.reactive.dto.Customer;
import reactor.core.publisher.Flux;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao dao;

	public List<Customer> loadAllCustomers() {
		long start = System.currentTimeMillis();
		List<Customer> customers = dao.getCustomers();
		long end = System.currentTimeMillis();

		long totalTime = end - start;
		System.out.println("Total execution time normal:: " + totalTime+"       start :: "+start+"     end :: "+end);
		return customers;
	}
	
	
	public Flux<Customer> loadAllCustomersStream() {
		long start = System.currentTimeMillis();
		Flux<Customer> customers = dao.getCustomersStream();
		long end = System.currentTimeMillis();

		long totalTime = end - start;
		System.out.println("Total execution time in reactive programming:: " + totalTime+"      start :: "+start+"       end :: "+end);
		return customers;
	}
}
