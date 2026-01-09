package com.huy.automationexercise.common;

import com.huy.automationexercise.constants.FrameworkConstants;
import com.huy.automationexercise.driver.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;

public class BaseTest {

    protected TargetFactory targetFactory = new TargetFactory();
    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void createDriver(@Optional String browser) {
        WebDriver driver = targetFactory.createInstance(browser);
        DriverManager.setDriver(driver);
        DriverManager.getDriver().get(FrameworkConstants.URL);
        DriverManager.getDriver().manage().window().maximize();
        System.setProperty(org.slf4j.simple.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "ERROR");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            try {
                // Chụp ảnh màn hình cho TẤT CẢ các trường hợp (PASS, FAIL, SKIP)
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                // Đặt tên ảnh dựa trên trạng thái (ví dụ: test_Login_PASSED_Screenshot)
                String status = getStatusName(result.getStatus());
                String attachmentName = result.getName() + "_" + status + "_Screenshot";

                Allure.addAttachment(
                        attachmentName,
                        "image/png",
                        new ByteArrayInputStream(screenshot),
                        "png"
                );

                System.out.println("Đã chụp ảnh màn hình (" + status + ") cho test: " + result.getName());

            } catch (Exception e) {
                System.err.println("Lỗi khi chụp ảnh trong AfterMethod: " + e.getMessage());
            } finally {
                // Đảm bảo trình duyệt luôn được đóng
                DriverManager.quit();
            }
        }
    }

    // Helper method để lấy tên trạng thái test cho dễ đọc trong Allure
    private String getStatusName(int status) {
        return switch (status) {
            case ITestResult.SUCCESS -> "PASSED";
            case ITestResult.FAILURE -> "FAILED";
            case ITestResult.SKIP -> "SKIPPED";
            default -> "UNKNOWN";
        };
    }

//        @AfterMethod(alwaysRun = true)
//        public void tearDown(ITestResult result) {
//            WebDriver driver = DriverManager.getDriver();
//            if (driver != null) {
//                try {
//                    if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {
//                        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//                        Allure.addAttachment(
//                                result.getName() + "_Screenshot",
//                                "image/png",
//                                new ByteArrayInputStream(screenshot),
//                                "png"
//                        );
//
//                        System.out.println("Đã đính kèm ảnh cho test: " + result.getName());
//                    }
//                } catch (Exception e) {
//                    System.err.println("Lỗi khi chụp ảnh trong AfterMethod: " + e.getMessage());
//                } finally {
//                    DriverManager.quit();
//                }
//            }
//        }
}


