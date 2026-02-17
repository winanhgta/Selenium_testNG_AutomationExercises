package com.huy.automationexercise.page;

import io.qameta.allure.Step;
import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[contains(.,'Logged in as')]")
    private WebElement loggedInAsText;

    @FindBy(xpath = "//a[contains(@href,'/login')]")
    private WebElement signupLoginLink;

    @FindBy(xpath = "//a[contains(text(),' Delete Account')]")
    private WebElement deleteAccountLink;

    @FindBy(xpath = "//a[contains(@href, '/logout')]")
    private WebElement logoutLink;

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
    private WebElement subscribeEmailInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement subscribeButton;

    @FindBy(xpath = "//div[text()='You have been successfully subscribed!']")
    private WebElement subscribeMessage;

    @FindBy(xpath = "//a[@href='/view_cart']")
    private WebElement cartButton;

    @FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4']")
    private List<WebElement> allProducts;

    @FindBy(xpath = "//a[text()='View Product']")
    private WebElement viewProductButton;

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

    @Step("Scroll down and verify that subscription title is visible")
    public boolean isSubscriptionTitleVisible(){
        scrollToElement(subscriptionTitle);
        return isDisplayed(subscriptionTitle);
    }

    @Step("Enter email and click subscribe button")
    public void subscribeEmail(){
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        System.out.println("Email: "+email);
        sendKeysToElement(subscribeEmailInput, email);
        clickToElement(subscribeButton);
    }

    @Step("Verify that success message 'You have been successfully subscribed!' is visible")
    public boolean isSubscribeMessageVisible(){
        return isDisplayed(subscribeMessage);
    }

    @Step("Click cart button and navigate to cart page")
    public CartPage clickCartButton(){
        clickToElement(cartButton);
        return new CartPage();
    }

    @Step("Click 'View Product' for any product on home page")
    public ProductDetailsPage clickViewProduct(){
        if (!allProducts.isEmpty()) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(allProducts.size());
            WebElement targetProduct = allProducts.get(randomIndex);
            scrollToElement(targetProduct);
            WebElement viewButton = targetProduct.findElement(By.xpath("//a[@href='/product_details/" + randomIndex + "' and contains(text(), 'View Product')]"));
            clickToElement(viewButton);
            return new ProductDetailsPage(randomIndex);
        } else {
            throw new RuntimeException("Product list is empty, can not click a random product");
        }
    }
}
