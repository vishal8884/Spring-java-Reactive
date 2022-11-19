package io.vishal.springboot.reactive.mongoDbCrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import io.vishal.springboot.reactive.mongoDbCrud.dto.ProductDto;
import io.vishal.springboot.reactive.mongoDbCrud.entity.Product;
import io.vishal.springboot.reactive.mongoDbCrud.repo.ProductRepository;
import io.vishal.springboot.reactive.mongoDbCrud.util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Flux<ProductDto> getProducts(){
		return productRepository.findAll()
				.map((product) -> AppUtils.entityToDto(product));       //no need to directly use model class instead we need to use DTO class
	}
	
	public Mono<ProductDto> getProduct(String id){
		return productRepository.findById(id)
				.map((product) -> AppUtils.entityToDto(product));
	}
	
	public Flux<ProductDto> getProductInRange(double min, double max){
		return productRepository.findByPriceBetween(Range.closed(min, max));
	}
	
	public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono){
		log.info("saving product");
		Mono<ProductDto> savedProduct = productDtoMono
				             .log()                                                          //This logs productsDto object
				             .map((productDto) -> AppUtils.dtoToEntity(productDto))
				             .log()                                                          //This logs product Object
		                     .flatMap((product) -> productRepository.insert(product))        //one to many use flatmap
		                     .map((product) -> AppUtils.entityToDto(product));               //single mapping we can use map
		
		//savedProduct.subscribe(productDto -> System.out.println(productDto)); //cannot subscribe one product multiple times
		
		return savedProduct;
	}
	
	public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono,String id){
		return productRepository.findById(id)
		                 .flatMap(product -> productDtoMono.map(productDto -> AppUtils.dtoToEntity(productDto))
		                 .doOnNext(e -> e.setId(id)))
		                 .flatMap((p)-> productRepository.save(p))
		                 .map(product -> AppUtils.entityToDto(product));
	}
	
	public Mono<Void> deleteProduct(String id){
		return productRepository.deleteById(id);
	}
	
	
	public Flux<Void> deleteAllProduct(){
		return productRepository.findAll()
		                 .flatMap((product) -> productRepository.delete(product));
	}
}
