package com.flightbooking.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.flightbooking.base.BaseTest;
import com.flightbooking.pages.FlightsPage;
import com.flightbooking.pages.HomePage;

public class FlightSearchTest extends BaseTest {

    @Test
    public void homePageLoadsTest() {
        HomePage home = new HomePage(driver);
        Assert.assertEquals(home.getHeading(), "Welcome to the Simple Travel Agency!");
    }

    @Test
    public void searchFlightsTest() {
        HomePage home = new HomePage(driver);
        FlightsPage flights = home.searchFlights("Paris", "Buenos Aires");

        Assert.assertEquals(flights.getHeading(), "Flights from Paris to Buenos Aires:");
        Assert.assertTrue(flights.getFlightCount() > 0, "no flights were shown");
    }

    @Test
    public void searchDifferentCitiesTest() {
        HomePage home = new HomePage(driver);
        FlightsPage flights = home.searchFlights("Boston", "London");

        Assert.assertEquals(flights.getHeading(), "Flights from Boston to London:");
    }
}
