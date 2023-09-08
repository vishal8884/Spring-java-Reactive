package io.vishal.springboot.reactive.course2.ExploreFluxAndMono.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FluxAndMonoGeneratorService {

	public Flux<String> namesFlux(){
		return Flux.fromIterable(List.of("Alex","ben","charle")).log();
	}
	
	
	public Mono<String> nameMono(){
		return Mono.just("Vishal").log();
	}
	
	
	public Flux<String> namesFluxMap(){
		return Flux.fromIterable(List.of("Alex","ben","charle"))
				.map(s -> s.toUpperCase())
				.log();
	}
	
	public Flux<String> namesFluxMapImmuatble(){
		Flux<String> flux = Flux.fromIterable(List.of("Alex","ben","charle"))
				.log();
		flux.map(s -> s.toUpperCase());
		return flux;
	}
	
	public Flux<String> namesFluxFilter(){
		return Flux.fromIterable(List.of("Alex","Bob","Charle")).filter(s -> s.length() > 3);
	}
	
	public Flux<String> namesFluxFlatMap(){
		return Flux.fromIterable(List.of("Alex","ben","charle"))
				.map(s -> s.toUpperCase())
				.filter(s -> s.length() > 3)
				.flatMap(s -> splitStr(s))
				.log();
	}
	
	public static void main(String[] args) {
		FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
		
		//FLUX
		System.out.println("----------------------FLUX-------------------------------------------------------------------\n");
		Flux<String> namesFlux = fluxAndMonoGeneratorService.namesFlux();
		namesFlux.subscribe(name -> System.out.println(name));
		
		
		
		System.out.println("-----------------------MONO------------------------------------------------------------------\n");
		//MONO
		Mono<String> nameMono = fluxAndMonoGeneratorService.nameMono();
			nameMono.subscribe(name -> System.out.println(name));
			
			
			
			
		System.out.println("----------------------FLUX USING MAP---------------------------------------------------------\n");
		//FLUX using map
		fluxAndMonoGeneratorService.namesFluxMap().subscribe(name -> System.out.println(name));
		
		
		
		
		
		System.out.println("----------------------FLUX FLATMAP---------------------------------------------------------\n");
		//Flux flatMap
		fluxAndMonoGeneratorService.namesFluxFlatMap().subscribe(name -> System.out.println(name));
	}
	
	//Private Utils
	
	private Flux<String> splitStr(String str){
		String[] splittedArr = str.split("");
		return Flux.fromArray(splittedArr);
	}
}
