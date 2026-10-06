package com.flightbooking.stepdefinitions;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.flightbooking.utils.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

  
    @Before("@ui")
    public void startBrowser() {
        ChromeOptions options = new ChromeOptions();
        if (ConfigReader.get("headless").equals("true")) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        context.driver = new ChromeDriver(options);
        context.driver.manage().window().maximize();
        context.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.get("implicit.wait"))));
    }

    @After("@ui")
    public void closeBrowser(Scenario scenario) {
        if (context.driver != null) {
    
            if (scenario.isFailed()) {
                byte[] shot = ((TakesScreenshot) context.driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(shot, "image/png", "failed screenshot");
            }
            context.driver.quit();
        }
    }
}
