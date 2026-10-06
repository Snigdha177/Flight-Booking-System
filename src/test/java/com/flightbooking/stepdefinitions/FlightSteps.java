package com.flightbooking.stepdefinitions;

import org.testng.Assert;

import com.flightbooking.pages.ConfirmationPage;
import com.flightbooking.pages.HomePage;
import com.flightbooking.pages.PurchasePage;
import com.flightbooking.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FlightSteps {

    TestContext context;
    HomePage homePage;

    public FlightSteps(TestContext context) {
        this.context = context;
    }

    @Given("the user is on the flight booking home page")
    public void openHomePage() {
        context.driver.get(ConfigReader.get("base.url"));
        homePage = new HomePage(context.driver);
    }

    @Then("the page heading should be {string}")
    public void checkHomeHeading(String expected) {
        Assert.assertEquals(homePage.getHeading(), expected);
    }

    @When("the user searches flights from {string} to {string}")
    public void searchFlights(String from, String to) {
        context.flightsPage = homePage.searchFlights(from, to);
    }

    @Then("the flights page heading should be {string}")
    public void checkFlightsHeading(String expected) {
        Assert.assertEquals(context.flightsPage.getHeading(), expected);
    }

    @Then("at least one flight should be listed")
    public void checkFlightsListed() {
        Assert.assertTrue(context.flightsPage.getFlightCount() > 0);
    }

    @When("the user chooses the first flight")
    public void chooseFirstFlight() {
        context.flightsPage.chooseFirstFlight();
        context.purchasePage = new PurchasePage(context.driver);
    }

    @When("the user enters passenger name {string} and city {string}")
    public void enterPassengerDetails(String name, String city) {
        context.purchasePage.fillPassengerDetails(name, "12 Test Street", city, "Delhi", "110001");
    }

    @When("the user pays with card {string}")
    public void payWithCard(String card) {
        context.purchasePage.fillCardDetails(card, "12", "2030", "Snigdha Das");
        context.confirmationPage = context.purchasePage.clickPurchase();
    }

    @Then("the confirmation page should say {string}")
    public void checkConfirmation(String expected) {
        ConfirmationPage page = context.confirmationPage;
        Assert.assertEquals(page.getHeading(), expected);
    }
}
