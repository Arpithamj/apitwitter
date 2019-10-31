package test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Usertweets5 {
	Properties prop;
	Logger log=Logger.getLogger("listusers");


	
	@Test
	public void UsernameTweets() throws IOException {
		prop=new Properties();
		PropertyConfigurator.configure("C:\\Users\\Online Test\\git\\api\\APIAutomation\\test-output\\log4j.properties");
		FileInputStream fis=new FileInputStream("C:\\Users\\Online Test\\git\\api\\APIAutomation\\src\\files\\data.properties");
		prop.load(fis);
		RestAssured.baseURI=prop.getProperty("status");
		Response response=given().auth().oauth(prop.getProperty("ConsumerKey"), prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret")).queryParam("screen name","Pranesh Dixit").
		when().get(Baseclass.TWeets()).then().extract().response();
		String res=response.asString();
		log.info(res);
		System.out.println(res);
	}
	

}
