package com.ecommerce.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDTO {

	long productId;
	
	@NotEmpty(message = "Product Name Should not be empty or Null")
	String productName;
	
	@NotEmpty(message = "Product Category Should not be empty or Null")
	String productCategory;
	
	long userId;
}
