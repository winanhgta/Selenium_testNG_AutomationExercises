package com.huy.automationexercise.page;

import com.huy.automationexercise.driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.huy.automationexercise.driver.DriverManager;

import java.time.Duration;

public class SignupLoginPage {

    private final WebDriverWait wait;

    // --- Signup Form ---
    @FindBy(name = "name")
    private WebElement nameField ;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement emailField;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    private WebElement signupButton;

    @FindBy(xpath = "//h2[text()='New User Signup!']")
    private WebElement newUserSignupText;

    @FindBy(xpath = "//h2[text()='Login to your account']")
    private WebElement loginToYourAccountTitle;

    @FindBy(xpath = "//input[@data-qa='login-email']")
    private WebElement loginEmailField;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    private WebElement loginPasswordField;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[text()='Your email or password is incorrect!']")
    private WebElement incorrectEmailPasswordNoti;

    @FindBy(xpath = "//a[contains(@href, '/logout')]")
    private WebElement logoutLink;

    @FindBy(xpath = "//p[text()='Email Address already exist!']")
    private WebElement emailExistNoti;

    @FindBy(xpath = "//img[@alt='Website for automation practice']")
    private WebElement logo;

    // --- Constructor ---

    public SignupLoginPage() {
        // Khởi tạo PageFactory để kích hoạt các @FindBy
        PageFactory.initElements(DriverManager.getDriver(), this);
        // Khởi tạo wait để tránh lỗi NullPointerException
        this.wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
    }

    @Step("Login with correct {email} and {password} and navigate to Home page")
    public HomePage login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(loginEmailField)).sendKeys(email);
        loginPasswordField.sendKeys(password);
        loginButton.click();
        return new HomePage();
    }

    @Step("Login with incorrect {email} and {password} and navigate to Home page")
    public void loginExpectingError(String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(loginEmailField)).sendKeys(email);
        loginPasswordField.sendKeys(password);
        loginButton.click();
    }

    @Step("Verify that the signup form is displayed")
    public boolean isSignupFormDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(nameField)).isDisplayed();
    }

    @Step("Signup with {name} and {email} and navigate to Register page")
    public RegisterPage signup(String name, String email) {
        wait.until(ExpectedConditions.visibilityOf(nameField)).sendKeys(name);
        emailField.sendKeys(email);
        signupButton.click();
        return new RegisterPage();
    }

    @Step("Signup with {name} and existing {email} and display error message")
    public void signupWithExistingEmail(String name, String email) {
        wait.until(ExpectedConditions.visibilityOf(nameField)).sendKeys(name);
        emailField.sendKeys(email);
        signupButton.click();
    }

    @Step("Verify that login to your account text is visible")
    public boolean isLoginToYourAccountVisible(){
        return wait.until(ExpectedConditions.visibilityOf(loginToYourAccountTitle)).isDisplayed();
    }

    @Step("Verify that incorrect email or password notification is visible")
    public boolean isIncorrectEmailPasswordVisible(){
        return wait.until(ExpectedConditions.visibilityOf(incorrectEmailPasswordNoti)).isDisplayed();
    }

    @Step("Verify that user is currently navigated to signup/login page")
    public boolean isSignUpLoginPageVisible(){
        return DriverManager.getDriver().getTitle().equals("Automation Exercise - Signup / Login");
    }

    @Step("Navigate to home page")
    public HomePage clickLogo(){
        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
        return new HomePage();
    }

    @Step("Verify that email is existing notification is visible")
    public boolean isEmailExistNotiVisible(){
        return wait.until(ExpectedConditions.visibilityOf(emailExistNoti)).isDisplayed();
    }

}