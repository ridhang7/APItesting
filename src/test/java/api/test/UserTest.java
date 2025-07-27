package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endPoints.UserEndPoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User payload;
	
	@BeforeClass
	public void setupData() {
		
		faker = new Faker();
		payload = new User();
		
		payload.setId(0);
		payload.setUsername(faker.name().username().toString());
		payload.setFirstName(faker.name().firstName().toString());
		payload.setLastName(faker.name().lastName().toString());
		payload.setEmail(faker.internet().safeEmailAddress());
		payload.setPassword(faker.internet().password());
		payload.setPhone(faker.phoneNumber().toString());
		
		System.out.println(payload.getUsername());
	}
	@Test(priority = 1)
	public void testPostUser() {
		System.out.println(payload.getUsername());
		Response res = UserEndPoints.createUser(payload);
		res.then().log().all();
		Assert.assertEquals(res.statusCode(), 200);
	}
	
	@Test(priority = 2)
	public void testGetUserByName() throws InterruptedException {
		System.out.println(payload.getUsername());
		Response res = UserEndPoints.readUser(payload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.statusCode(), 200);
	}
	
	@Test(priority = 3)
	public void testUpdateUser() {
		System.out.println(payload.getUsername());
		System.out.println(payload.getEmail());
		payload.setEmail(faker.internet().safeEmailAddress());
		System.out.println(payload.getEmail());
		Response res = UserEndPoints.updateUser(payload.getUsername(), payload);
		res.then().log().all();
		Assert.assertEquals(res.statusCode(), 200);
	}
	
	@Test(priority = 4)
	public void testDeleteUser() {	
		System.out.println(payload.getUsername());		
		Response res = UserEndPoints.deleteUser(payload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.statusCode(), 200);
	}
	
}
