package com.huy.automationexercise.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SignupLoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By nameField = By.name("name");
    private By emailField = By.xpath("//input[@data-qa='signup-email']");
    private By signupButton = By.xpath("//button[@data-qa='signup-button']");

    public SignupLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isSignupFormDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).isDisplayed();
    }

    public RegisterPage signup(String name, String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(signupButton).click();
        return new RegisterPage(driver);
    }
}