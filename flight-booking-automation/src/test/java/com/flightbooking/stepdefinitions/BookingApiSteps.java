package com.flightbooking.stepdefinitions;

import org.testng.Assert;

import com.flightbooking.api.BookingApi;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookingApiSteps {

    TestContext context;
    BookingApi api = new BookingApi();

    public BookingApiSteps(TestContext context) {
        this.context = context;
    }

    @When("I create a booking for {string} {string} with price {int}")
    public void createBooking(String first, String last, int price) {
        context.response = api.createBooking(api.buildBooking(first, last, price, "2026-12-01", "2026-12-10"));
    }

    @Given("a booking exists for {string} {string} with price {int}")
    public void bookingExists(String first, String last, int price) {
        context.response = api.createBooking(api.buildBooking(first, last, price, "2026-12-01", "2026-12-10"));
        context.bookingId = context.response.jsonPath().getInt("bookingid");
    }

    @When("I get the booking by its id")
    public void getBooking() {
        context.response = api.getBooking(context.bookingId);
    }

    @When("I delete the booking")
    public void deleteBooking() {
        context.token = api.getToken();
        context.response = api.deleteBooking(context.bookingId, context.token);
    }

    @Then("the response status should be {int}")
    public void checkStatus(int status) {
        Assert.assertEquals(context.response.getStatusCode(), status);
    }

    @Then("the response should have a booking id")
    public void checkBookingId() {
        Assert.assertTrue(context.response.jsonPath().getInt("bookingid") > 0);
    }

    @Then("the booking firstname should be {string}")
    public void checkFirstNameInCreate(String name) {
        Assert.assertEquals(context.response.jsonPath().getString("booking.firstname"), name);
    }

    @Then("the firstname in response should be {string}")
    public void checkFirstName(String name) {
        Assert.assertEquals(context.response.jsonPath().getString("firstname"), name);
    }

    @Then("getting the booking again should give status {int}")
    public void checkBookingGone(int status) {
        Assert.assertEquals(api.getBooking(context.bookingId).getStatusCode(), status);
    }
}
