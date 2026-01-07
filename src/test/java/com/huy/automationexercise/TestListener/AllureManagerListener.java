package com.huy.automationexercise.TestListener;

import com.huy.automationexercise.driver.DriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("--- TEST FAILED: " + result.getName() + " ---");

        WebDriver driver = DriverManager.getDriver();

        if (driver != null) {
            // Chụp ảnh dưới dạng mảng Byte
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // Sử dụng Allure API để đính kèm ảnh trực tiếp vào báo cáo
            // ByteArrayInputStream giúp Allure đọc dữ liệu ảnh để nhúng vào JSON report
            Allure.addAttachment(result.getName() + "_Failed_Screenshot", new ByteArrayInputStream(screenshot));

            System.out.println("Screenshot captured and attached to Allure for: " + result.getName());
        } else {
            System.out.println("Driver is null. Cannot take screenshot.");
        }
    }

    // Các hàm onTestStart, onTestSuccess giữ nguyên như cũ...
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("--- STARTING TEST: " + result.getName() + " ---");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("--- TEST PASSED: " + result.getName() + " ---");
    }
}