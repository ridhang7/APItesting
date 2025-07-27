package api.test;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endPoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;


public class UserTestUsingExcel {
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus) {
		
		User payload = new User();
		payload.setId(Integer.parseInt(id));
		payload.setUsername(username);
		payload.setFirstName(firstName);
		payload.setLastName(lastName);
		payload.setEmail(email);
		payload.setPassword(password);
		payload.setPhone(phone);
		payload.setUserStatus(Integer.parseInt(userStatus));
		
//		Response res = UserEndPoints.createUser();
	
	
	}
}
