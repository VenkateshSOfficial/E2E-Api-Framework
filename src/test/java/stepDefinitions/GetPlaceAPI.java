package stepDefinitions;

import io.cucumber.java.en.*;
import utilities.Utilities;


public class GetPlaceAPI extends Utilities {
	private String placeID;
	@Given("user fetches the place id from the response received from AddPlaceAPI")
	public void user_fetches_the_place_id_from_the_response_received_from_add_place_api() {
		placeID=getPlaceID();
	}
	@When("user send {string} and {string} http request")
	public void user_send_and_http_request(String resource, String httpMethodType) {
		performHttpRequestsCall(resource, httpMethodType);
	}
	@Then("user validates the status code {string}")
	public void user_validates_the_status_code(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Then("user validates name {string}")
	public void user_validates_name(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
}
