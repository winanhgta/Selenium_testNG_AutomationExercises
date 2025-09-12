package com.huy.automationexercise.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        DriverFactory.launchWebsite("https://automationexercise.com/");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
