package api.endpoints;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Created UserEndpoint class to perform create, retrieve, update and delete request to the User API

public class UserEndPoints2 {
	
	static ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;	
	}
	
	public static Response createUser(User payload) {
		
		String post_url = getURL().getString("post_url");
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url);
		
		return response;
			
		
	}
	
public static Response readUser(String userName) {
	
	String get_url = getURL().getString("get_url");
		
		Response response = given()
				.pathParam("username", userName)
			
		.when()
			.get(get_url);
		
		return response;
			
		
	}

public static Response updateUser(User payload,String userName) {
	
	String update_url = getURL().getString("update_url");
	
	Response response = given()
		.pathParam("username", userName)
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
	.when()
		.put(update_url);
	
	return response;
		
	
}

public static Response deleteUser(String userName) {
	
	String delete_url = getURL().getString("delete_url");
	
	Response response = given()
			.pathParam("username", userName)
		
	.when()
		.delete(delete_url);
	
	return response;
		
	
}

	

}
