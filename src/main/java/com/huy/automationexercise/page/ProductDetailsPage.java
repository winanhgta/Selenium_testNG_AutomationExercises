package com.huy.automationexercise.page;

import com.huy.automationexercise.driver.DriverManager;
import com.huy.automationexercise.utils.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Objects;

public class ProductDetailsPage extends BasePage {
    private int index;

    @FindBy(xpath = "//div[@class='product-information']/h2")
    private WebElement productName;

    @FindBy(xpath = "//p[contains(text(),'Category')]")
    private WebElement productCategory;

    @FindBy(xpath = "//span[contains(text(), 'Rs')]")
    private WebElement productPrice;

    @FindBy(xpath = "//p/b[contains(text(), 'Availability')]")
    private WebElement productAvailability;

    @FindBy(xpath = "//p/b[contains(text(), 'Condition')]")
    private WebElement productCondition;

    @FindBy(xpath = "//p/b[contains(text(), 'Brand')]")
    private WebElement productBrand;

    @FindBy(xpath = "//input[@type='number']")
    private WebElement productQuantity;

    @FindBy(xpath = "//button[@class='btn btn-default cart']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[@class='modal-body']//p[@class='text-center']//a[@href='/view_cart']")
    private WebElement viewCartLink;


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ProductDetailsPage(int index) {
        super();
        this.index = index;
    }

    @Step("Verify that user is landed to product detail page")
    public boolean isDetailPageLanded() {
        WebUI.removeAds();
        wait.until(ExpectedConditions.urlContains("/product_details/"));
        return Objects.requireNonNull(DriverManager.getDriver().getCurrentUrl()).contains("/product_details/" + index);
    }

    @Step("Verify that detail is visible: product name, category, price, availability, condition, brand")
    public boolean isDetailVisible(){
        return isDisplayed(productName) && isDisplayed(productCategory) && isDisplayed(productPrice) && isDisplayed(productAvailability) && isDisplayed(productCondition) && isDisplayed(productBrand);
    }

    @Step("Increase product quantity to {{quantity}}")
    public void increaseQuanity(int quantity){
        sendKeysToElement(productQuantity, String.valueOf(quantity));
    }

    @Step("Click Add to Cart button")
    public void clickAddToCartButton(){
        clickToElement(addToCartButton);
    }

    @Step("Click View Cart link")
    public CartPage clickViewCartLink(){
        clickToElement(viewCartLink);
        return new CartPage();
    }




}
