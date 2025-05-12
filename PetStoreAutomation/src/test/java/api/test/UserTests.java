package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.AllureRestAssuredFilter;
import io.qameta.allure.Allure;
import io.qameta.allure.Flaky;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		
		RestAssured.filters(new AllureRestAssuredFilter());
		
	}
	
	@Test(priority=1,description = "Verify that user creation is successful")
	public void testPostUser() {
		
		logger.info("********* Creating User   **********");
		
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		//Allure.addAttachment("API Response", "application/json", response.getBody().asPrettyString(),",json");
		
		logger.info("********* User is created  **********");
		
	}
	@Test(priority=2,description = "Reading user info")
	public void testGetUserByName() {
		
		logger.info("********* Reading User Info   **********");
		
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		//Allure.addAttachment("API Response", "application/json", response.getBody().asPrettyString(),",json");
		
		logger.info("********* User Info is displayed   **********");
	}
	@Test(priority=3,description = "Update user sceanrio")
	public void testUpdateUserByName() {
		
		logger.info("********* Updating User   **********");
		
		//update data using payload
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints.updateUser(userPayload, this.userPayload.getUsername());
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		//Checking data after update
		
		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		//Allure.addAttachment("API Response", "application/json", response.asPrettyString(),",json");
		
		logger.info("********* User is updated   **********");
		
	}
	@Test(priority=4,description = "Delete user is successful")
	@Flaky
	public void testDeleteUserByName() {
		
		logger.info("********* Deleting User   **********");
		
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
	//	Allure.addAttachment("API Response", "application/json", response.asPrettyString(),",json");
		logger.info("*********  User is deleted   **********");
	}

}
