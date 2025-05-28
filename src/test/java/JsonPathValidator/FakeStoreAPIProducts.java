package JsonPathValidator;

import static io.restassured.RestAssured.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FakeStoreAPIProducts {
	@Test
	public void jsonValidatorProducts() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		Response response = given().when().get("/products");
		String jsonResponse = response.asString();

		// JsonPath: List of all Id's
		List<Integer> allIds = JsonPath.read(jsonResponse, "$[*].id");
		System.out.println("Total number of Id's:" + allIds.size());
		Assert.assertEquals(allIds.size(), 20);

		//// JsonPath: List of all titles's
		List<String> allTitles = JsonPath.read(jsonResponse, "$[*].title");
		Assert.assertTrue(allTitles.contains("Mens Cotton Jacket"));

		// JSonPAth:The details about a product that has rating 3
		List<Object> rates = JsonPath.read(jsonResponse, "$[?(@.rating.rate==3)]");
		System.out.println(rates);
		
		
		// JSonPAth:The details about a title,price,id that has  category=jewelery
		List<Map<String,Object>> categoryjewelery = JsonPath.read(jsonResponse, "$[?(@.category=='jewelery')].[\"title\",\"price\",\"id\"]");
		System.out.println(categoryjewelery);
		for(Map<String,Object> product:categoryjewelery) {
			String titlevalue=(String)product.get("title");
			Object pricevalue=(Object)product.get("price");
			int idvalue=(Integer)product.get("id");
			System.out.println("title:"+titlevalue+"||price:"+pricevalue+"||id:"+idvalue);
		}
			
	}

}
