package GETAPIs;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetCallPathParameterswithDataProviders {

	@DataProvider(name="circuitCountYearWise")
	public Object[][] circuitCountYearWise(){
		
		return new Object[][]{
			
			{"2001",17},
			{"2009",17},
			{"2024",24},
			{"2010",19}
			
			
			
		};
		
		}
	
	@Test(dataProvider = "circuitCountYearWise")
	public void getCircuitCountFormulaOneTest(String yearSeason,int circuitCount) {
	//Path Parameters Example
		
		//https://ergast.com/api/f1/2001/circuits.json 
			
		RestAssured.baseURI="https://ergast.com";
		
		given().log().all()
			.pathParam("year", yearSeason)
			.when()
				.get("/api/f1/{year}/circuits.json")
					.then().log().all()
						.assertThat()
							.body("MRData.CircuitTable.Circuits.circuitId",hasSize(circuitCount));
				
	}
	
	
	
	@Test
	public void getCircuitCountFormulaOneTestwithoutdataproviders() {
	//Path Parameters Example
		
		//https://ergast.com/api/f1/2001/circuits.json 
			
		RestAssured.baseURI="https://ergast.com";
		
		Response response=given().log().all()
			.pathParam("year", 2009)
			.when()
				.get("/api/f1/{year}/circuits.json");
		
		JsonPath js=response.jsonPath();
		List<Object> circuitIdList=js.getList("MRData.CircuitTable.Circuits.circuitId");
		
		for(int i=0;i<circuitIdList.size();i++) {
			
			Object circuitid=circuitIdList.get(i);
			System.out.println(circuitid);
			
		}
		
					
	}

}
