package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase{
	TestBase testBase;
	CloseableHttpResponse closeableHttpResponse;
	RestClient restClient;
	String serviceURL;
	String apiURL;
	String url;
	HashMap<String, String> headerMap;
	
	String per_pageCount;
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
	
	@Test (priority=1)
	public void getAPITestWithoutHeaders() throws ParseException, IOException {
		restClient = new RestClient();
		CloseableHttpResponse closeableHttpResponse = restClient.get(url);
		
		// Status code assertion method
		statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ----> "+statusCode);
		Assert.assertEquals(statusCode, responseStatusCode_200, "Status code is not "+responseStatusCode_200+". Status code is: " + statusCode);
		
		// Json response
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response Json from API --->"+responseJson);
		
		//Json response Assertion
		per_pageCount = TestUtil.getValueByJPath(responseJson, "/per_page");
		Assert.assertEquals(per_pageCount, "6", "per page count is not 6. per page value is: " + per_pageCount);	
		
		// Get All headers message
		Header[] headerMessageArray = closeableHttpResponse.getAllHeaders();
		HashMap<String,String> allHeader = new HashMap<String, String>();
		for (Header header : headerMessageArray) 
		{
			allHeader.put(header.getName(), header.getValue());
		}
		System.out.println("Response from header --->"+allHeader);
	}
	
	@Test (priority=2)
	public void getAPITestWithHeaders() throws ParseException, IOException {
		restClient = new RestClient();
		CloseableHttpResponse closeableHttpResponse = restClient.get(url, headerMap);

		// Status code assertion method
		statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ----> "+statusCode);
		Assert.assertEquals(statusCode, responseStatusCode_200, "Status code is not "+responseStatusCode_200+". Status code is: " + statusCode);
		
		// Json response
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response Json from API --->"+responseJson);
		
		//Json response Assertion
		per_pageCount = TestUtil.getValueByJPath(responseJson, "/per_page");
		Assert.assertEquals(per_pageCount, "6", "per page count is not 6. per page value is: " + per_pageCount);	
		
		// Get All headers message
		Header[] headerMessageArray = closeableHttpResponse.getAllHeaders();
		HashMap<String,String> allHeader = new HashMap<String, String>();
		for (Header header : headerMessageArray) 
		{
			allHeader.put(header.getName(), header.getValue());
		}
		System.out.println("Response from header --->"+allHeader);
		
	}
}
