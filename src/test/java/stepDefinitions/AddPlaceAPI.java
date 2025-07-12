package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import pojo.addPlacePojo.AddPlaceResponse;
import resources.APIResources;
import testData.TestDataBuild;
import utilities.Utilities;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;


public class AddPlaceAPI extends Utilities {
    private ValidatableResponse validatableResponse;
    private AddPlaceResponse addPlaceResponse;
    private String placeId;
    private String status;

    @Given("add place payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        // Use centralized "req" variable from Utilities
        req = given()
              .spec(requestSpecification())
              .body(TestDataBuild.addPlacePayload(name, language, address));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String httpMethodType) {
        performHttpRequestsCall(resource, httpMethodType); // Delegates work to Utilities
    }

    @Then("user validate the status code to be {string}")
    public void user_validate_the_status_code_to_be(String statusCode) {
        validatableResponse = addPlaceApiResource.then().spec(responseSpecification(Integer.parseInt(statusCode)));
    }

    @Then("user validates the {string} to be {string}")
    public void user_validates_the_to_be(String key, String expectedValue) {
        addPlaceResponse = validatableResponse.extract().response().as(AddPlaceResponse.class);
        placeId = addPlaceResponse.getPlace_id(); // Extract place ID
        setPlaceID(placeId); // Save place ID
        System.out.println("The place id is : " + placeId);

        status = addPlaceResponse.getStatus(); // Extract status
        Assert.assertEquals(expectedValue, status); // Assert status matches expected
    }
}