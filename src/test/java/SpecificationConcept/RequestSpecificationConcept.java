package SpecificationConcept;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationConcept {

	@Test
	public void get_user_Request_Spec() {
	
	RestAssured.baseURI="https://gorest.co.in";
	
	RequestSpecification request=new RequestSpecBuilder()
			.setBaseUri("https://gorest.co.in")
				.setContentType(ContentType.JSON)
					.addHeader("Authorization", "Bearer 177ad5140ea25bcba6fb5629bca244944bb45d48d68025c985cc7711aee7a597")
						.build();
	
	given()
		.spec(request)
		.when()
			.get()
				.then()
					.assertThat()
						.statusCode(200);
	
	}
	
	
	public static RequestSpecification requestSpec() {
		
		RequestSpecification request=new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
					.setContentType(ContentType.JSON)
						.addHeader("Authorization", "Bearer 177ad5140ea25bcba6fb5629bca244944bb45d48d68025c985cc7711aee7a597")
							.build();
		
		
		return request;
		
		
	}
	
	
	@Test
	public void get_user_Request_Spec_with_queryparam() {
	given().log().all()
		.spec(requestSpec())
		.queryParam("name", "naveen")
		.when().log().all()
			.get("/public/v2/users")
				.then().log().all()
					.assertThat()
						.statusCode(200);
	
	}

}
