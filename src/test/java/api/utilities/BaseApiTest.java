package api.utilities;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class BaseApiTest {
	 @BeforeClass
	    public void setup() {
	        RestAssured.filters(new AllureRestAssuredFilter()); // Set our custom filter
	       // RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	    }

}
