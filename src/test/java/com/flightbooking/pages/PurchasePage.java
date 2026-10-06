package com.flightbooking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PurchasePage {

    WebDriver driver;

    By heading = By.tagName("h2");
    By nameField = By.id("inputName");
    By addressField = By.id("address");
    By cityField = By.id("city");
    By stateField = By.id("state");
    By zipField = By.id("zipCode");
    By cardNumberField = By.id("creditCardNumber");
    By cardMonthField = By.id("creditCardMonth");
    By cardYearField = By.id("creditCardYear");
    By nameOnCardField = By.id("nameOnCard");
    By purchaseBtn = By.cssSelector("input[type='submit']");

    public PurchasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeading() {
        return driver.findElement(heading).getText();
    }

    public void fillPassengerDetails(String name, String address, String city, String state, String zip) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(stateField).sendKeys(state);
        driver.findElement(zipField).sendKeys(zip);
    }

    public void fillCardDetails(String cardNumber, String month, String year, String nameOnCard) {
        driver.findElement(cardNumberField).sendKeys(cardNumber);
  
        driver.findElement(cardMonthField).clear();
        driver.findElement(cardMonthField).sendKeys(month);
        driver.findElement(cardYearField).clear();
        driver.findElement(cardYearField).sendKeys(year);
        driver.findElement(nameOnCardField).sendKeys(nameOnCard);
    }

    public ConfirmationPage clickPurchase() {
        driver.findElement(purchaseBtn).click();
        return new ConfirmationPage(driver);
    }
}
