package Serialization;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.http.util.Asserts;
import org.junit.jupiter.api.Test;
import org.testng.Assert;


public class SpecBuilderPojoReq {

	public static void main(String[] args) {
	AddPlace p = new AddPlace();
	p.setAccuracy(50);
	p.setAddress("985, main layout, cohen 09");
	p.setLanguage("French-IN");
	p.setName("Front line house");
	p.setPhone_number("(+91) 983 893 8574");
	p.setWebsite("http://google.com");
	
	List<String> listnew = new ArrayList<String>();
	listnew.add("shoe park");
	listnew.add("shop");	
	p.setTypes(listnew);

	Location l = new Location();
	l.setLat(-19.383494);
	l.setLng(22.427322);
	p.setLocation(l);
	
	RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
						.setContentType(ContentType.JSON).build();
	
	RequestSpecification req1 = given().spec(req).body(p); 

	ResponseSpecification resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		Response response = req1.log().all().when().post("/maps/api/place/add/json")
							.then().log().all().spec(resp).extract().response();
		
		String responseString = response.asString();
		
		JsonPath js = new JsonPath(responseString);
		System.out.println(js.getString("place_id"));
		}

}
