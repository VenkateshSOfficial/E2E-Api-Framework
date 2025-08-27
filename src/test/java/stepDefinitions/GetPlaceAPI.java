package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.Utilities;

public class GetPlaceAPI extends Utilities {

	private Response response; // This will hold the API response
	private String placeID;    // Hold the placeID received from AddPlaceAPI

	/**
	 * Step: Fetch the Place ID from the AddPlaceAPI response
	 */
	@Given("user fetches the place id from the response received from AddPlaceAPI")
	public void user_fetches_the_place_id_from_the_response_received_from_add_place_api() {
		// Retrieve stored Place ID from a shared utility (assuming `getPlaceID` is implemented in Utilities)
		placeID = getPlaceID();
		System.out.println("Fetched Place ID: " + placeID);

		// Attach the placeID as a query parameter to the request (if needed for GET requests)
		req = req.queryParam("place_id", placeID);
	}

	/**
	 * Step: Send the GetPlaceAPI request using resource + HTTP method
	 */
	@When("user send {string} and {string} http request")
	public void user_send_and_http_request(String resource, String httpMethodType) {
		// Perform the HTTP request and store the response
		response = performHttpRequestsCall(resource, httpMethodType);

		// Ensure the response is not null
		if (response == null) {
			throw new RuntimeException("API response is null. Ensure the request was properly executed.");
		}

		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asString());
	}

	/**
	 * Step: Validate the status code of the API response
	 */
	@Then("user validates the status code {string}")
	public void user_validates_the_status_code(String expectedStatusCode) {
		// Extract status code from the Response
		int actualStatusCode = response.getStatusCode();
		int expectedStatus = Integer.parseInt(expectedStatusCode);

		// Validate the status code
		Assert.assertEquals("Status code mismatch!", expectedStatus, actualStatusCode);
		System.out.println("Status code validated successfully: " + actualStatusCode);
	}

	/**
	 * Step: Validate the name in the API response
	 */
	@Then("user validates name {string}")
	public void user_validates_name(String expectedName) {
		// Extract the name field from the JSON response body
		String actualName = response.jsonPath().getString("name");

		// Validate the name against the expected value
		Assert.assertEquals("Name mismatch!", expectedName, actualName);
		System.out.println("Validated name from API response: " + actualName);
	}
}