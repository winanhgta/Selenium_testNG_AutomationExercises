package com.huy.automationexercise.driver;


import com.huy.automationexercise.constants.FrameworkConstants;
import com.huy.automationexercise.exceptions.TargetNotValidException;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;

import java.util.Optional;

/**
 * Class TargetFactory giúp quyết định việc khởi tạo trình duyệt cụ thể nào.
 * Phiên bản này được tối ưu chỉ dành cho việc thực thi LOCAL (trên máy cá nhân).
 */
public class TargetFactory {

    /**
     * Khởi tạo WebDriver dựa trên cấu hình mặc định trong FrameworkConstants.
     * @return WebDriver instance
     */
    public WebDriver createInstance() {
        // Lấy tên trình duyệt từ cấu hình, mặc định là CHROME nếu không tìm thấy
        String browserName = Optional.ofNullable(FrameworkConstants.BROWSER)
                .orElse("CHROME")
                .toUpperCase();

        return createInstance(browserName);
    }

    /**
     * Khởi tạo WebDriver theo tên trình duyệt truyền vào.
     * @param browser Tên trình duyệt (ví dụ: "chrome", "firefox", "edge")
     * @return WebDriver instance
     */
    public WebDriver createInstance(String browser) {
        // 1. Xác định tên trình duyệt cuối cùng (ưu tiên từ Constants nếu có)
        String finalBrowserName = (FrameworkConstants.BROWSER != null && !FrameworkConstants.BROWSER.isEmpty())
                ? FrameworkConstants.BROWSER.toUpperCase()
                : browser.toUpperCase();

        // 2. Ghi chú vào Allure Report để theo dõi trong báo cáo
        Allure.step("🤖 Hệ thống đang khởi tạo trình duyệt: " + finalBrowserName);

        try {
            // 3. Sử dụng tính năng valueOf của Enum BrowserFactory để tìm trình duyệt tương ứng
            // Cách này giúp loại bỏ lệnh switch-case dài dòng
            return BrowserFactory.valueOf(finalBrowserName).createDriver();

        } catch (IllegalArgumentException e) {
            // Nếu truyền sai tên trình duyệt (ví dụ: "Crom" thay vì "CHROME")
            throw new TargetNotValidException("Trình duyệt '" + finalBrowserName + "' không hợp lệ hoặc chưa được hỗ trợ!");
        }
    }
}
