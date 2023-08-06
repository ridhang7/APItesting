package com.qa.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasToString;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;

public class GetCallBDD {

	@Test
	public void getCallBdd() throws ClientProtocolException, IOException
	{
//		HashMap<String,String> requestHeaders = new HashMap<String,String>(); 
//		requestHeaders.put("HeaderKey", "HeaderValue");
		
		//Pass Header as keyValue
//		given().header("HeaderKey", "HeaderValue");
//		given().headers("HeaderKey", "HeaderValue");
		
		//Pass Header as HashMap
//		given().
//		headers(requestHeaders);
		
		//Pass header as Header class
//		Header requestHeaderOne = new Header ("HedaerKey","HeaderValue");
//		given().
//		header(requestHeaderOne);
		
		given().
		when().
			get("https://ergast.com/api/f1/drivers.json").
		then().
		assertThat().
		statusCode(200).and().
			body("MRData.total", hasToString("857"));
	}
	
}
