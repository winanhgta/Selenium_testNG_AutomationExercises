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

    @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
    private WebElement loggedInAsText;

    @FindBy(xpath = "//a[contains(@href,'/login')]")
    private WebElement signupLoginLink;

    @FindBy(xpath = "//a[contains(@href, '/delete_account')]")
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

    /**
     * Verifies that the home page is visible by checking for the "Features Items" section.
     * @return true if the section is visible, false otherwise.
     */
    public boolean isFeaturesItemsVisible() {
        return wait.until(ExpectedConditions.visibilityOf(featuresItemsSection)).isDisplayed();
    }

    /**
     * Verifies that the 'Logged in as' text is visible on the page.
     * @return true if the text element is visible, false otherwise.
     */
    public boolean isLoggedInAsTextVisible() {
        // Wait for the element to be visible and then check its display status
        return wait.until(ExpectedConditions.visibilityOf(loggedInAsText)).isDisplayed();
    }

    /**
     * Clicks the 'Delete Account' link and navigates to the confirmation page.
     * @return A new instance of the AccountDeletedPage.
     */
//    public AccountDeletedPage clickDeleteAccount() {
//        wait.until(ExpectedConditions.elementToBeClickable(deleteAccountLink)).click();
//        return new AccountDeletedPage(DriverManager.getDriver());
//    }

    /**
     * Clicks the 'Logout' link.
     * @return A new instance of the SignupLoginPage.
     */
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
        // thêm check null-safe
        return loggedInAsText != null && loggedInAsText.getText().contains(expectedName);
    }
}
