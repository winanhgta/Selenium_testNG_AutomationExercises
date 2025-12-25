package com.huy.automationexercise.page;

import com.huy.automationexercise.driver.DriverManager;
import com.huy.automationexercise.utils.UserData;
import net.datafaker.Faker;
import net.datafaker.providers.base.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AccountCreatedPage {
    private WebDriverWait wait;

    @FindBy(xpath = "//h2/b[text()='Account Created!']")
    private WebElement accountCreatedTitle;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueButton;


    // --- Constructor ---
    public AccountCreatedPage() {
        // Khởi tạo PageFactory để kích hoạt các @FindBy
        PageFactory.initElements(DriverManager.getDriver(), this);
        // Khởi tạo wait để tránh lỗi NullPointerException
        this.wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
    }

    public boolean isAccountCreatedVisible(){
        return wait.until(ExpectedConditions.visibilityOf(accountCreatedTitle)).isDisplayed();
    }

    public HomePage clickContinueButton(){
        wait.until(ExpectedConditions.visibilityOf(continueButton)).click();
        return new HomePage();
    }
}
