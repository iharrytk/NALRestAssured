package com.qa.user;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest {
	
	public static String getRandonEmail() {
		
		return "restassuredpractice"+System.currentTimeMillis()+"@gmail.com";
	}
	
	
	@Test
	public void createUser() {
		
		
		
		RestAssured.baseURI="https://gorest.co.in";
		
		User userrequest=new User("Har", getRandonEmail(), "female", "active");
		
		
		Response response=RestAssured.given()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer 177ad5140ea25bcba6fb5629bca244944bb45d48d68025c985cc7711aee7a597")
			.body(userrequest)
			.when()
			.post("/public/v2/users");
		
		Integer userid=response.jsonPath().get("id");
		System.out.println("The user that has been posted has the id:"+userid);
		
		//GET API to get the same userid:
		Response getresponse=RestAssured.given()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 177ad5140ea25bcba6fb5629bca244944bb45d48d68025c985cc7711aee7a597")
		.when().log().all()
		.get("/public/v2/users/"+userid);
		
		System.out.println(getresponse.asPrettyString());
		
		
		
		//deserialization
		ObjectMapper mapper=new ObjectMapper();
		try {
			User userresponse=mapper.readValue(getresponse.getBody().asString(), User.class);
			
			Assert.assertEquals(userresponse.getId(), userid);
			Assert.assertEquals(userresponse.getName(), userrequest.getName());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
			
		
		
	}

}
