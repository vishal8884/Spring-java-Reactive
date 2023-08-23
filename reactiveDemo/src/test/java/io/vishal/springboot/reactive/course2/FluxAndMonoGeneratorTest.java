package io.vishal.springboot.reactive.course2;

import org.junit.jupiter.api.Test;

import io.vishal.springboot.reactive.course2.ExploreFluxAndMono.service.FluxAndMonoGeneratorService;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoGeneratorTest {

	FluxAndMonoGeneratorService fluxAndMonoGenerator = new FluxAndMonoGeneratorService();
	
	@Test
	void namesFlux() {
		var namesFlux = fluxAndMonoGenerator.namesFlux();
		
		StepVerifier.create(namesFlux)
		            .expectNext("Alex","ben","charle")
	             	//.expectNextCount(3)
		            .verifyComplete();
	}
	
	@Test
	public void namesFluxMapTest() {
		Flux<String> namesFluxMap = fluxAndMonoGenerator.namesFluxMap();
		StepVerifier.create(namesFluxMap).expectNext("ALEX","BEN","CHARLE").verifyComplete();
	}
}
