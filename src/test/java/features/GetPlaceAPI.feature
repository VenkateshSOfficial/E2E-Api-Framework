Feature: validate get place API
  Scenario Outline:validate if get place api can fetch the response
    Given user fetches the place id from the response received from AddPlaceAPI
    When user send "GetPlaceAPI" and "GET" http request
    Then user validates the status code "200"
    And user validates name "<name>"
    Examples:
      | name     |
      | AA house |