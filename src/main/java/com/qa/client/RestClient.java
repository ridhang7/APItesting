package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class RestClient {
	
	// 1. Get Method without Headers
	public CloseableHttpResponse get(String url) throws ParseException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse closeableHttpResponse = null;
		HttpGet httpget = new HttpGet(url); //http get request
		closeableHttpResponse =  httpClient.execute(httpget); //Hit the GET URL
		
	return closeableHttpResponse;
	}
	// 2. Get Method with headers
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ParseException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse closeableHttpResponse = null;
		HttpGet httpget = new HttpGet(url); //http get request
		// iterate header map for header key & values
		for(Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		closeableHttpResponse =  httpClient.execute(httpget); //Hit the GET URL
	return closeableHttpResponse;
	}
	
	// 3. Post Method
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse closeableHttpResponse = null;
		HttpPost httppost = new HttpPost(url); // http post request
		httppost.setEntity(new StringEntity(entityString)); // payload
		// iterate header map for header key & values
		for(Map.Entry<String, String> entry : headerMap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		closeableHttpResponse =  httpClient.execute(httppost); //Hit the POST URL
		return closeableHttpResponse;
	}
}


