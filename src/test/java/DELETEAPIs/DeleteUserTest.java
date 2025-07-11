package DELETEAPIs;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.qa.user.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest {
	
public static String getRandonEmail() {
		
		return "restassuredpractice"+System.currentTimeMillis()+"@gmail.com";
	}
	
	
	@Test
	public void deleteUser_PUTcall() {
		
		
		
		RestAssured.baseURI="https://gorest.co.in";
		
		User userrequest=new User("Har", getRandonEmail(), "female", "active");
		
		//create a record using POST call
		Response response=RestAssured.given()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer 177ad5140ea25bcba6fb5629bca244944bb45d48d68025c985cc7711aee7a597")
			.body(userrequest)
			.when()
			.post("/public/v2/users");
		
		Integer userid=response.jsonPath().get("id");
		System.out.println("The user that has been posted has the id:"+userid);
		
	
		
		//Delete API to actually delete the user created in step 31 to 39
		RestAssured.given()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 177ad5140ea25bcba6fb5629bca244944bb45d48d68025c985cc7711aee7a597")
		.body(userrequest)
		.when()
		.delete("/public/v2/users/"+userid)
		.then().log().all()
		.statusCode(204);
		
		
		//Verify the record has been deleted by calling the deleted record using GET call
		RestAssured.given()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 177ad5140ea25bcba6fb5629bca244944bb45d48d68025c985cc7711aee7a597")
		.body(userrequest)
		.when()
		.get("/public/v2/users/"+userid)
		.then().log().all()
		.statusCode(404)
		.assertThat()
		.body("message", equalTo("Resource not found"));
		
		
		
		
		
		
			
			
		
		
	}

}
