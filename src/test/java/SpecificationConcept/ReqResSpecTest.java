package SpecificationConcept;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReqResSpecTest {
	
public static RequestSpecification requestSpec() {
		
		RequestSpecification request=new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
					.setContentType(ContentType.JSON)
						.addHeader("Authorization", "Bearer 177ad5140ea25bcba6fb5629bca244944bb45d48d68025c985cc7711aee7a597")
							.build();
		
		
		return request;
		
		
	}
public static ResponseSpecification get_user_status_200Ok_Response_Spec() {
	
	
	ResponseSpecification response	=new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
					.expectHeader("server", "cloudflare")
						.expectStatusCode(200)
							.build();
		
		return response;
		
	}
	
	
	@Test
	public void get_user_status200Ok() {
		
		given().log().all()
			.spec(requestSpec())
				.when()
					.get("/public/v2/users")
						.then()
							.assertThat()
								.spec(get_user_status_200Ok_Response_Spec());
					
		
		
	}

}
