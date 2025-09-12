package com.huy.automationexercise.base;

//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class DriverFactory {

    private static WebDriver driver;

    // Khởi tạo driver (singleton)
    public static WebDriver getDriver() {
        if (driver == null) {
            //WebDriverManager.edgedriver().setup(); // Tự động tải EdgeDriver
            driver = new EdgeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    // Mở website
    public static void launchWebsite(String url) {
        getDriver().get(url);
    }

    // Đóng driver
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

