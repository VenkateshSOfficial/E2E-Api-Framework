Feature: validate places API
  Scenario Outline: verify if place is being successfully added using AddPlaceAPI
    Given add place payload with "<name>" "<language>" "<address>"
    When user calls "<API name>" with "<HTTP request>" http request
    Then user validate the status code to be "200"
    And user validates the "status" to be "OK"
    Examples:
      | name     |  | language | address            | API name    | HTTP request |
      | AA house |  | Tamil    | pillar koil street | AddPlaceAPI | POST         |
      | BB house |  | English  | WTC street         | AddPlaceAPI | POST         |

