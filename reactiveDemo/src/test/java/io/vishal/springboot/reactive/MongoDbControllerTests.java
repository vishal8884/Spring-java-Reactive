package io.vishal.springboot.reactive;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import io.vishal.springboot.reactive.mongoDbCrud.controller.ProductController;
import io.vishal.springboot.reactive.mongoDbCrud.dto.ProductDto;
import io.vishal.springboot.reactive.mongoDbCrud.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
public class MongoDbControllerTests {

	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private ProductService service;
	
	@Test
	public void addProductTest() {
		Mono<ProductDto> productDtoMono = Mono.just(new ProductDto("102","mobile","1","10000"));
		when(service.saveProduct(productDtoMono)).thenReturn(productDtoMono);
		
		webTestClient.post().uri("/products/save").body(Mono.just(productDtoMono),ProductDto.class).exchange().expectStatus().isOk();
	}
	
	@Test
	public void getProductsTest() {
		Flux<ProductDto> productDtoToFlux = Flux.just(new ProductDto("102","mobile","1", "19000"),new ProductDto("103","mobile","1", "19000"));
		when(service.getProducts()).thenReturn(productDtoToFlux);
	}
	
}
