@tester
Feature: Data content validation

  Scenario: As a tester I want to verify page links
    Given user navigates to "Men" page
    And user see "Men" page
    Then  user verifies Links

  Scenario: As a tester I want to make sure no JS errors when I visit the Store
    Given user navigates to "Men" page
    And user see "Men" page
    Then user verifies Logs