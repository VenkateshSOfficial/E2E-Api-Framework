package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;


public class Utilities {
	FileInputStream fis;
	public Properties prop;

	public void readConfigFile() throws IOException{
		fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\resources\\global.properties");
		prop = new Properties();
		prop.load(fis);
	}
	public String fetchDataFromProperties(String key) throws IOException {
		Properties properties = new Properties();
		// Correct file path
		String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\global.properties";
		// Log the file path for debugging
//		System.out.println("Loading properties file from: " + filePath);
		try (FileInputStream fis = new FileInputStream(filePath)) {
			properties.load(fis);
		} catch (FileNotFoundException e) {
			throw new IOException("Properties file not found at path: " + filePath, e);
		}
		// Fetch and return value
		return properties.getProperty(key);
	}
	public RequestSpecification requestSpecification() throws IOException {
		return new RequestSpecBuilder()
				.setBaseUri(fetchDataFromProperties("baseuri"))
				.addQueryParam(fetchDataFromProperties("key"),fetchDataFromProperties("value"))
				.addHeader("Content-Type", "application/json")
				.addFilter(RequestLoggingFilter.logRequestTo(new PrintStream(Files.newOutputStream(Paths.get("src/main/java/logs/requestLogs.txt")))))
				.addFilter(ResponseLoggingFilter.logResponseTo(new PrintStream(Files.newOutputStream(Paths.get("src/main/java/logs/responseLogs.txt")))))
				.build();
	}

	public ResponseSpecification responseSpecification(int statusCode){
		return new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(statusCode)
				.build();
	}
}
