@api
Feature: Booking API

  Scenario: Create a new booking
    When I create a booking for "Snigdha" "Das" with price 5500
    Then the response status should be 200
    And the response should have a booking id
    And the booking firstname should be "Snigdha"

  Scenario: Get a booking that was created
    Given a booking exists for "Rahul" "Sharma" with price 4200
    When I get the booking by its id
    Then the response status should be 200
    And the firstname in response should be "Rahul"

  Scenario: Delete a booking
    Given a booking exists for "Priya" "Nair" with price 3000
    When I delete the booking
    Then the response status should be 201
    And getting the booking again should give status 404
