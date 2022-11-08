package io.vishal.springboot.reactive;

import java.time.Duration;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
class ReactiveDemoApplicationTests {

	@Test
	void testMono() {
		System.out.println("--------------------testMono()------------------------------------------");
		Mono<String> mono = Mono.just("Mackbook pro2"); //mono is responsible for publishing  0...1
		
		mono.log()                                      //logs all the signals
		    .map(data -> data.toUpperCase())            //transforms the data
		    .subscribe(data -> System.out.println("data :: "+data));  //subscibe starts all this process
	}
	
	@Test
	void testFlux() {
		System.out.println("--------------------testFlux()------------------------------------------");
		Flux<String> flux = Flux.just("Mackbook pro2","iphone","subs"); //this works for multiple sets of data  0...n
		
		flux.log()
		    .map(data -> data.toUpperCase())
		    .subscribe(data -> System.out.println("data :: "+data));
	}
	
	@Test
	void testFluxWithDelay() {  
		System.out.println("--------------------testFluxWithDelay()------------------------------------------");
		Flux<String> flux = Flux.just("acer","asus","dell")
				.delayElements(Duration.ofSeconds(10)); //this does not print as we added delay and test completes by the delay time.
		
		flux.log()
		    .map(data -> data.toUpperCase())
		    .subscribe(data -> System.out.println("data :: "+data));
	}
	
	@Test
	void testFluxWithDelayAndThreadSleep() throws InterruptedException {  //same as before method
		System.out.println("--------------------testFluxWithDelayAndThreadSleep()------------------------------------------");
		Flux.just("acer","asus","dell")
				.delayElements(Duration.ofSeconds(2)) //this does not print as we added delay and test competes by the delay time
		        .log()
		        .map(data -> data.toUpperCase())
		        .subscribe(System.out::println);
		
		Thread.sleep(6000); //to resolve above issue
	}
	
	@Test
	void testFluxWithArrayList() throws InterruptedException {  //same as before method
		System.out.println("--------------------testFluxWithArrayList()------------------------------------------");
		
		Flux.fromIterable(Arrays.asList("my","name","is","vishal"))
		    .log()
		    .map(data -> data.toUpperCase())
		    .subscribe(System.out::println);
		
	}
	
	@Test
	void testFluxWithArrayListUsingSubscriber() throws InterruptedException {  //same as before method
		System.out.println("--------------------testFluxWithArrayListUsingSubscriber()------------------------------------------");
		
		Flux.fromIterable(Arrays.asList("the","tom","and","jerry"))
		    .log()
		    .map(data -> data.toUpperCase())
		    .subscribe(new Subscriber<String>() {

				@Override
				public void onSubscribe(Subscription s) {
					s.request(2);  //by default value will be zero if we dont add this and publisher will not publish
					//as the capacity of the publisher is set to 2..it will not publish "jerry"
				}

				@Override
				public void onNext(String order) {
					System.out.println("The order is :: "+order);
				}

				@Override
				public void onError(Throwable t) {
					t.printStackTrace();
					
				}

				@Override
				public void onComplete() {
					System.out.println("Completely Done!!");
				}

				
			});
		
	}
	
	
	
	@Test
	void testFluxWithArrayListUsingSubscriberWithBatching() throws InterruptedException {  //same as before method
		System.out.println("--------------------testFluxWithArrayListUsingSubscriber()------------------------------------------");
		
		Flux.fromIterable(Arrays.asList("a","road","and","runner","boys"))
		    .log()
		    .map(data -> data.toUpperCase())
		    .subscribe(new Subscriber<String>() {
		    	
		    	private long count = 0;
		    	private Subscription subscription;

				@Override
				public void onSubscribe(Subscription subscription) {
					this.subscription = subscription;
					subscription.request(3);  //by default value will be zero if we dont add this and publisher will not publish
					//as the capacity of the publisher is set to 2..it will not publish "jerry"
				}

				@Override
				public void onNext(String order) {
					count++;
					if(count>=3) {    //this makes processing in batches of 3
						count = 0;
						subscription.request(3);
					}
					System.out.println("The order is :: "+order);
				}

				@Override
				public void onError(Throwable t) {
					t.printStackTrace();
					
				}

				@Override
				public void onComplete() {
					System.out.println("Completely Done!!");
				}

				
			});
		
	}

}
