package com.huy.automationexercise.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends BasePage{

    @FindBy(xpath = "//div[@class='features_items']")
    private WebElement featuresItemsSection;

    @FindBy(xpath = "//div[@class='col-sm-4']")
    private List<WebElement> allProducts;

    @FindBy(xpath = "//a[text()='View Product']")
    private WebElement viewProductButton;

    public ProductsPage() {
        super();
    }

    @Step("Verify that products list is visible")
    public boolean isProductsListVisible(){
        if (isDisplayed(featuresItemsSection) && !allProducts.isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    @Step("Click view product button of {index} product")
    public ProductDetailsPage clickViewProductButton(int index){
        WebElement firstProduct = allProducts.get(index);
        WebElement viewButton = firstProduct.findElement(By.xpath(".//a[text()='View Product']"));
        scrollToElement(viewButton);
        clickToElement(viewButton);
        return new ProductDetailsPage(index);
    }


}
