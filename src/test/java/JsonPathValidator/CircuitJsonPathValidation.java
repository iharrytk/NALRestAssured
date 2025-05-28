package JsonPathValidator;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


import java.util.List;

public class CircuitJsonPathValidation {
	
	
	@Test
	public void circuitJsonPathTest() {
		RestAssured.baseURI="https://ergast.com";
		
		
		Response response=given()
		.contentType(ContentType.JSON)
		.when()
		.get("/api/f1/2017/circuits.json");
		
		//We have JQL written to take the count of total number of circuits.
		//Go through the Request JSON to understand the JQL
		String jsonResponse=response.asString();
		int circuitCount=JsonPath.read(jsonResponse,"$.MRData.CircuitTable.Circuits.length()");
		System.out.println("The circuitCount for the year 2017 is:"+circuitCount);
		Assert.assertEquals(circuitCount, 20);
		
		
		//We have JQL written to take the number of countries participating in the year 2017.
		//Go through the Request JSON to understand the JQL
		List<String> countriesParticipating=JsonPath.read(jsonResponse,"$.MRData..country");
		System.out.println("The countries participating for the year 2017 is:"+countriesParticipating.size());
		int count=0;
		for(String s: countriesParticipating) {
			
			count++;
			System.out.println(+count+":"+s);
		}
		Assert.assertTrue(countriesParticipating.contains("Brazil"),"Verified wether brazil is particpating");
	}

}
