package com.flightbooking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {

    WebDriver driver;

    By heading = By.tagName("h1");
    By bookingId = By.xpath("//td[text()='Id']/following-sibling::td");
    By status = By.xpath("//td[text()='Status']/following-sibling::td");

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeading() {
        return driver.findElement(heading).getText();
    }

    public String getBookingId() {
        return driver.findElement(bookingId).getText();
    }

    public String getStatus() {
        return driver.findElement(status).getText();
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
