package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import pojo.addPlacePojo.AddPlaceResponse;
import testData.TestDataBuild;
import utilities.Utilities;

import java.io.IOException;

import static io.restassured.RestAssured.given;


public class AddPlaceAPI extends Utilities {
    RequestSpecification requestSpecification;
    Response addPlaceApiResource;
    ValidatableResponse validatableResponse;
    AddPlaceResponse addPlaceResponse;

    String placeId;
    String status;

    @Given("add place payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        requestSpecification = given()
                .spec(requestSpecification())
                .body(TestDataBuild.addPlacePayload(name,language,address));
    }
    /*@Given("add place payload")
    public void add_place_payload() throws IOException {
        requestSpecification = given().spec(requestSpecification()).body(TestDataBuild.addPlacePayload());
    }*/
    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String string, String string2) throws IOException {
        addPlaceApiResource = requestSpecification.when().post(fetchDataFromProperties("addPlaceApiResource"));
    }
    @Then("user validate the status code to be {string}")
    public void user_validate_the_status_code_to_be(String string) {
        validatableResponse = addPlaceApiResource.then().spec(responseSpecification(200));
    }
    @Then("user validates the {string} to be {string}")
    public void user_validates_the_to_be(String string, String string2) {
        addPlaceResponse = validatableResponse.extract().response().as(AddPlaceResponse.class);
        placeId = addPlaceResponse.getPlace_id();
        System.out.println("The place id is : " + placeId);
        status = addPlaceResponse.getStatus();
        Assert.assertEquals("OK", status);
    }
}
