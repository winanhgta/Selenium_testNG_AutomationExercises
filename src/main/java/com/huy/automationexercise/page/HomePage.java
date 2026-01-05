package com.huy.automationexercise.page;

import io.qameta.allure.Step;
import net.datafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[contains(.,'Logged in as')]")
    private WebElement loggedInAsText;

    @FindBy(xpath = "//a[contains(@href,'/login')]")
    private WebElement signupLoginLink;

    @FindBy(xpath = "//a[contains(text(),' Delete Account')]")
    private WebElement deleteAccountLink;

    @FindBy(xpath = "//a[contains(@href, '/logout')]")
    private WebElement logoutLink;

    // A stable element to verify the page has loaded
    @FindBy(className = "features_items")
    private WebElement featuresItemsSection;

    @FindBy(xpath = "//img[@alt='Website for automation practice']")
    private WebElement logo;

    @FindBy(xpath = "//a[text()=' Contact us']")
    private WebElement contactUsLink;

    @FindBy(xpath = "//li/a[contains(@href, 'test_cases')]")
    private WebElement testCasesLink;

    @FindBy(xpath = "//a[@href='/products']")
    private WebElement productsLink;

    @FindBy(xpath = "//h2[text()='Subscription']")
    private WebElement subscriptionTitle;

    @FindBy(xpath = "//input[@id='susbscribe_email']")
    private WebElement subcribeEmailInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement subcribeButton;

    @FindBy(xpath = "//div[text()='You have been successfully subscribed!']")
    private WebElement subcribeMessage;


    // Sửa Constructor: Tự lấy driver từ DriverManager
    public HomePage() {
        super();
    }

    @Step("Click and navigate to Sign up/login page")
    public SignupLoginPage clickSignupLogin() {
        clickToElement(signupLoginLink);
        return new SignupLoginPage();
    }

    @Step("Verify home page is visible")
    public boolean isPageVisible(){
        try{
            return wait.until(ExpectedConditions.visibilityOf(logo)).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

    @Step("Verify that user is logged in as {expectedName}")
    public boolean isLoggedInAs(String expectedName) {
        System.out.println("Actual text: "+loggedInAsText.getText());
        return loggedInAsText != null && loggedInAsText.getText().equals("Logged in as " + expectedName);
    }

    @Step("Click and navigate to Delete Account page")
    public AccountDeletedPage clickDeleteAccount(){
        clickToElement(deleteAccountLink);
        return new AccountDeletedPage();
    }

    @Step("Click logout and navigate to sign up/login page")
    public SignupLoginPage clickLogout(){
        clickToElement(logoutLink);
        return new SignupLoginPage();
    }

    @Step("Click Contact us and navigate to contact us page")
    public ContactUsPage clickContactUs(){
        clickToElement(contactUsLink);
        return new ContactUsPage();
    }

    @Step("Click testcases link")
    public TestCasesPage clickTestCases(){
        clickToElement(testCasesLink);
        return new TestCasesPage();
    }

    @Step("Click all products link")
    public ProductsPage clickAllProducts(){
        clickToElement(productsLink);
        return new ProductsPage();
    }

    @Step("Verify that subscription title is visible")
    public boolean isSubscriptionTitleVisible(){
        scrollToElement(subscriptionTitle);
        return isDisplayed(subscriptionTitle);
    }

    @Step("Enter email and click subscribe button")
    public void subcribeEmail(){
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        sendKeysToElement(subcribeEmailInput, email);
        clickToElement(subcribeButton);
    }

    @Step("Verify that success message 'You have been successfully subscribed!' is visible")
    public boolean isSubcribeMessageVisible(){
        return isDisplayed(subcribeMessage);
    }
}
