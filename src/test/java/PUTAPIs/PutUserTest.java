package PUTAPIs;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.qa.user.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PutUserTest {
	
	public static String getRandonEmail() {
		
		return "restassuredpractice"+System.currentTimeMillis()+"@gmail.com";
	}
	
	
	@Test
	public void updateUser_PUTcall() {
		
		
		
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
		
		//Updating the user that has been posted in step 33 to 38
		userrequest.setName("Bar");
		userrequest.setEmail("bar@gmail.com");
		
		//PUT API to actually update the user with the updated values from step 44 and 45
		RestAssured.given()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 177ad5140ea25bcba6fb5629bca244944bb45d48d68025c985cc7711aee7a597")
		.body(userrequest)
		.when()
		.put("/public/v2/users/"+userid)
		.then().log().all()
		.statusCode(200)
		.assertThat()
		.body("id", equalTo(userid))
		.and()
		.body("name", equalTo(userrequest.getName()))
		.and()
		.body("email", equalTo(userrequest.getEmail()));
		
		
		
		
		
		
			
			
		
		
	}

	
	
	
}
