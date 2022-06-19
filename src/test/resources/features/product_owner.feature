@product_owner
Feature: GitHub API

  Scenario: As a product owner I want to see how many open pull requests are there for our product
    Given user loads available "open" pull requests
    Then user saves "open" pull requests data to file "pull_requests"