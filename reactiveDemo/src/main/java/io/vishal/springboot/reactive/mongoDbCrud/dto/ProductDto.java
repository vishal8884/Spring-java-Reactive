package io.vishal.springboot.reactive.mongoDbCrud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

	private String id;
	private String name;
	private String qty;
	private String price;
}
