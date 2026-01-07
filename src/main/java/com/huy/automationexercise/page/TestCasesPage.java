package com.huy.automationexercise.page;

import com.huy.automationexercise.driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Objects;

public class TestCasesPage extends BasePage {

    public TestCasesPage(){
        super();
    }

    @Step("Verify that user is navigated to testcases page successfully")
    public boolean isCurrentUrl() {
        wait.until(ExpectedConditions.urlContains("/test_cases"));
        return Objects.requireNonNull(DriverManager.getDriver().getCurrentUrl()).contains("/test_cases");
    }

}
