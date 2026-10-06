package com.flightbooking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HomePage {

    WebDriver driver;

    By heading = By.tagName("h1");
    By fromDropdown = By.name("fromPort");
    By toDropdown = By.name("toPort");
    By findFlightsBtn = By.cssSelector("input[type='submit']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeading() {
        return driver.findElement(heading).getText();
    }

    public void selectFromCity(String city) {
        new Select(driver.findElement(fromDropdown)).selectByVisibleText(city);
    }

    public void selectToCity(String city) {
        new Select(driver.findElement(toDropdown)).selectByVisibleText(city);
    }

    public FlightsPage clickFindFlights() {
        driver.findElement(findFlightsBtn).click();
        return new FlightsPage(driver);
    }

    
    public FlightsPage searchFlights(String from, String to) {
        selectFromCity(from);
        selectToCity(to);
        return clickFindFlights();
    }
}
