package com.flightbooking.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 
public class FlightsPage {

    WebDriver driver;

    By heading = By.tagName("h3");
    By flightRows = By.cssSelector("table.table tbody tr");
    By chooseButtons = By.cssSelector("table.table tbody tr input[type='submit']");

    public FlightsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeading() {
        return driver.findElement(heading).getText();
    }

    public int getFlightCount() {
        List<WebElement> rows = driver.findElements(flightRows);
        return rows.size();
    }

    public void chooseFirstFlight() {
        driver.findElements(chooseButtons).get(0).click();
    }

    // flight 3 for example, number starts from 1
    public void chooseFlight(int number) {
        driver.findElements(chooseButtons).get(number - 1).click();
    }

    // airline name of a row (3rd column)
    public String getAirline(int rowNumber) {
        List<WebElement> rows = driver.findElements(flightRows);
        return rows.get(rowNumber - 1).findElements(By.tagName("td")).get(2).getText();
    }
}
