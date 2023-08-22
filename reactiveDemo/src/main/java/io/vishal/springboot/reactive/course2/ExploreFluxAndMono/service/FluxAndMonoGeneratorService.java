package io.vishal.springboot.reactive.course2.ExploreFluxAndMono.service;

import java.util.List;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FluxAndMonoGeneratorService {

	public Flux<String> namesFlux(){
		return Flux.fromIterable(List.of("Alex","ben","charle"));
	}
	
	
	public Mono<String> nameMono(){
		return Mono.just("Vishal");
	}
	
	
	public static void main(String[] args) {
		FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
		
		//FLUX
		Flux<String> namesFlux = fluxAndMonoGeneratorService.namesFlux();
		namesFlux.subscribe(name -> System.out.println(name));
		
		//MONO
		Mono<String> nameMono = fluxAndMonoGeneratorService.nameMono();
		nameMono.subscribe(name -> System.out.println(name));
	}
}
