package com.huy.automationexercise.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountDeletedPage extends BasePage {

    @FindBy(xpath = "//b[text()='Account Deleted!']")
    private WebElement accountDeletedTitle;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueButton;

    // --- Constructor ---
    public AccountDeletedPage() {
        super();
    }

    @Step("Verify that the account deleted title is visible")
    public boolean isDeletedTitleVisible(){
        return isDisplayed(accountDeletedTitle);
    }

    @Step("Click continue button and navigate to Home page")
    public HomePage clickContinue(){
        clickToElement(continueButton);
        return new HomePage();
    }
}
