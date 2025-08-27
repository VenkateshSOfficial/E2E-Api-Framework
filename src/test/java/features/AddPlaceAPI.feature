Feature: validate place API's
  @@AddPlace
  Scenario Outline: verify if place is being successfully added using AddPlaceAPI
    Given add place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then user validate the status code to be "200"
    And user validates the "status" to be "OK"
    Examples:
      | name     | language | address            |
      | AA house | Tamil    | pillar koil street |

  @GetPlace
  Scenario Outline:validate if get place api can fetch the response
    Given user fetches the place id from the response received from AddPlaceAPI
    When user send "GetPlaceAPI" and "GET" http request
    Then user validates the status code "200"
    And user validates name "<name>"
    Examples:
      | name     |
      | AA house |
      | BB house |

