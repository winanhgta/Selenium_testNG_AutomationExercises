//package com.huy.automationexercise.page;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import java.time.Duration;
//public class HomePage {
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
//    private WebElement loggedInAsText;
//
//    private By signupLoginLink = By.xpath("//a[contains(@href,'/login')]");
//
////    public HomePage(WebDriver driver) {
////        this.driver = driver;
////        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
////    }
//
//    public HomePage(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//    }
//
//    public SignupLoginPage clickSignupLogin() {
//        wait.until(ExpectedConditions.elementToBeClickable(signupLoginLink)).click();
//        return new SignupLoginPage(driver);
//    }
//
//    public String getTitle() {
//        return driver.getTitle();
//    }
//
//    public boolean isLoggedInAs(String expectedName) {
//        return loggedInAsText.getText().contains(expectedName);
//    }
//}

package com.huy.automationexercise.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
    private WebElement loggedInAsText;

    // đổi sang WebElement để dùng với PageFactory + ExpectedConditions
    @FindBy(xpath = "//a[contains(@href,'/login')]")
    private WebElement signupLoginLink;

    // A stable element to verify the page has loaded
    @FindBy(className = "features_items")
    private WebElement featuresItemsSection;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public SignupLoginPage clickSignupLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(signupLoginLink)).click();
        return new SignupLoginPage(driver);
    }

    /**
     * Verifies that the home page is visible by checking for the "Features Items" section.
     * @return true if the section is visible, false otherwise.
     */
    public boolean isFeaturesItemsVisible() {
        return wait.until(ExpectedConditions.visibilityOf(featuresItemsSection)).isDisplayed();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isLoggedInAs(String expectedName) {
        // thêm check null-safe
        return loggedInAsText != null && loggedInAsText.getText().contains(expectedName);
    }
}
