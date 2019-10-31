package test;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//import files.Payload;
//import files.resources;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Create1 {
	Logger log=Logger.getLogger("create");
	Properties prop=new Properties();
	
	@BeforeTest
	public void tweet() throws IOException {
		PropertyConfigurator.configure("C:\\Users\\Online Test\\git\\api\\APIAutomation\\test-output\\log4j.properties");
	FileInputStream fis=new FileInputStream("C:\\Users\\Online Test\\git\\api\\APIAutomation\\src\\files\\data.properties");
	prop.load(fis);
	}

@Test
public void createTweet() throws IOException {
	
	RestAssured.baseURI=prop.getProperty("status");
	Response res=given().auth().oauth(prop.getProperty("ConsumerKey"), prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret")).
    queryParam("status","I am learning API testing,#Qualitest1").
    when().post(Baseclass.postResource()).then().extract().response();

String response=res.asString();
System.out.println(response);
log.info(response);
JsonPath js = new JsonPath(response);
String id=js.get("id").toString();
log.info(id);
System.out.println(id);
String text=js.get("text").toString();
log.info(text);
System.out.println(text);
}

}
