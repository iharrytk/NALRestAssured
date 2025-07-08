package com.pet.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.api.PetLombokPOJO.Category;
import com.pet.api.PetLombokPOJO.Tags;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Arrays;
import java.util.List;

public class PetTest {
	
	@Test
	public void petlombok_withoutbuilder() {
		
		RestAssured.baseURI="https://petstore.swagger.io";
		
		Category category=new Category(1, "Dog");
		Tags tag1=new Tags(1,"mydaxi");
		Tags tag2=new Tags(2,"mydax");
		List<Tags> tags=Arrays.asList(tag1,tag2);
		List<String> photoUrls=Arrays.asList("www.dax.com","www.daxi.com");
		PetLombokPOJO pet=new PetLombokPOJO(1, category, "Dax", photoUrls, tags, "Active");
		
		Response response=given()
		.log()
		.all()
		.contentType(ContentType.JSON)
		.body(pet)//Serialization
		.when()
		.log()
		.all()
		.post("/v2/pet");
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Deserialization
		
		ObjectMapper mapper=new ObjectMapper();
		try {
			PetLombokPOJO pl=mapper.readValue(response.getBody().asString(), PetLombokPOJO.class);
			System.out.println("The id value is:"+pl.getId());
			System.out.println("The category value id is:"+pl.getCategory().getId());
			System.out.println("The category value name is:"+pl.getCategory().getName());
			System.out.println("The name value is:"+pl.getName());
			System.out.println("The phorourls list is:"+pl.getPhotoUrls());
			System.out.println("The tags list first value:"+pl.getTags().get(0).getId());
			System.out.println("The tags list first value:"+pl.getTags().get(0).getName());
			System.out.println("The tags list second value:"+pl.getTags().get(1).getId());
			System.out.println("The tags list second value:"+pl.getTags().get(1).getName());
			System.out.println("The status value is:"+pl.getStatus());
			
				
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
	public void petlombok_withbuilder() {
		
		RestAssured.baseURI="https://petstore.swagger.io";
		
		Category category=new Category
				.CategoryBuilder()
				.id(1)
				.name("Daxiboy")
				.build();
		
		Tags tag1=new Tags.TagsBuilder().id(1).name("mydaxi").build();
		Tags tag2=new Tags.TagsBuilder().id(2).name("mydaxiboy").build();
		List<Tags> tags=Arrays.asList(tag1,tag2);
		List<String> photoUrls=Arrays.asList("www.dax.com","www.daxi.com");
		PetLombokPOJO pet=new PetLombokPOJO
		.PetLombokPOJOBuilder()
		.id(9)
		.category(category)
		.name("daxiboy")
		.photoUrls(photoUrls)
		.tags(tags)
		.status("active")
		.build();
		
		Response response=given()
		.log()
		.all()
		.contentType(ContentType.JSON)
		.body(pet)//Serialization
		.when()
		.log()
		.all()
		.post("/v2/pet");
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Deserialization
		
		ObjectMapper mapper=new ObjectMapper();
		try {
			PetLombokPOJO pl=mapper.readValue(response.getBody().asString(), PetLombokPOJO.class);
			System.out.println("The id value is:"+pl.getId());
			System.out.println("The category value id is:"+pl.getCategory().getId());
			System.out.println("The category value name is:"+pl.getCategory().getName());
			System.out.println("The name value is:"+pl.getName());
			System.out.println("The phorourls list is:"+pl.getPhotoUrls());
			System.out.println("The tags list first value:"+pl.getTags().get(0).getId());
			System.out.println("The tags list first value:"+pl.getTags().get(0).getName());
			System.out.println("The tags list second value:"+pl.getTags().get(1).getId());
			System.out.println("The tags list second value:"+pl.getTags().get(1).getName());
			System.out.println("The status value is:"+pl.getStatus());
			
				
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
