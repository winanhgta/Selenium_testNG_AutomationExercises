package com.huy.automationexercise.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountDeletedPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//b[text()='Account Deleted!']")
    private WebElement accountDeletedTitle;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueButton;

    public AccountDeletedPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isAccountDeletedTitleVisible() {
        return wait.until(ExpectedConditions.visibilityOf(accountDeletedTitle)).isDisplayed();
    }

    public HomePage clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        return new HomePage(driver);
    }
}