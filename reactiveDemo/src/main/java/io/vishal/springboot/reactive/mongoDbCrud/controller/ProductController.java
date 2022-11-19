package io.vishal.springboot.reactive.mongoDbCrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.vishal.springboot.reactive.mongoDbCrud.dto.ProductDto;
import io.vishal.springboot.reactive.mongoDbCrud.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {
	
	@Autowired
	private ProductService service;

	@GetMapping
	public Flux<ProductDto> getProducts(){
		log.info("getProducts() called");
		return service.getProducts();
	}
	
	@GetMapping("/{id}")
	public Mono<ProductDto> getProduct(@PathVariable String id){
		log.info("getProduct() called");
		return service.getProduct(id);
	}
	
	@GetMapping("/product-range")
	public Flux<ProductDto> getProductBetweenRange(@RequestParam("min") double min,@RequestParam("max") double max){
		log.info("productRange called");
		return service.getProductInRange(min, max);
	}
	
	@PostMapping("/save")
	public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDtoMono){
		log.info("save product called");
		return service.saveProduct(productDtoMono);
	}
	
	@PutMapping("/update/{id}")
	public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono, @PathVariable String id){
		log.info("updated product called");
		return service.updateProduct(productDtoMono,id);
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<Void> deleteProduct(@PathVariable String id){
		log.info("delete product called");
		return service.deleteProduct(id);
	}
	
	@DeleteMapping("/delete/all")
	public Flux<Void> deleteAllProducts(){
		log.info("deleting all products");
		return service.deleteAllProduct();
	}
	
}
