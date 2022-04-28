package GoogleAPI;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;

//import RA_Jira.CookieAuth;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class JiraTestRef {

	public static void main(String[] args) throws IOException {
	
//		PersonalTester CA = new PersonalTester();
//		String sessionId = CA.getSessionId("gokul.kadium", "Shiva@2021");
//		//attachDocToIssue(sessionId);
		//DynamicJSonFilePass.addBook();	
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String getCourse = given().queryParam("accessToken", "ya29.a0ARrdaM_T5OytiUGT5iIgHCMezFw91NrUNlTt64n3qGN_VDyYAZ1ImFdQkFyX3bjRQW38W7YUYTYH5lZ8ruD0OH5liqcPPxpcXamhuAZbVNnokr0mocVG-0NFFimTJ-3lowXimRwuqJYETE7g6MvZ2KuTNmj1gQ")
				.when().post("/getCourse.php")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println("_______________________________________");
		System.out.println(getCourse);
	
	
	}

	public static void attachDocToIssue(String sessionId) {
		
		RestAssured.baseURI = "http://localhost:8080";
		given().log().all().header("X-Atlassian-Token","no-check").header("Cookie", "JSESSIONID="+sessionId+"").pathParam("key","10100")
		.header("Content-Type","multipart/form-data").multiPart("file",new File("C:\\Users\\Admin\\Desktop\\Gokul\\Working_Req\\RestAssuredTestProject4.3\\src\\GKAttachment.txt")).when()
		.post("/rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
	}
		
public String getSessionId(String Username, String Password) {
		
		
		RestAssured.baseURI= "http://localhost:8080";
		String response = given().header("content-type","application/json")
		.body("{ \"username\": \""+Username+"\", \"password\": \""+Password+"\" }").when()
		.post("/rest/auth/1/session").then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath JS = new JsonPath(response);
		String SessionId=JS.getString("session.value");
		System.out.println("SessionId Generated successfully :"+SessionId);
				return SessionId;
		
		//Second Approach of using Session filter
//				RestAssured.baseURI= "http://localhost:8080";
//				SessionFilter sess=new SessionFilter(); 
//				String response = given().log().all().header("content-type","application/json")
//				.body("{ \"username\": \""+Username+"\", \"password\": \""+Password+"\" }").filter(sess).when()
//				.post("/rest/auth/1/session").then().log().all().assertThat().statusCode(200).extract().response().asString();
//				
//				System.out.println(response);
//						return sess;
//				
}

	

}
