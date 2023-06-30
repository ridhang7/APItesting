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
	
	
	@BeforeMethod
	public void setUp(){
		testBase = new TestBase();
		serviceURL = prop.getProperty("URL");
		apiURL = prop.getProperty("apiURL");
		url = serviceURL + apiURL;
		
	}
	
	@Test
	public void getAPITest() throws ParseException, IOException {
		restClient = new RestClient();
		CloseableHttpResponse closeableHttpResponse = restClient.get(url);
		
		// Status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ----> "+statusCode);
		
		//Status Code Assertion
		Assert.assertEquals(statusCode, responseStatusCode_200, "Status code is not 200. Status code is: " + statusCode);
		
		// Json response
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response Json from API --->"+responseJson);
		
		//Json response Assertion
		System.out.println(TestUtil.getValueByJPath(responseJson, "/data[0]/last_name"));	
		
		// All headers
		Header[] headerMessageArray = closeableHttpResponse.getAllHeaders();
		HashMap<String,String> allHeader = new HashMap<String, String>();
		for (Header header : headerMessageArray) 
		{
			allHeader.put(header.getName(), header.getValue());
		}
		System.out.println("Response from header --->"+allHeader);
	} 
}
