package GoogleAPI;
import io.restassured.RestAssured;
import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.apache.http.util.Asserts;

public class PlaceE2E {

	
	public static void main(String[] args) {
		
		//Given - All input Details
		//When - Submit the API - resource details, HTTP method
		//Then - Validate the response

		//Basic Validation (Class 17) 
	/*	RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server","Apache/2.4.18 (Ubuntu)");
	*/	
		//Class 18 - Parsing JSON Response
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server","Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		//System.out.println("Response is :\n\n"+response);
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		String status = js.getString("status");
		
		System.out.println("Status is : " +status);
		System.out.println("Place Id is : "+placeId);
		
		//Update Place
		String Temp = "71 Summer run, USA";
		double a = Math.random();
		String NewAddress=a+Temp;
		System.out.println("New Address is : "+NewAddress);
		String Updteresponse=given().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+NewAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("maps/api/place/update/json")
		.then().statusCode(200).body("msg",equalTo("Address successfully updated"))
		.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
	    
		//System.out.println("Update Place\n"+Updteresponse);
	
		
		//Get Place
		String response2=given().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				.when().get("maps/api/place/get/json")
				.then().statusCode(200).extract().response().asString();
	
		//System.out.println("Get Place response is : "+response2);
		JsonPath js1 = new JsonPath(response2); 
		String updatedAddress = js1.getString("address");
		System.out.println(updatedAddress.equals(NewAddress));
 		
		
	}

}
