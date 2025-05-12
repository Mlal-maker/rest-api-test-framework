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

import api.endpoints.UserEndPoints2;
import api.payload.User;
import api.utilities.AllureRestAssuredFilter;
import api.utilities.RetryAnalyzer;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserTests2 {
	
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
	
	@Test(priority=1)
	@Description("creating user")
	public void testPostUser() {
		
		logger.info("********* Creating User   **********");
		
		Response response=UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********* User is created  **********");
		
	}
	@Test(priority=2)
	@Description("reading user")
	@Flaky
	public void testGetUserByName() {
		
		logger.info("********* Reading User Info   **********");
		
		Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********* User Info is displayed   **********");
	}
	@Test(priority=3)
	@Description("updating user")
	public void testUpdateUserByName() {
		
		logger.info("********* Updating User   **********");
		
		//update data using payload
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints2.updateUser(userPayload, this.userPayload.getUsername());
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		//Checking data after update
		
		Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********* User is updated   **********");
		
	}
	@Test(priority=4)
	@Description("Deleting user")
	public void testDeleteUserByName() {
		
		logger.info("********* Deleting User   **********");
		
		Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*********  User is deleted   **********");
	}

}
