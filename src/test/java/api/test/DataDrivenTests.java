package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	@Test(priority=1,dataProvider = "Data",dataProviderClass = DataProviders.class)
	public void testPostUser(String userIds, String userName, String
			fName, String lastname, String email, String pwd, String phone) {
		
		User userPayload =  User.builder()
								.id(Integer.parseInt(userIds))
								.username(userName)
								.firstName(fName)
								.lastName(lastname)
								.email(email)
								.password(pwd)
								.phone(phone).build();
		
		
				
		Response response=UserEndPoints.createUser(userPayload);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
//	@Test(priority=2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
//	public void testDeleteuserByName(String userName) {
//		
//		Response response = UserEndPoints.deleteUser(userName);
//		Assert.assertEquals(response.getStatusCode(), 200);
//		
//	}

}
