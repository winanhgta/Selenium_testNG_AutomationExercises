package com.huy.automationexercise.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
    private WebElement loggedInAsText;

    private By signupLoginLink = By.xpath("//a[contains(@href,'/login')]");

//    public HomePage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SignupLoginPage clickSignupLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(signupLoginLink)).click();
        return new SignupLoginPage(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isLoggedInAs(String expectedName) {
        return loggedInAsText.getText().contains(expectedName);
    }
}
