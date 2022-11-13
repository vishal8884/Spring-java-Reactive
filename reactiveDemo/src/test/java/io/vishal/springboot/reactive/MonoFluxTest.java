package io.vishal.springboot.reactive;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

	@Test
	public void testMono() {
		System.out.println("--------------------------test mono without exception----------------------------");
		Mono<String> monoString = Mono.just("vishal")//any type dataType we can add
				.log(); 
		monoString.subscribe(System.out::println);
	}
	
	
	@Test
	public void testMonoException() {
		System.out.println("----------------------------test mono with exception-----------------------------");
		Mono<?> monoString = Mono.just("vishal")//any type dataType we can add
				.then(Mono.error(new RuntimeException("Exception")))
				.log(); 
		monoString.subscribe(System.out::println,(e) -> System.out.println(e.getMessage()));
	}
	
	@Test
	public void testFlux() {
		System.out.println("----------------------------test flux without exception-----------------------------");
		Flux<String> fluxString = Flux.just("Spring","Springboot","Hibernate","microservice")
				.concatWithValues("AWS")
				.log();
		
		fluxString.subscribe(System.out::println);
	}
	
	@Test
	public void testFluxException() {
		System.out.println("----------------------------test flux with exception-----------------------------");
		Flux<String> fluxString = Flux.just("Spring","Springboot","Hibernate","microservice")
				.concatWithValues("AWS")
				.concatWith(Flux.error(new RuntimeException("Exception occured in Flux")))
				.concatWithValues("Cloud")  //this wont we onNext() as error occured
				.log();
		
		fluxString.subscribe(System.out::println,(e) -> System.out.println(e.getMessage()));
	}
}
