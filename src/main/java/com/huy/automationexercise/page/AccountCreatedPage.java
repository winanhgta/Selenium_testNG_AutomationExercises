package com.huy.automationexercise.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreatedPage {
    private WebDriver driver;

    @FindBy(xpath = "//b[normalize-space()='Account Created!']")
    private WebElement accountCreatedMessage;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueButton;

    public AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isAccountCreatedMessageDisplayed() {
        return accountCreatedMessage.isDisplayed();
    }

    public HomePage clickContinue() {
        continueButton.click();
        return new HomePage(driver);
    }
}
