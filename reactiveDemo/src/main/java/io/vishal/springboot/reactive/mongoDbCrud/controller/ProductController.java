package io.vishal.springboot.reactive.mongoDbCrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.vishal.springboot.reactive.mongoDbCrud.dto.ProductDto;
import io.vishal.springboot.reactive.mongoDbCrud.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService service;

	@GetMapping
	public Flux<ProductDto> getProducts(){
		return service.getProducts();
	}
	
	@GetMapping("/{id}")
	public Mono<ProductDto> getProduct(@PathVariable String id){
		return service.getProduct(id);
	}
	
	@GetMapping("/{id}")
	public Flux<ProductDto> getProductBetweenRange(@RequestParam double min,@RequestParam double max){
		return service.getProductInRange(min, max);
	}
}
