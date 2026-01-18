package com.huy.automationexercise.page;

import com.huy.automationexercise.driver.DriverManager;
import com.huy.automationexercise.models.ProductModel;
import com.huy.automationexercise.utils.TestDataUtil;
import io.qameta.allure.Step;
import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage{
    @FindBy(xpath = "//h2[text()='Subscription']")
    private WebElement subscriptionTitle;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement subscribeEmailInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement subscribeButton;

    @FindBy(xpath = "//div[text()='You have been successfully subscribed!']")
    private WebElement subscribeMessage;

    @FindBy(xpath = "//table[@class='table table-condensed']//tbody//tr[contains(@id, 'product')]")
    private WebElement cartRows;

    public CartPage(){
        super();
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

    @Step("Get all product in Cart")
    public List<ProductModel> getActualProductsInCart() {
        List<ProductModel> actualList = new ArrayList<>();
        List<WebElement> rows = DriverManager.getDriver().findElements(By.xpath("//table[@class='table table-condensed']//tbody//tr[contains(@id, 'product')]"));
        for (WebElement row : rows) {
            // Dùng Xpath tương đối (có dấu chấm đầu) để tìm bên trong từng 'row'
            String name = row.findElement(By.xpath(".//td[@class='cart_description']//a")).getText();
            String price = row.findElement(By.xpath(".//td[@class='cart_price']/p")).getText();
            String qty = row.findElement(By.xpath(".//td[@class='cart_quantity']/button")).getText();
            String total = row.findElement(By.xpath(".//td[@class='cart_total']/p")).getText();

            actualList.add(ProductModel.builder()
                    .description(name)
                    .rawPrice(Integer.parseInt(TestDataUtil.cleanNumber(price)))
                    .quantity(Integer.parseInt(qty))
                    .total(Integer.parseInt(TestDataUtil.cleanNumber(total)))
                    .build());
        }
        return actualList;
    }
}
