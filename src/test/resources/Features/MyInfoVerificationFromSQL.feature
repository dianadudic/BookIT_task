Feature: As a user I should able to see my info

  @myinfo
  Scenario: User should able to see his/her info
    Given user is on the login page and logs in
    Then user hover over to the my button and select self button
    Then user sees myInfo