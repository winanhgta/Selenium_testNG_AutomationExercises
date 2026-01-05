package com.huy.automationexercise.page;

import com.huy.automationexercise.driver.DriverManager;
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
//TODO: Finish detail page testcase
    @Step("Verify that user is landed to product detail page")
    public boolean isDetailPageLanded() {
        wait.until(ExpectedConditions.urlContains("/product_details/"));
        return Objects.requireNonNull(DriverManager.getDriver().getCurrentUrl()).contains("/product_details/" + index);
    }

    @Step("Verify that detail is visible: product name, category, price, availability, condition, brand")
    public boolean isDetailVisible(){
        return isDisplayed(productName) && isDisplayed(productCategory) && isDisplayed(productPrice) && isDisplayed(productAvailability) && isDisplayed(productCondition) && isDisplayed(productBrand);
    }



}
