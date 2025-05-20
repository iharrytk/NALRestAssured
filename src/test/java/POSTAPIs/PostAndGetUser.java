package POSTAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.io.*;

public class PostAndGetUser {
	@Test
	public void getAllUsersWithQueryParametersTest() {
	//Step 1==>post call-->Add a user
	//Step 2==>get call-->Verify the user has been added in step 1 using a get call	

		RestAssured.baseURI="https://gorest.co.in";
		
		int userid=given().log().all()
				.contentType(ContentType.JSON)
				.body(new File("./src/test/resource/data/adduser.json"))
				.header("Authorization","Bearer 177ad5140ea25bcba6fb5629bca244944bb45d48d68025c985cc7711aee7a597")
			.when().log().all()
				.post("/public/v2/users/")
			.then().log().all()
				.assertThat()
				.statusCode(201)
				.and()
				.body("name", equalTo("suryat"))
				.extract()
				.path("id");
		
		System.out.println("user id -->" + userid);				
											
		
		given().log().all()
			.header("Authorization","Bearer 177ad5140ea25bcba6fb5629bca244944bb45d48d68025c985cc7711aee7a597")
		.when().log().all()
			.get("/public/v2/users/"+ userid)
		.then().log().all()
			.assertThat()
			.statusCode(200)
			.and()
			.body("id",equalTo(userid));
		
					
		
	}
}
