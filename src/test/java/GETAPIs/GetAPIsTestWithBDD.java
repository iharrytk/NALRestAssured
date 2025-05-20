package GETAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetAPIsTestWithBDD {
	
	
	@Test
	public void getProductsTest() {
		
		given().log().all()
			.when().log().all()
				.get("https://fakestoreapi.com/products")
					.then().log().all()
						.assertThat()
							.contentType(ContentType.JSON)
								.and()
									.statusCode(200)
										.and()
											.header("Connection", "keep-alive")
												.and()
													.body("$.size()", equalTo(20))
														.and()
															.body("id", is(notNullValue()))
																.and()
																	.body("title",hasItem("Mens Cotton Jacket"));
														
		
		
		
		
	}
	
	@Test
	public void getAllUsersTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
			given().log().all()
				.header("Authorization","Bearer 177ad5140ea25bcba6fb5629bca244944bb45d48d68025c985cc7711aee7a597")
				.when().log().all()
					.get("/public/v2/users")
						.then().log().all()
							.assertThat()
								.body("$.size()", equalTo(10))
									.and()
										.body("$.id", is(notNullValue()))
											.and()
												.body("name", hasItem("Baala Kapoor"));
								
						
		
	}
	
	@Test
	public void getAllUsersWithQueryParametersTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		given().log().all()
			.queryParam("name", "madhuri")
			.queryParam("status", "inactive")
			.when().log().all()
				.get("/public/v2/users")
					.then().log().all()
						.assertThat()
							.statusCode(200);
		
		
						
		
	}
	
	@Test
	public void getProductsLimit5Test() {
		
		RestAssured.baseURI="https://fakestoreapi.com";
		
		Response response=given().log().all()
			.queryParam("limit", "5")
			.when().log().all()
				.get("/products");
					
		JsonPath js=response.jsonPath();
		int firstjsonobjectId=js.getInt("[0].id");
		System.out.println("firstjsonobjectId : "+firstjsonobjectId);
									
		String secondjsonobjecttitle=js.getString("[1].title");					
		System.out.println("secondjsonobjecttitle : "+secondjsonobjecttitle);
		
		String thirdjsonobjectRatingCount=js.getString("[2].rating.count");					
		System.out.println("thirdjsonobjectRatingCount : "+thirdjsonobjectRatingCount);
		
	}
	
	
	@Test
	public void getProductsLimit10Test() {
		//Extracting JSON Array from Response
		
		RestAssured.baseURI="https://fakestoreapi.com";
		
		Response response=given().log().all()
			.queryParam("limit", "10")
			.when().log().all()
				.get("/products");
		
		JsonPath js=response.jsonPath();
		List<Integer> listId=js.getList("id");
		List<String> listTitle=js.getList("title");
		List<Object> listRatingRate=js.getList("rating.rate");
		List<Object> listRatingCount=js.getList("rating.count");
		
		for(int i=0;i<listId.size();i++) {
			int id=listId.get(i);
			String title=listTitle.get(i);
			Object ratingRate=listRatingRate.get(i);
			Object ratingCount=listRatingCount.get(i);
			System.out.println("ID: "+id+"|| "+"title: "+title+"|| "+"ratingRate: "+ratingRate+"||"+"ratingCount: "+ratingCount);
		}
		
		
		
	}
	
	
	@Test
	public void getSingleUserTest() {
		//Extracting JSON Object from Response
		
		RestAssured.baseURI="https://gorest.co.in";
		
		Response response=given().log().all()
				.when().log().all()
					.get("/public/v2/users/7725399");
			
			JsonPath js=response.jsonPath();
			int id=js.getInt("id");
			String name=js.getString("name");
			String email=js.getString("email");
			
			System.out.println("id:"+id+"||"+"name:"+name+"||"+"email:"+email);
		
		
	}
	
	@Test
	public void getSingleUserExtractMethodTest() {
		//Extracting JSON Object value id from Response using extract() method
		
		RestAssured.baseURI="https://gorest.co.in";
		
		int id=given().log().all()
				.when().log().all()
					.get("/public/v2/users/7725399")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.extract()
										.path("id");
			
		System.out.println("The value of userid is:"+id);
			
			
		
		
	}
	
	@Test
	public void getSingleUserExtractMultipleMethodTest() {
		//Extracting JSON Object value id from Response using extract() method
		
		RestAssured.baseURI="https://gorest.co.in";
		
		Response response=given().log().all()
				.when().log().all()
					.get("/public/v2/users/7725399");
		
			
			
		int id=response.then().extract().path("id");
		String name=response.then().extract().path("name");
		
		System.out.println("The value of userid is:"+id);
		System.out.println("The value of userName is:"+name);	
			
		
		
	}
	
	@Test
	public void getSingleUserExtractMultipleAliasTest() {
		//Extracting JSON Object value id from Response using extract() method
		
		RestAssured.baseURI="https://gorest.co.in";
		
		Response response=given().log().all()
				.when().log().all()
					.get("/public/v2/users/7725399")
						.then()
							.extract()
								.response();
								
		
			
			
		int id=response.path("id");
		String name=response.path("name");
		
		System.out.println("The value of userid is:"+id);
		System.out.println("The value of userName is:"+name);	
			
		
		
	}
	
	@Test
	public void getFormulaonePathParametersTest() {
		//Path Parameters Example
		
		//https://ergast.com/api/f1/2001/circuits.json 
			
		RestAssured.baseURI="https://ergast.com";
		
		given().log().all()
			.pathParam("year", 2001)
			.when().log().all()
				.get("/api/f1/{year}/circuits.json")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.body("MRData.CircuitTable.Circuits.circuitId", hasSize(17))
										.and()
											.body("MRData.CircuitTable.season", equalTo("2001"))
												.and()
													.body("MRData.CircuitTable.Circuits[0].circuitId", equalTo("albert_park"));
		
	}
}
