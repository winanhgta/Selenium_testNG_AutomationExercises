package com.huy.automationexercise.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = DriverFactory.getDriver();
        DriverFactory.launchWebsite("https://automationexercise.com/");
    }

    @AfterClass
    public static void tearDown() {
        DriverFactory.quitDriver();
    }
}
