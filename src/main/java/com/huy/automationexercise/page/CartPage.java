package com.huy.automationexercise.page;

import io.qameta.allure.Step;
import net.datafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{
    public CartPage(){
        super();
    }

    @FindBy(xpath = "//h2[text()='Subscription']")
    private WebElement subscriptionTitle;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement subscribeEmailInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement subscribeButton;

    @FindBy(xpath = "//div[text()='You have been successfully subscribed!']")
    private WebElement subscribeMessage;

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

}
