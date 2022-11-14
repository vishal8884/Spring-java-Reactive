package io.vishal.springboot.reactive.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.vishal.springboot.reactive.handler.CustomerHandler;
import io.vishal.springboot.reactive.handler.CustomerStreamHandler;

@Configuration
public class RouterConfig {
	
	@Autowired
	private CustomerHandler handler;
	
	@Autowired
	private CustomerStreamHandler streamHandler;

	@Bean
	public RouterFunction<ServerResponse> routerFunction(){
		return RouterFunctions.route()
				.GET("/router/customers",(serverRequest) -> handler.loadCustomers(serverRequest))
				.GET("/router/customers/stream", (serverRequest) -> streamHandler.getCustomers(serverRequest))
				.GET("/router/customer/{input}",(serverRequest) -> handler.findCustomer(serverRequest))
				.POST("/router/customer/save",(serverRequest) -> handler.saveCustomer(serverRequest))
				.build();
	}
}
