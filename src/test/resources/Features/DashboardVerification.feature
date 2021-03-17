Feature: As a user, I should be able to see list off all rooms on Dashboard

  @dashboard
  Scenario: User can see list of all rooms
    Given User is on dashboard
    Then User can see all rooms
      | ocean view    |
      | kuzzat's cave |
      | 4stay         |
      | study area    |
      | kitchen       |
      | lobby         |
      | kilimanjaro   |
      | half dome     |
      | denali        |
      | meru          |







