package io.vishal.springboot.reactive.mongoDbCrud.repo;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import io.vishal.springboot.reactive.mongoDbCrud.dto.ProductDto;
import io.vishal.springboot.reactive.mongoDbCrud.entity.Product;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String>{

	Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);
}
