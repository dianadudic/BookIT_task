Feature: As a devOps team member I should able to see all my members

  @team
  Scenario: As a user I should able to see my team member
    Given User is on dashboard
    Then user hover over to the my button and select team button
    Then user sees team members