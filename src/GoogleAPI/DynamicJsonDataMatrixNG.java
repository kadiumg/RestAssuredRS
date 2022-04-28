package GoogleAPI;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import java.util.Random;
import org.testng.annotations.DataProvider;


public class DynamicJsonDataMatrixNG {
  @Test(dataProvider = "BooksData")
  public static void addBook(String BookName, String isbn, String author)
	{
		
	RestAssured.baseURI="https://rahulshettyacademy.com";
	Random rand = new Random();
	int upperbound = 2004;
	String response = given().log().all().header("Content-Type", "application/json")
			.body(payload.AddBook(BookName,isbn,rand.nextInt(upperbound),author))
			.when().post("/Library/Addbook.php")
			.then().log().all().assertThat().statusCode(200).extract().response().asString();
	
	System.out.println(response);
	JsonPath js = new JsonPath(response);
	String BookId = js.getString("ID");
	System.out.println(BookId);
	}
	

 @DataProvider(name="BooksData")
 public Object[][] getData() 
 {
	return new Object[][] {{"Book1","1234","Venky"},
		{"Book2","2345","Anish"},
		{"Book3","3456","Mitra"},
		{"Book4","4567","Gokul"},
		
	}; 
	 
	 
 }
  }

