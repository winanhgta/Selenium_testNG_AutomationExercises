package com.huy.automationexercise.driver;

import com.huy.automationexercise.constants.FrameworkConstants;
import com.huy.automationexercise.exceptions.HeadlessNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Boolean.TRUE;

public enum BrowserFactory {

    CHROME {
        @Override
        public WebDriver createDriver() {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(getOptions());
        }

        @Override
        public ChromeOptions getOptions() {
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();

            // 1. Cấu hình Prefs để chặn popup và các thứ gây nhiễu
            prefs.put("profile.default_content_setting_values.notifications", 2);
            prefs.put("profile.default_content_setting_values.popups", 2); // Chặn popup triệt để
            prefs.put("profile.default_content_settings.popups", 0);
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            // 2. Loại bỏ thanh thông báo "Chrome is being controlled..." (thủ phạm gây lệch tọa độ click)
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            options.setExperimentalOption("useAutomationExtension", false);

            // 3. Đối số dòng lệnh (Arguments) để chặn quảng cáo
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-extensions"); // Lưu ý: Tắt cái này nếu bạn dùng Adblock .crx
            options.addArguments("--remote-allow-origins=*");

            // Chặn Google Ads và Vignette Ads bằng cách chặn các tiến trình liên quan
            options.addArguments("--disable-features=OptimizationHints");
            options.addArguments("--incognito"); // Chạy ẩn danh giúp giảm Ads cá nhân hóa

            // Tích hợp Extension chặn quảng cáo (Nếu bạn có file .crx)
            // options.addExtensions(new File("src/test/resources/extensions/adblock.crx"));

            if (Boolean.parseBoolean(FrameworkConstants.HEADLESS)) {
                options.addArguments("--headless=new", "--window-size=1920,1080", "--no-sandbox", "--disable-dev-shm-usage");
            } else {
                options.addArguments("--start-maximized");
            }
            return options;
        }
    },
    EDGE {
        @Override
        public WebDriver createDriver() {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver(getOptions());
        }

        @Override
        public EdgeOptions getOptions() {
            EdgeOptions options = new EdgeOptions();
            Map<String, Object> prefs = new HashMap<>();

            prefs.put("profile.default_content_setting_values.notifications", 2);
            prefs.put("profile.default_content_setting_values.popups", 2);
            options.setExperimentalOption("prefs", prefs);

            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--inprivate"); // Tương tự incognito của Chrome
            options.addArguments("--remote-allow-origins=*");

            if (Boolean.parseBoolean(FrameworkConstants.HEADLESS)) {
                options.addArguments("--headless=new", "--window-size=1920,1080", "--no-sandbox", "--disable-dev-shm-usage");
            } else {
                options.addArguments(START_MAXIMIZED);
            }
            return options;
        }
    },
    // FIREFOX và SAFARI giữ nguyên logic tương tự...
    FIREFOX {
        @Override
        public WebDriver createDriver() {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver(getOptions());
        }

        @Override
        public FirefoxOptions getOptions() {
            FirefoxOptions options = new FirefoxOptions();
            if (Boolean.parseBoolean(FrameworkConstants.HEADLESS)) {
                options.addArguments("-headless", "--window-size=1920,1080", "--no-sandbox", "--disable-dev-shm-usage");
            }
            return options;
        }
    },
    SAFARI {
        @Override
        public WebDriver createDriver() {
            return new SafariDriver(getOptions());
        }

        @Override
        public SafariOptions getOptions() {
            SafariOptions options = new SafariOptions();
            if (TRUE.equals(Boolean.valueOf(FrameworkConstants.HEADLESS)))
                throw new HeadlessNotSupportedException(options.getBrowserName());
            return options;
        }
    };

    private static final String START_MAXIMIZED = "--start-maximized";
    public abstract WebDriver createDriver();
    public abstract MutableCapabilities getOptions();
}