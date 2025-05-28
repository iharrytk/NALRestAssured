package com.product.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductPOJOLombok {
	
	
	private int id;
	private String title;
	private Object price;
	private String description;
	private String category;
	private String image;
	private Rating rating;
	
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Getter
	@Setter
	public static class Rating {

		private Object rate;
		private int count;
	}

}
