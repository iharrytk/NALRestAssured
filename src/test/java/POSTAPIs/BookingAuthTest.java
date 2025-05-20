package POSTAPIs;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.io.*;

public class BookingAuthTest {
	
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

}
