package com.qa.base;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {
	public Properties prop;
	public int responseStatusCode_200 = 200;
	public int responseStatusCode_201 = 201;
	public int responseStatusCode_500 = 500;
	public int responseStatusCode_400 = 400;
	public int responseStatusCode_401 = 401;
	public int responseStatusCode_404 = 404;
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
			prop.load(ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
