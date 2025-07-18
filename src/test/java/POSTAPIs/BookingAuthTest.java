package POSTAPIs;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import POJOs.RestfullbookerPOJO;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.io.*;

public class BookingAuthTest {
	
	@BeforeTest
	public void allureSetup() {
		
		
		RestAssured.filters(new AllureRestAssured());
	}
	
	@Test
	public void getBookingauthTokenTest_with_JSONString() {
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		String token=given().log().all()
				.contentType(ContentType.JSON)
					.body("{\r\n"
							+ "    \"username\" : \"admin\",\r\n"
							+ "    \"password\" : \"password123\"\r\n"
							+ "}")
			.when()
				.post("/auth")
					.then()
						.assertThat()
							.statusCode(200)
								.and()
									.extract()
										.path("token");
			
		
		System.out.println("The token returned by the POST call is:"+token);
		Assert.assertNotNull(token);
		
		
		
		
	}
	
	@Test
	public void getBookingauthTokenTest_with_JSONFile() {
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		

		
		String token=given().log().all()
				.contentType(ContentType.JSON)
					.body(new File("./src/test/resource/data/restfulbookerauth.json"))
			.when()
				.post("/auth")
					.then()
						.assertThat()
							.statusCode(200)
								.and()
									.extract()
										.path("token");
			
		
		System.out.println("The token returned by the POST call is:"+token);
		Assert.assertNotNull(token);
		
		
		
		
	}
	
	
	@Test
	public void getBookingauthTokenTest_with_JSONPOJO() {
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		RestfullbookerPOJO obj=new RestfullbookerPOJO("admin","password123");
		String token=given()
		.contentType(ContentType.JSON)
		.body(obj)
		.when()
		.post("/auth")
		.then()
		.assertThat()
		.statusCode(200)
		.extract()
		.path("token");
		
		
		System.out.println("The token of restfuull booker is:"+token);
			
	}

}
