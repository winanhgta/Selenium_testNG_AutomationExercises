package com.huy.automationexercise.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

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

    @FindBy(xpath = "//a[text()=' Contact us']")
    private WebElement contactUsLink;


    // Sửa Constructor: Tự lấy driver từ DriverManager
    public HomePage() {
        super();
    }

    @Step("Click and navigate to Sign up/login page")
    public SignupLoginPage clickSignupLogin() {
        clickToElement(signupLoginLink);
        return new SignupLoginPage();
    }

    @Step("Verify home page is visible")
    public boolean isPageVisible(){
        try{
            return wait.until(ExpectedConditions.visibilityOf(logo)).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

    @Step("Verify that user is logged in as {expectedName}")
    public boolean isLoggedInAs(String expectedName) {
        System.out.println("Actual text: "+loggedInAsText.getText());
        return loggedInAsText != null && loggedInAsText.getText().equals("Logged in as " + expectedName);
    }

    @Step("Click and navigate to Delete Account page")
    public AccountDeletedPage clickDeleteAccount(){
        clickToElement(deleteAccountLink);
        return new AccountDeletedPage();
    }

    @Step("Click logout and navigate to sign up/login page")
    public SignupLoginPage clickLogout(){
        clickToElement(logoutLink);
        return new SignupLoginPage();
    }

    @Step("Click Contact us and navigate to contact us page")
    public ContactUsPage clickContactUs(){
        clickToElement(contactUsLink);
        return new ContactUsPage();
    }
}
