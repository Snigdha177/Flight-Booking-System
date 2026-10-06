package com.flightbooking.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.flightbooking.base.BaseTest;
import com.flightbooking.pages.ConfirmationPage;
import com.flightbooking.pages.FlightsPage;
import com.flightbooking.pages.HomePage;
import com.flightbooking.pages.PurchasePage;

public class BookFlightTest extends BaseTest {

    @Test
    public void bookFlightEndToEndTest() {
        HomePage home = new HomePage(driver);
        FlightsPage flights = home.searchFlights("Paris", "Rome");
        flights.chooseFirstFlight();

        PurchasePage purchase = new PurchasePage(driver);
        purchase.fillPassengerDetails("Snigdha Das", "12 Test Street", "Delhi", "Delhi", "110001");
        purchase.fillCardDetails("4111111111111111", "12", "2030", "Snigdha Das");
        ConfirmationPage confirmation = purchase.clickPurchase();

        Assert.assertEquals(confirmation.getHeading(), "Thank you for your purchase today!");
        Assert.assertEquals(confirmation.getTitle(), "BlazeDemo Confirmation");
        Assert.assertFalse(confirmation.getBookingId().isEmpty(), "booking id is empty");
    }

    @Test
    public void purchasePageOpensAfterChoosingFlightTest() {
        HomePage home = new HomePage(driver);
        FlightsPage flights = home.searchFlights("Philadelphia", "Berlin");
        flights.chooseFlight(2);

        PurchasePage purchase = new PurchasePage(driver);
        Assert.assertTrue(purchase.getHeading().contains("has been reserved"));
    }
}
