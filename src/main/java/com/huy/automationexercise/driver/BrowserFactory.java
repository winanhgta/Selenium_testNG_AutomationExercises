package com.huy.automationexercise.driver;

import com.huy.automationexercise.constants.FrameworkConstants;
import com.huy.automationexercise.exceptions.HeadlessNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
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

public enum BrowserFactory {

    CHROME {
        @Override
        public WebDriver createDriver() {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver(getOptions());
            setBrowserSize(driver);
            return driver;
        }

        @Override
        public ChromeOptions getOptions() {
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", getChromiumPrefs());
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            options.setExperimentalOption("useAutomationExtension", false);

            addChromiumArguments(options);

            if (Boolean.parseBoolean(FrameworkConstants.HEADLESS)) {
                options.addArguments("--headless=new"); // Bắt buộc phải có trên Linux/CI
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
            } else {
                options.addArguments(START_MAXIMIZED);
            }
            return options;
        }
    },
    EDGE {
        @Override
        public WebDriver createDriver() {
            WebDriverManager.edgedriver().setup();
            WebDriver driver = new EdgeDriver(getOptions());
            setBrowserSize(driver);
            return driver;
        }

        @Override
        public EdgeOptions getOptions() {
            EdgeOptions options = new EdgeOptions();
            options.setExperimentalOption("prefs", getChromiumPrefs());

            addChromiumArguments(options);
            options.addArguments("--inprivate");

            if (Boolean.parseBoolean(FrameworkConstants.HEADLESS)) {
                options.addArguments("--headless=new");
            } else {
                options.addArguments(START_MAXIMIZED);
            }
            return options;
        }
    },
    FIREFOX {
        @Override
        public WebDriver createDriver() {
            WebDriverManager.firefoxdriver().setup();
            WebDriver driver = new FirefoxDriver(getOptions());
            setBrowserSize(driver);
            return driver;
        }

        @Override
        public FirefoxOptions getOptions() {
            FirefoxOptions options = new FirefoxOptions();
            if (Boolean.parseBoolean(FrameworkConstants.HEADLESS)) {
                options.addArguments("-headless");
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
            if (Boolean.parseBoolean(FrameworkConstants.HEADLESS)) {
                throw new HeadlessNotSupportedException(options.getBrowserName());
            }
            return options;
        }
    };

    private static final String START_MAXIMIZED = "--start-maximized";

    public abstract WebDriver createDriver();
    public abstract MutableCapabilities getOptions();

    // Helper: Ép kích thước màn hình Full HD cho Headless
    protected void setBrowserSize(WebDriver driver) {
        if (Boolean.parseBoolean(FrameworkConstants.HEADLESS)) {
            driver.manage().window().setSize(new Dimension(1920, 1080));
        } else {
            driver.manage().window().maximize();
        }
    }

    // Helper: Cấu hình Prefs chung cho các trình duyệt nhân Chromium
    protected Map<String, Object> getChromiumPrefs() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.default_content_setting_values.popups", 2);
        prefs.put("profile.password_manager_enabled", false);
        return prefs;
    }

    // Helper: Đối số dòng lệnh chung cho Chromium
    protected void addChromiumArguments(org.openqa.selenium.chromium.ChromiumOptions<?> options) {
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080"); // Set size ngay từ lúc khởi chạy
        options.addArguments("--incognito");
    }
}