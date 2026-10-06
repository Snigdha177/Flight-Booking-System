package com.flightbooking.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.flightbooking.pages.ConfirmationPage;
import com.flightbooking.pages.FlightsPage;
import com.flightbooking.pages.PurchasePage;

import io.restassured.response.Response;
 
public class TestContext {
 
    public WebDriver driver;
    public FlightsPage flightsPage;
    public PurchasePage purchasePage;
    public ConfirmationPage confirmationPage;
 
    public Response response;
    public int bookingId;
    public String token;
}
