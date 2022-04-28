package GoogleAPI;
import io.restassured.path.json.JsonPath;

public class JsonTests {

	public static void main(String[] args) {
		
		
		JsonPath js = new JsonPath(payload.MockResponse());
		//System.out.println("Number of courses are : "+js.getInt("courses.size()"));
		System.out.println("Number of courses are : "+js.getInt("courses.title.size()"));
		System.out.println("Purchase Amount is: "+js.getString("dashboard.purchaseAmount"));
		System.out.println("Title of the first course is :"+js.getString("courses[0].title"));
			
		int AmountSum = 0;
		System.out.println("Course Title  "+"   "+"Course Amount");
		for( int i = 0; i< js.getInt("courses.size()") ;i++) {
			System.out.println((js.getString("courses["+i+"].title"))+"  "+(js.getString("courses["+i+"].price")));
			String courseDetail = (js.getString("courses["+i+"].title"));
			
			AmountSum = AmountSum + js.getInt("courses["+i+"].price");
			
			//System.out.println(AmountSum+" "+js.getInt("dashboard.purchaseAmount"));
			
			if(courseDetail.equals("RPA")) {
			System.out.println("Number of copies sold for RPA are : "+js.getString("courses["+i+"].copies"));
		}
			
		}
		}

}
