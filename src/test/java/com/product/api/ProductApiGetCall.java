package com.product.api;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProductApiGetCall {

	@Test
	public void productGet() {

		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = given().when().get("/products");

		// Deserialize get call JSON response
		ObjectMapper mapper = new ObjectMapper();
		try {
			ProductPOJO[] responsejsonpojo = mapper.readValue(response.getBody().asString(), ProductPOJO[].class);
			for (int i = 0; i < responsejsonpojo.length; i++) {

				int id = responsejsonpojo[i].getId();
				String title = responsejsonpojo[i].getTitle();
				Object price = responsejsonpojo[i].getPrice();
				Object rate = responsejsonpojo[i].getRating().getRate();
				int count = responsejsonpojo[i].getRating().getCount();

				System.out.println(+id + "||" + title + "||" + price + "||" + rate + "||" + count);

			}

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
