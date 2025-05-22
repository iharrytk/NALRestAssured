package POSTAPIs;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import POJOs.AddUserPOJO;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostAndGetUserPOJO {
	public static String randomEmail() {
		
		return "apitesting"+System.currentTimeMillis()+"@gmail.com";
	}
	
	@Test
	public void postAndGetUser_with_JSONPOJO() {
		
		//Step 1==>post call-->Add a user
		//Step 2==>get call-->Verify the user has been added in step 1 using a get call	

		 RestAssured.baseURI="https://gorest.co.in";
		 
		 AddUserPOJO obj=new AddUserPOJO("suryat",randomEmail(),"male","active");
		 
		 Response response=
				 		given()
				 			.contentType(ContentType.JSON)
				 			.body(obj)
				 			.header("Authorization","Bearer 177ad5140ea25bcba6fb5629bca244944bb45d48d68025c985cc7711aee7a597")
				 		.when()
				 			.post("/public/v2/users/");
		 
		 
		 response.prettyPrint();
		 
		 
		 
		 JsonPath js=response.jsonPath();
		 int userId=js.getInt("id");
		 System.out.println("The user  has been added and the id is:"+userId);
		 
		 Assert.assertEquals(js.getString("name"), obj.getName());
		 
		 
		 given().log().all()
			.header("Authorization","Bearer 177ad5140ea25bcba6fb5629bca244944bb45d48d68025c985cc7711aee7a597")
		.when().log().all()
			.get("/public/v2/users/"+ userId)
		.then().log().all()
			.assertThat()
			.statusCode(200)
			.and()
			.body("id",equalTo(userId));

		
	}

}
