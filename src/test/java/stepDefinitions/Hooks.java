package stepDefinitions;


import io.cucumber.java.Before;
import utilities.Utilities;

import java.io.IOException;

public class Hooks extends Utilities {
    String placeID;
    @Before("@AddPlace")
    public void beforeScenario() throws IOException {
        if (placeID == null) {
            AddPlaceAPI addPlace = new AddPlaceAPI()
                    .add_place_payload_with("Kaushi", "English", "ASIA")
                    .user_calls_with_http_request("AddPlaceAPI", "POST");

            // 1. Validate status code (this sets validatableResponse)
            addPlace.user_validate_the_status_code_to_be("200");

            // 2. Now it's safe to extract place_id and assert status
            String placeId = addPlace.user_validates_the_to_be("status", "OK");

            // 3. Store it
            setPlaceID(placeId);
        }

        System.out.println("The place ID value is : " + getPlaceID());
    }
}
