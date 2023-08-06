package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase{
	TestBase testBase;
	CloseableHttpResponse closeableHttpResponse;
	RestClient restClient;
	String serviceURL;
	String apiURL;
	String url;
	HashMap<String, String> headerMap;
	String entityString;
	int statusCode;

	@BeforeMethod
	public void setUp(){
		testBase = new TestBase();
		serviceURL = prop.getProperty("URL");
		apiURL = prop.getProperty("apiURL");
		url = serviceURL + apiURL;
		headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type","application/json");
		
	}
	
	@Test
	public void postApiTest() throws ClientProtocolException, IOException
	{
		restClient = new RestClient();
		
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("Morpheus","Leader");
		
		//Object to Json file
		mapper.writeValue(new File("C:\\Users\\ridha\\eclipse-workspace\\restapi\\src\\main\\java\\com\\qa\\data\\user.json"), users);
		
		//Object to Json in string
		entityString = mapper.writeValueAsString(users);
		System.out.println(entityString);

		CloseableHttpResponse closeableHttpResponse = restClient.post(url, entityString, headerMap);
		
		// Status code assertion method
		statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ----> "+statusCode);
		Assert.assertEquals(statusCode, responseStatusCode_201, "Status code is not "+responseStatusCode_201+". Status code is: " + statusCode);
		
		// Json response
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response Json from API --->"+responseJson);
		
		//Json to Java object
		Users userRespObj = mapper.readValue(responseString, Users.class);
		System.out.println(userRespObj);
		
		Assert.assertEquals(userRespObj.getName(), users.getName(), "Name is not"+users.getName()+". Name in response is" + users.getName());
		Assert.assertEquals(userRespObj.getJob(), users.getJob(), "Name is not"+users.getJob()+". Name in response is" + users.getJob());
		
		System.out.println("Response name is ---->"+userRespObj.getName());
		System.out.println("Response job is ---->"+userRespObj.getJob());
	}
}
