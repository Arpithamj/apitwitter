package test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Search2 {
	Properties prop;
	Logger log=Logger.getLogger("search");

	@Test
	public void getTweet() throws IOException {
		prop=new Properties();
		PropertyConfigurator.configure("C:\\Users\\Online Test\\git\\api\\APIAutomation\\test-output\\log4j.properties");
		FileInputStream fis=new FileInputStream("C:\\Users\\Online Test\\git\\api\\APIAutomation\\src\\files\\data.properties");
		prop.load(fis);
		RestAssured.baseURI=prop.getProperty("search");
		Response res=given().auth().oauth(prop.getProperty("ConsumerKey"), prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret")).
				queryParam("q","#Qualitest").queryParam("count",100).
	    when().get(Baseclass.Resourceretweet()).then().extract().response();

	String response=res.asString();
	log.info(response);
	System.out.println(response);
	JsonPath js = new JsonPath(response);
	}
}
