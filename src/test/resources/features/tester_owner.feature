@test_owner
Feature: LogIn

  Background:
    Given current user is "tolkachev75@maildrop.cc"

  Scenario: As a test owner I can login to the Store
    Given user navigates to "Men" page
    And user navigates to "Login" page
    And user see "Login" page
    When user submit current user credentials
    Then user see "My Account" page


