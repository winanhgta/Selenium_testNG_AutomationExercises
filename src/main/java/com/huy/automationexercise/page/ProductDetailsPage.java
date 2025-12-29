package com.huy.automationexercise.page;

import com.huy.automationexercise.driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Objects;

public class ProductDetailsPage extends BasePage {
    private int index;

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



}
