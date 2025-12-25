package com.huy.automationexercise.page;

import com.huy.automationexercise.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.huy.automationexercise.driver.DriverManager;

import java.time.Duration;

public class SignupLoginPage {

    private WebDriverWait wait;

    // --- Signup Form ---
    @FindBy(name = "name")
    private WebElement nameField ;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement emailField;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    private WebElement signupButton;

    @FindBy(xpath = "//h2[text()='New User Signup!']")
    private WebElement newUserSignupText;

    // --- Login Form ---
    private By loginEmailField = By.xpath("//input[@data-qa='login-email']");
    private By loginPasswordField = By.xpath("//input[@data-qa='login-password']");
    private By loginButton = By.xpath("//button[@data-qa='login-button']");

    public SignupLoginPage() {
        // Khởi tạo PageFactory để kích hoạt các @FindBy
        PageFactory.initElements(DriverManager.getDriver(), this);
        // Khởi tạo wait để tránh lỗi NullPointerException
        this.wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
    }

    /**
     * Logs into the application using the provided credentials.
     * @param email The user's email.
     * @param password The user's password.
     * @return A new instance of the HomePage.
     */
    public HomePage login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginEmailField)).sendKeys(email);
        DriverManager.getDriver().findElement(loginPasswordField).sendKeys(password);
        DriverManager.getDriver().findElement(loginButton).click();
        return new HomePage();
    }

    public boolean isNewUserSignupDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(newUserSignupText)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSignupFormDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(nameField)).isDisplayed();
    }

    public RegisterPage signup(String name, String email) {
        wait.until(ExpectedConditions.visibilityOf(nameField)).sendKeys(name);
        emailField.sendKeys(email);
        signupButton.click();
        return new RegisterPage();
    }

    public boolean isLoginFormDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginEmailField)).isDisplayed();
    }
}