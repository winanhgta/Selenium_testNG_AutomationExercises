package com.huy.automationexercise.page;

import com.huy.automationexercise.driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ProductsPage extends BasePage{

    Random rand = new Random();

    @FindBy(xpath = "//div[@class='features_items']")
    private WebElement featuresItemsSection;

    @FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4']")
    private List<WebElement> allProducts;

    @FindBy(xpath = "//div[@class='product-overlay']//div[@class='overlay-content']//a[@class='btn btn-default add-to-cart']")
    private WebElement addToCartButtonOnHover;

    @FindBy(xpath = "//div[@class='modal-footer']//button[text()='Continue Shopping']")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//div[@class='modal-body']//a[@href='/view_cart']")
    private WebElement viewCartButtonOnAddedMessage;

    @FindBy(xpath = "//a[text()='View Product']")
    private WebElement viewProductButton;

    @FindBy(xpath = "//h2[text()='Searched Products']")
    private WebElement searchedProductTitle;

    @FindBy(xpath = "//div[@class='productinfo text-center']/p")
    private WebElement productName;

    @FindBy(xpath = "//div[@class='productinfo text-center']/p")
    private List<WebElement> allProductsName;

    @FindBy(xpath = "//input[@id='search_product']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@id='submit_search']")
    private WebElement searchButton;

    @FindBy(xpath = "//h2[text()='Searched Products']")
    private WebElement searchedProductsTitle;

    private String productNameToSearch;

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

    @Step("Hover on the product position: {index} and click Add to cart")
    public void hoverOnProduct(int index){
        // if input is 1 -> index = 0. If input is 2 -> index = 1.
        if (index-1 < allProducts.size() && index-1 >= 0) {
            WebElement targetProduct = allProducts.get(index-1);
            scrollToElement(targetProduct);
            hoverOnElement(targetProduct);
            WebElement targetButton = targetProduct.findElement((By) addToCartButtonOnHover);
            clickToElement(targetButton);
        } else {
            throw new IllegalArgumentException("Index " + index + " is out of products list's range!");
        }
    }

    @Step("Click continue Shopping button")
    public void clickContinueShoppingButton(){
        clickToElement(continueShoppingButton);
    }

    @Step("Verify that user is landed to product detail page")
    public boolean isProductPageLanded() {
        wait.until(ExpectedConditions.urlContains("/products"));
        return Objects.requireNonNull(DriverManager.getDriver().getCurrentUrl()).contains("/products");
    }

    @Step("Click view product button of position: {index} product")
    public ProductDetailsPage clickViewProductButton(int index){
        WebElement targetProduct = allProducts.get(index);
        WebElement viewButton = targetProduct.findElement(By.xpath("//a[@href='/product_details/" + index + "' and contains(text(), 'View Product')]"));
        scrollToElement(viewButton);
        clickToElement(viewButton);
        return new ProductDetailsPage(index);
    }

    @Step("Find product name: {productNameToSearch} and click search button")
    public void searchedProduct(){
        int randomIndex = rand.nextInt(allProductsName.size());
        productNameToSearch = allProductsName.get(randomIndex).getText();
        scrollToElement(searchInput);
        sendKeysToElement(searchInput, productNameToSearch);
        clickToElement(searchButton);
    }

    @Step("Verify that Searched Products title is visible")
    public boolean isSearchedProductsTitleVisible(){
        return isDisplayed(searchedProductsTitle);
    }

    @Step("Verify that all the products related to search are visible")
    public boolean areAllSearchedProductsVisible(){
        System.out.println("Product searched name: "+productNameToSearch);
        if (allProductsName.isEmpty()) return false;
        for (WebElement element : allProductsName) {
            String name = element.getText().toLowerCase();
            System.out.println("Product in searched list: "+name);
            if (!name.contains(productNameToSearch.toLowerCase())) {
                return false;
            }
        }
        return true;
    }
}
