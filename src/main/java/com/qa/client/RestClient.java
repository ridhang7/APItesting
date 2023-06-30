package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	public CloseableHttpResponse get(String url) throws ParseException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse closeableHttpResponse = null;
		HttpGet httpget = new HttpGet(url); //http get request
		closeableHttpResponse =  httpClient.execute(httpget); //Hit the GET URL
		
	return closeableHttpResponse;
	}
}


