package com.huy.automationexercise.driver;

import com.huy.automationexercise.constants.FrameworkConstants;
import com.huy.automationexercise.exceptions.HeadlessNotSupportedException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Boolean.TRUE;

public enum BrowserFactory {

    CHROME {
        @Override
        public WebDriver createDriver() {
            // Tự động tải và thiết lập Driver phù hợp với phiên bản Chrome trên máy
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(getOptions());
        }

        @Override
        public ChromeOptions getOptions() {
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();

            // Chặn các popup gây phiền nhiễu và tắt lưu mật khẩu
            prefs.put("profile.default_content_setting_values.notifications", 2);
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("autofill.profile_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            // Gom nhóm các tham số cấu hình hệ thống
            options.addArguments("--disable-infobars", "--disable-extensions", "--remote-allow-origins=*");
            options.setAcceptInsecureCerts(true);
            options.addArguments("--disable-notifications"); // Tắt thông báo đẩy
            options.addArguments("--disable-popup-blocking"); // Tắt chặn popup
            options.addArguments("--dns-prefetch-disable"); // Tắt dự đoán DNS để tăng tốc load ban đầu

            // Kiểm tra chế độ Headless từ Constants
            if (Boolean.parseBoolean(FrameworkConstants.HEADLESS)) {
                options.addArguments("--headless=new", "--window-size=1920,1080", "--no-sandbox", "--disable-dev-shm-usage");
                options.addArguments("--disable-notifications"); // Tắt thông báo đẩy
                options.addArguments("--disable-popup-blocking"); // Tắt chặn popup
                options.addArguments("--disable-infobars"); // Tắt thanh "Chrome is being controlled..."
                options.addArguments("--dns-prefetch-disable"); // Tắt dự đoán DNS để tăng tốc load ban đầu
            } else {
                options.addArguments("--start-maximized");
            }
            return options;
        }
    },
    EDGE {
    @Override
    public WebDriver createDriver() {
        return new EdgeDriver(getOptions());
    }

    @Override
    public EdgeOptions getOptions() {
        EdgeOptions options = new EdgeOptions();

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.password_manager_leak_detection", false); // Turn off change your password
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");

        options.setAcceptInsecureCerts(true);

        if (Boolean.valueOf(FrameworkConstants.HEADLESS) == true) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--high-dpi-support=1");
            options.addArguments("--force-device-scale-factor=1");
        } else {
            options.addArguments(START_MAXIMIZED);
        }

        return options;
    }
}, FIREFOX {
    @Override
    public WebDriver createDriver() {
        return new FirefoxDriver(getOptions());
    }

    @Override
    public FirefoxOptions getOptions() {
        FirefoxOptions options = new FirefoxOptions();

        options.setAcceptInsecureCerts(true);

        if (Boolean.valueOf(FrameworkConstants.HEADLESS) == true) {
            options.addArguments("-headless");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
        }

        return options;
    }
}, SAFARI {
    @Override
    public WebDriver createDriver() {
        return new SafariDriver(getOptions());
    }

    @Override
    public SafariOptions getOptions() {
        SafariOptions options = new SafariOptions();
        options.setAutomaticInspection(false);

        if (TRUE.equals(Boolean.valueOf(FrameworkConstants.HEADLESS)))
            throw new HeadlessNotSupportedException(options.getBrowserName());

        return options;
    }
};

    private static final String START_MAXIMIZED = "--start-maximized";

    public abstract WebDriver createDriver();

    public abstract MutableCapabilities getOptions();
}
