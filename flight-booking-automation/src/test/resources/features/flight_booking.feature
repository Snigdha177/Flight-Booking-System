@ui
Feature: Flight booking on the website

  Background:
    Given the user is on the flight booking home page

  Scenario: Home page opens properly
    Then the page heading should be "Welcome to the Simple Travel Agency!"

  Scenario: Search flights between two cities
    When the user searches flights from "Paris" to "Buenos Aires"
    Then the flights page heading should be "Flights from Paris to Buenos Aires:"
    And at least one flight should be listed

  Scenario: Book a flight end to end
    When the user searches flights from "Paris" to "Rome"
    And the user chooses the first flight
    And the user enters passenger name "Snigdha Das" and city "Delhi"
    And the user pays with card "4111111111111111"
    Then the confirmation page should say "Thank you for your purchase today!"

  Scenario Outline: Search flights for different routes
    When the user searches flights from "<from>" to "<to>"
    Then the flights page heading should be "Flights from <from> to <to>:"

    Examples:
      | from         | to           |
      | Boston       | London       |
      | Portland     | Berlin       |
      | San Diego    | New York     |
