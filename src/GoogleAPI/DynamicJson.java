package GoogleAPI;
import io.restassured.RestAssured;
import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Random;

import org.apache.http.util.Asserts;
import org.junit.jupiter.api.Test;
public class DynamicJson {
	
	public static void addBook()
	{
		
	RestAssured.baseURI="https://rahulshettyacademy.com";
	Random rand = new Random();
	int upperbound = 2004;
	String response = given().log().all().header("Content-Type", "application/json")
			.body(payload.AddBook("Book1","isbn1",rand.nextInt(upperbound),"author1"))
			.when().post("/Library/Addbook.php")
			.then().log().all().assertThat().statusCode(200).extract().response().asString();
	
	System.out.println(response);
	JsonPath js = new JsonPath(response);
	String BookId = js.getString("ID");
	System.out.println(BookId);
	}
	
	
}
