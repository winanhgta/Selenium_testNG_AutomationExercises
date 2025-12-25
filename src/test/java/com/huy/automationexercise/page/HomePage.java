package com.huy.automationexercise.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.huy.automationexercise.driver.DriverManager;

import java.time.Duration;

public class HomePage {
    private WebDriverWait wait;

    @FindBy(xpath = "//a[contains(.,'Logged in as')]")
    private WebElement loggedInAsText;

    @FindBy(xpath = "//a[contains(@href,'/login')]")
    private WebElement signupLoginLink;

    @FindBy(xpath = "//a[contains(text(),' Delete Account')]")
    private WebElement deleteAccountLink;

    @FindBy(xpath = "//a[contains(@href, '/logout')]")
    private WebElement logoutLink;

    // A stable element to verify the page has loaded
    @FindBy(className = "features_items")
    private WebElement featuresItemsSection;

    @FindBy(xpath = "//img[@alt='Website for automation practice']")
    private WebElement logo;


    // Sửa Constructor: Tự lấy driver từ DriverManager
    public HomePage() {
        // Khởi tạo PageFactory để kích hoạt các @FindBy
        PageFactory.initElements(DriverManager.getDriver(), this);
        // Khởi tạo wait để tránh lỗi NullPointerException
        this.wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
    }

    public SignupLoginPage clickSignupLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(signupLoginLink)).click();
        return new SignupLoginPage();
    }


    public boolean isFeaturesItemsVisible() {
        return wait.until(ExpectedConditions.visibilityOf(featuresItemsSection)).isDisplayed();
    }


    public boolean isLoggedInAsTextVisible() {
        // Wait for the element to be visible and then check its display status
        return wait.until(ExpectedConditions.visibilityOf(loggedInAsText)).isDisplayed();
    }

    public SignupLoginPage clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
        return new SignupLoginPage();
    }

    public String getTitle() {
        return DriverManager.getDriver().getTitle();
    }

    public boolean isPageVisible(){
        try{
            return wait.until(ExpectedConditions.visibilityOf(logo)).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

    public boolean isLoggedInAs(String expectedName) {
        System.out.println("Actual text: "+loggedInAsText.getText());
        return loggedInAsText != null && loggedInAsText.getText().equals("Logged in as " + expectedName);
    }

    public AccountDeletedPage clickDeleteAccount(){
        wait.until(ExpectedConditions.elementToBeClickable(deleteAccountLink)).click();
        return new AccountDeletedPage();
    }
}
