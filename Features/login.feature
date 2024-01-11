Feature: Checking the login with invalid credentials

  @sanity
  Scenario: Login scenario
    Given the user navigates to login page
    When user enters email as "jobspari2@gmail.com" and password as "abc258"
    And user clicks on login button
    Then user capture the error
   