package ExcelDrivenHashmap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import GoogleAPI.payload;
import io.restassured.RestAssured;

public class AddPlace {

	
	public static void main(String[] args) {
	
		
		//For Excel data 
		HashMap<String, Object> map = new HashMap<>();
		map.put("accuracy", "53");
		map.put("name", "KGF");
		map.put("phone_number", "85858585");
		map.put("address", "Carter Road");
		map.put("website", "www.google.com");
		map.put("language", "ENG_IN");
		
		HashMap<String, Object> map2 = new HashMap<>();
		map2.put("lat", "-38.35");
		map2.put("lng", "33.51");
		map.put("location", map2);
		
		String[] typesdata = {"Show Park","Shop"};
		map.put("types", typesdata);
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(map).when().post("maps/api/place/add/json")
		.then().log().all().statusCode(200).body("scope", equalTo("APP"))
		.header("server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		
		
	}
}
