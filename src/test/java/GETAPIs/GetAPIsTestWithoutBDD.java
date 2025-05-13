package GETAPIs;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/*******NON BDD Approach-Get api call *********/

public class GetAPIsTestWithoutBDD {
	RequestSpecification request;

	@BeforeTest
	public void setUp() {

		RestAssured.baseURI = "https://gorest.co.in";
		request = RestAssured.given();
		request.header("Authorization", "Bearer 177ad5140ea25bcba6fb5629bca244944bb45d48d68025c985cc7711aee7a597");
	}

	@Test
	public void getAllUsersTest() {
		/* Request */
		//RestAssured.baseURI = "https://gorest.co.in";

		//RequestSpecification request = RestAssured.given();
		//request.header("Authorization", "Bearer 177ad5140ea25bcba6fb5629bca244944bb45d48d68025c985cc7711aee7a597");
		Response response = request.get("/public/v2/users");

		/* Response */

		/*----------Status code--------*/
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		/*----------Status code--------*/
		String statusLine = response.getStatusLine();
		System.out.println(statusLine);

		/*----------Response body in pretty format--------*/
		String responseBody = response.prettyPrint();
		System.out.println(responseBody);

		/*----------Response header content type--------*/
		String contenttype = response.header("Content-Type");
		System.out.println(contenttype);

		/*----------Response all headers size and list--------*/
		List<Header> allHeaders = response.headers().asList();
		System.out.println(allHeaders.size());

		for (Header h : allHeaders) {

			System.out.println(h.getName() + "	:	" + h.getValue());

		}

		/* Validations */
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		Assert.assertEquals(contenttype, "application/json; charset=utf-8");
	}

	@Test
	public void getUserWithQueryParamTest() {
		/* Request */
		//RestAssured.baseURI = "https://gorest.co.in";

		//RequestSpecification request = RestAssured.given();
		//request.header("Authorization", "Bearer 177ad5140ea25bcba6fb5629bca244944bb45d48d68025c985cc7711aee7a597");
		request.queryParam("name", "Naveen");
		request.queryParam("status", "inactive");
		Response response = request.get("/public/v2/users");

		/* Response */

		/*----------Status code--------*/
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		/*----------Status code--------*/
		String statusLine = response.getStatusLine();
		System.out.println(statusLine);

		/*----------Response body in pretty format--------*/
		String responseBody = response.prettyPrint();
		System.out.println(responseBody);

		/*----------Response header content type--------*/
		String contenttype = response.header("Content-Type");
		System.out.println(contenttype);

		/*----------Response all headers size and list--------*/
		List<Header> allHeaders = response.headers().asList();
		System.out.println(allHeaders.size());

		for (Header h : allHeaders) {

			System.out.println(h.getName() + "	:	" + h.getValue());

		}

		/* Validations */
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		Assert.assertEquals(contenttype, "application/json; charset=utf-8");
	}

	@Test
	public void getUserWithQueryParam_hashmap_Test() {
		/* Request */
		//RestAssured.baseURI = "https://gorest.co.in";

		//RequestSpecification request = RestAssured.given();
		//request.header("Authorization", "Bearer 177ad5140ea25bcba6fb5629bca244944bb45d48d68025c985cc7711aee7a597");
		HashMap<String, String> queryParam = new HashMap<String, String>();
		queryParam.put("name", "Naveen");
		queryParam.put("status", "inactive");
		queryParam.put("gender", "female");
		request.queryParams(queryParam);
		Response response = request.get("/public/v2/users");

		/* Response */

		/*----------Status code--------*/
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		/*----------Status Line--------*/
		String statusLine = response.getStatusLine();
		System.out.println(statusLine);

		/*----------Response body in pretty format--------*/
		String responseBody = response.prettyPrint();
		System.out.println(responseBody);

	}

}
