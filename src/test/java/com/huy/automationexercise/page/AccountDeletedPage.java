package com.huy.automationexercise.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.huy.automationexercise.driver.DriverManager;

import java.time.Duration;

public class AccountDeletedPage {
    private WebDriverWait wait;

    @FindBy(xpath = "//b[text()='Account Deleted!']")
    private WebElement accountDeletedTitle;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueButton;

    // --- Constructor ---
    public AccountDeletedPage() {
        // Khởi tạo PageFactory để kích hoạt các @FindBy
        PageFactory.initElements(DriverManager.getDriver(), this);
        // Khởi tạo wait để tránh lỗi NullPointerException
        this.wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
    }

    @Step("Verify that the account deleted title is visible")
    public boolean isDeletedTitleVisible(){
        return wait.until(ExpectedConditions.visibilityOf(accountDeletedTitle)).isDisplayed();
    }

    @Step("Click continue button and navigate to Home page")
    public HomePage clickContinue(){
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        return new HomePage();
    }
}
