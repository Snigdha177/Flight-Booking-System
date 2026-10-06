package com.flightbooking.api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import com.flightbooking.utils.ConfigReader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
 
public class BookingApi {

    private String baseUrl = ConfigReader.get("api.base.url");

    public String getToken() {
        Map<String, String> body = new HashMap<>();
        body.put("username", ConfigReader.get("api.username"));
        body.put("password", ConfigReader.get("api.password"));

        Response res = given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/auth");

        return res.jsonPath().getString("token");
    }
 
    public Map<String, Object> buildBooking(String firstName, String lastName, int price, String checkin, String checkout) {
        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", checkin);
        dates.put("checkout", checkout);

        Map<String, Object> booking = new HashMap<>();
        booking.put("firstname", firstName);
        booking.put("lastname", lastName);
        booking.put("totalprice", price);
        booking.put("depositpaid", true);
        booking.put("bookingdates", dates);
        booking.put("additionalneeds", "Window seat");
        return booking;
    }

    public Response createBooking(Map<String, Object> booking) {
        return given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");
    }

    public Response getBooking(int id) {
        return given()
                .baseUri(baseUrl)
                .accept(ContentType.JSON)
                .when()
                .get("/booking/" + id);
    }

    public Response updateBooking(int id, Map<String, Object> booking, String token) {
        return given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(booking)
                .when()
                .put("/booking/" + id);
    }

    public Response deleteBooking(int id, String token) {
        return given()
                .baseUri(baseUrl)
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + id);
    }
}
