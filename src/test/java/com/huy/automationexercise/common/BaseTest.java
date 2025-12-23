package com.huy.automationexercise.common;

import com.huy.automationexercise.driver.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {

        // Khởi tạo đối tượng TargetFactory để dùng chung cho việc tạo Driver
        protected TargetFactory targetFactory = new TargetFactory();

        /**
         * Hàm Setup chạy trước mỗi phương thức @Test
         * @Parameters("browser"): Cho phép truyền tên trình duyệt từ file testng.xml
         * @Optional: Nếu không truyền từ xml, nó sẽ lấy giá trị mặc định từ FrameworkConstants
         */
        @BeforeMethod(alwaysRun = true)
        @Parameters("browser")
        public void createDriver(@Optional String browser) {
            // 1. TargetFactory tạo ra instance của WebDriver (Local)
            // Nó sẽ tự xử lý việc lấy browser từ Constants nếu tham số 'browser' bị null
            WebDriver driver = targetFactory.createInstance(browser);

            // 2. Đưa Driver vừa tạo vào DriverManager để quản lý Thread-Safe (an toàn đa luồng)
            DriverManager.setDriver(driver);

            // 3. Cấu hình cơ bản sau khi mở trình duyệt
            DriverManager.getDriver().manage().window().maximize();
        }

        /**
         * Hàm TearDown chạy sau mỗi phương thức @Test
         * Đảm bảo trình duyệt luôn được đóng dù test case Pass hay Fail
         */
        @AfterMethod(alwaysRun = true)
        public void closeDriver() {
            // Gọi hàm quit từ DriverManager để đóng trình duyệt và giải phóng bộ nhớ
            DriverManager.quit();
        }
    }

