Feature: validate add place API
  Scenario Outline: verify if place is being successfully added using AddPlaceAPI
    Given add place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then user validate the status code to be "200"
    And user validates the "status" to be "OK"
    Examples:
      | name     | language | address            |
      | AA house | Tamil    | pillar koil street |

