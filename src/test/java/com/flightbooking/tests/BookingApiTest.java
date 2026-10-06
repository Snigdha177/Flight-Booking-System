package com.flightbooking.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.flightbooking.api.BookingApi;

import io.restassured.response.Response;
 
public class BookingApiTest {

    BookingApi api = new BookingApi();
    String token;
    int bookingId;

    @BeforeClass
    public void getAuthToken() {
        token = api.getToken();
        Assert.assertNotNull(token, "token should not be null");
    }

    @Test(priority = 1)
    public void createBookingTest() {
        Map<String, Object> booking = api.buildBooking("Snigdha", "Das", 5500, "2026-12-01", "2026-12-10");

        Response res = api.createBooking(booking);

        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.jsonPath().getString("booking.firstname"), "Snigdha");
        Assert.assertEquals(res.jsonPath().getInt("booking.totalprice"), 5500);

        bookingId = res.jsonPath().getInt("bookingid");
        Assert.assertTrue(bookingId > 0, "booking id should be generated");
    }

    @Test(priority = 2, dependsOnMethods = "createBookingTest")
    public void getBookingTest() {
        Response res = api.getBooking(bookingId);

        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.jsonPath().getString("firstname"), "Snigdha");
        Assert.assertEquals(res.jsonPath().getString("lastname"), "Das");
        Assert.assertEquals(res.jsonPath().getString("bookingdates.checkin"), "2026-12-01");
    }

    @Test(priority = 3, dependsOnMethods = "createBookingTest")
    public void updateBookingTest() {
        Map<String, Object> updated = api.buildBooking("Snigdha", "Das", 7200, "2026-12-03", "2026-12-12");

        Response res = api.updateBooking(bookingId, updated, token);

        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.jsonPath().getInt("totalprice"), 7200);
        Assert.assertEquals(res.jsonPath().getString("bookingdates.checkin"), "2026-12-03");
    }

    @Test(priority = 4, dependsOnMethods = "createBookingTest")
    public void deleteBookingTest() {
        Response res = api.deleteBooking(bookingId, token);

         
        Assert.assertEquals(res.getStatusCode(), 201);

        Response check = api.getBooking(bookingId);
        Assert.assertEquals(check.getStatusCode(), 404);
    }

    @Test(priority = 5)
    public void getBookingThatDoesNotExistTest() {
        Response res = api.getBooking(99999999);
        Assert.assertEquals(res.getStatusCode(), 404);
    }

    @Test(priority = 6)
    public void deleteWithoutTokenShouldFailTest() {
        Response res = api.deleteBooking(1, "wrongtoken");
        Assert.assertEquals(res.getStatusCode(), 403);
    }
}
