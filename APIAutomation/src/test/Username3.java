package test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Username3 {
	Properties prop;
	Logger log=Logger.getLogger("Username");


	@Test
	public void userName() throws IOException {
		prop=new Properties();
		PropertyConfigurator.configure("C:\\Users\\Online Test\\git\\api\\APIAutomation\\test-output\\log4j.properties");
		FileInputStream fis=new FileInputStream("C:\\Users\\Online Test\\git\\api\\APIAutomation\\src\\files\\data.properties");
		prop.load(fis);
		RestAssured.baseURI=prop.getProperty("search");
		Response res=given().auth().oauth(prop.getProperty("ConsumerKey"), prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret"))
				.param("q", "Qualitest").
				when().get(Baseclass.Resourceretweet()).then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
	            .extract().response();
		String response=res.asString();
		log.info(response);
		JsonPath js=new JsonPath(response);
		int count=js.get("statuses.size()");
		System.out.println(count);
		for(int i=0;i<count;i++) {
			String Username=js.getString("statuses["+i+"].user.screen_name");
			System.out.println("Name - "+Username);
		}
	}
	
	
}
