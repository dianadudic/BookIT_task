Feature: user login to BookIT


  @login
  Scenario Outline: User should able to login to BookIT App
    Given user is on the login page
    When user enters "<username>" and "<password>"
    Then user click the login button
    Then user on the "<Dashboard>"
    Examples:
      | username              | password   | Dashboard |
      | wfarrell8n@usnews.com | doniafisby | map       |