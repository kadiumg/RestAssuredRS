package Serialization_Deserialization;

import io.restassured.RestAssured;
import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.http.util.Asserts;
import org.junit.jupiter.api.Test;
import org.testng.Assert;


public class PojoResponse {
	public static void main(String[] args) {
		
		String url = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F0AX4XfWhtuerxXIPa4xhDT-Sl_xvFokjrzuV0xmHRL5PiuzFqFFGV0rzfRyC-VakQcgZkHA&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
		String partialcode=url.split("code=")[1];
		String code=partialcode.split("&scope")[0];
				
		//RestAssured.baseURI = "https://www.googleapis.com/oauth2/v4/token";
		String Response = given().urlEncodingEnabled(false).log().all().queryParams("code", code)
								.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
								.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
								.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
								.queryParams("grant_type","authorization_code")
								.queryParams("scope","https://www.googleapis.com/auth/userinfo.email")
								.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js = new JsonPath(Response);
		String accessCode = js.getString("access_token");
		System.out.println(accessCode);
		
		//defaultParser(Parser.JSON) is required because in the header it is mentioned as html/text
		GetCourse gc = given().queryParam("access_token",accessCode).expect().defaultParser(Parser.JSON).when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);

		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		System.out.println(gc.getExpertise());
		System.out.println(gc.getCourses().getWebAutomation().get(2).getPrice());

		
		//Print all the CourseTitles and Price details in WebAutomation
		List<webAutomation> WA = gc.getCourses().getWebAutomation();
		for(int i=0; i < WA.size(); i++) {
			System.out.println(gc.getCourses().getWebAutomation().get(i).getCourseTitle()+"     "+gc.getCourses().getWebAutomation().get(i).getPrice());
		}
		
		//Get the price of SoapUI Webservices testing 
		List<api> apiCourses=gc.getCourses().getApi();
		for(int i=0;i<apiCourses.size();i++)
		{
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
					{
				System.out.println(apiCourses.get(i).getPrice());
					}
		}
		
		//Get the course names of WebAutomation, ## Need to understand the code ##
		ArrayList<String> a= new ArrayList<String>();		
		String[] courseTitles= { "Selenium Webdriver Java","Cypress","Protractor"};
		List<webAutomation> w=gc.getCourses().getWebAutomation();
		
		for(int j=0;j<w.size();j++)
		{
			a.add(w.get(j).getCourseTitle());
		}
		
		List<String> expectedList=	Arrays.asList(courseTitles);
		Assert.assertTrue(a.equals(expectedList));
			
		
	}
	
}


