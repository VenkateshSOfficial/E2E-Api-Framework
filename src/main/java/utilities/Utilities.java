package utilities;

import static io.restassured.RestAssured.requestSpecification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;


public class Utilities {
	private FileInputStream fis;
	private Properties prop;
	protected static RequestSpecification req; // Centralized static variable
	protected static String placeID;
	protected Response addPlaceApiResource;
	private APIResources apiResources;

	// Getter for placeID
	public String getPlaceID() {
		return placeID;
	}

	// Setter for placeID
	public void setPlaceID(String placeID) {
		Utilities.placeID = placeID;
	}

	// Read configuration file
	public void readConfigFile() throws IOException {
		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\global.properties");
		prop = new Properties();
		prop.load(fis);
	}

	// Fetch data from global.properties
	public String fetchDataFromProperties(String key) throws IOException {
		Properties properties = new Properties();
		String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\global.properties";

		try (FileInputStream fis = new FileInputStream(filePath)) {
			properties.load(fis);
		} catch (FileNotFoundException e) {
			throw new IOException("Properties file not found at path: " + filePath, e);
		}

		return properties.getProperty(key);
	}

	// Initialize shared RequestSpecification with lazy initialization
	public RequestSpecification requestSpecification() throws IOException {
		if (req == null) {
			req = new RequestSpecBuilder()
					.setBaseUri(fetchDataFromProperties("baseuri")) // Ensure base URI exists
					.addQueryParam(fetchDataFromProperties("key"), fetchDataFromProperties("value")) // Ensure both key and value exist
					.addHeader("Content-Type", "application/json") // Add Content-Type
					.addFilter(RequestLoggingFilter.logRequestTo(new PrintStream(Files.newOutputStream(Paths.get("src/main/java/logs/requestLogs.txt")))))
					.addFilter(ResponseLoggingFilter.logResponseTo(new PrintStream(Files.newOutputStream(Paths.get("src/main/java/logs/responseLogs.txt")))))
					.build();
		}
		return req;
	}

	public ResponseSpecification responseSpecification(int statusCode) {
		return new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(statusCode)
				.build();
	}

	// Perform HTTP requests using the shared RequestSpecification
	public Response performHttpRequestsCall(String resource, String httpMethodType) {
		// Ensure req is initialized
		if (req == null) {
			throw new RuntimeException("RequestSpecification is null. Initialize it before making the API call.");
		}

		// Resolve the resource endpoint from the APIResources enum
		apiResources = APIResources.valueOf(resource);
		System.out.println("Resolved API Resource Endpoint: " + apiResources.getResource());

		// Response object to store the API response
		Response response;

		// Determine the HTTP method
		switch (httpMethodType.toUpperCase()) {
			case "GET":
				response = req.when().get(apiResources.getResource());
				break;
			case "POST":
				response = req.when().post(apiResources.getResource());
				break;
			case "DELETE":
				response = req.when().delete(apiResources.getResource());
				break;
			case "PUT":
				response = req.when().put(apiResources.getResource());
				break;
			default:
				throw new UnsupportedOperationException("Unsupported HTTP method type: " + httpMethodType);
		}

		// Log status code and assign response
		System.out.println("Response Status Code: " + response.getStatusCode());
		addPlaceApiResource = response; // Important: Save the response in addPlaceApiResource if required
		return response;
	}
}
