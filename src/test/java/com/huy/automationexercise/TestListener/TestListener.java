package com.huy.automationexercise.TestListener; // Đảm bảo trùng với thư mục thực tế

import com.huy.automationexercise.driver.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    // Hàm này giúp đính kèm ảnh vào Allure Report
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("--- TEST FAILED: " + result.getName() + " ---");

        // Lấy driver từ DriverManager của bạn
        WebDriver driver = DriverManager.getDriver();

        if (driver != null) {
            saveScreenshot(driver);
            System.out.println("Screenshot captured for: " + result.getName());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("--- STARTING TEST: " + result.getName() + " ---");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("--- TEST PASSED: " + result.getName() + " ---");
    }
}