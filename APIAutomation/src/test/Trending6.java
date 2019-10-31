package test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

public class Trending6 {
	Logger log=Logger.getLogger("Hashtag");

	Properties prop;

  int a[]= {2295383,28218,23424977,23424852};
  
	@Test
	public void hashTag() throws IOException {
		prop=new Properties();
		PropertyConfigurator.configure("C:\\Users\\Online Test\\git\\api\\APIAutomation\\test-output\\log4j.properties");
		FileInputStream fis=new FileInputStream("C:\\Users\\Online Test\\git\\api\\APIAutomation\\src\\files\\data.properties");
		prop.load(fis);
		for(int i=0;i<a.length;i++) {
			RestAssured.baseURI=prop.getProperty("trends");
			Response res=given().auth().oauth(prop.getProperty("ConsumerKey"), prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret"))
					.queryParam("id", a[i]).when().get(Baseclass.Trendytweet()).then().extract().response();
			String response=res.asString();
			log.info(response);
			
		}
	}
}
