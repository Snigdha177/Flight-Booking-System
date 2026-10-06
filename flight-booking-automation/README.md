# Flight Booking Automation

This is a test automation project for a flight booking website.

The project uses **Java, Selenium, TestNG, REST Assured, Cucumber, and Maven**.


Using Selenium, I automated:

* Searching for a flight
* Selecting a flight
* Filling the booking form
* Checking the booking confirmation

Website used: BlazeDemo

### API Testing

Using REST Assured, I tested:

* Create booking
* Get booking
* Update booking
* Delete booking

API used: Restful Booker
 

I used Cucumber to write some of the test scenarios in feature files.

## Tools Used

* Java 17
* Selenium
* TestNG
* REST Assured
* Cucumber
* Maven


## How to Run

Make sure Java 17, Maven and Chrome are installed.

Run the tests using:

```bash
mvn clean test
```

To run in headless mode:

```bash
mvn test -Dheadless=true
```

## Reports

Test reports are generated after the test execution.

* TestNG report: `target/surefire-reports`
* Cucumber report: `target/cucumber-reports`
