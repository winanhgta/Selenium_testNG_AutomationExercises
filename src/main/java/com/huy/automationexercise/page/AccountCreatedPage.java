package com.huy.automationexercise.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountCreatedPage extends BasePage {

    @FindBy(xpath = "//h2/b[text()='Account Created!']")
    private WebElement accountCreatedTitle;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueButton;

    // --- Constructor ---
    public AccountCreatedPage() {
        super();
    }

    @Step("Verify that the account created title is visible")
    public boolean isAccountCreatedVisible(){
        return isDisplayed(accountCreatedTitle);
    }

    @Step("Click continue button and navigate to Home page")
    public HomePage clickContinueButton(){
        clickToElement(continueButton);
        return new HomePage();
    }
}
