package com.huy.automationexercise.page;

import com.huy.automationexercise.constants.FrameworkConstants;
import com.huy.automationexercise.driver.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriverWait wait;

    public BasePage() {
        // Init PageFactory 1 lần duy nhất cho tất cả các trang
        PageFactory.initElements(DriverManager.getDriver(), this);
        this.wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT));
    }

    //Method to interact with element

    protected void clickToElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void sendKeysToElement(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element)).clear();
        element.sendKeys(value);
    }

    protected boolean isDisplayed(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    protected boolean isAlertDisplayed(){
        try{
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e){
            return false;
        }
    }

    protected void uploadFile(WebElement element, String filePath) {
        element.sendKeys(filePath);
    }

    protected boolean isCurrentUrlDisplayed(String url){
        return wait.until(ExpectedConditions.urlContains(url));
    }

    protected void scrollToElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            // scrollIntoView(true) sẽ đưa đỉnh element lên đỉnh màn hình
            // Nhưng dùng script dưới đây sẽ đưa element vào giữa màn hình (tối ưu hơn)
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        } catch (Exception e) {
            System.out.println("Could not scroll to element: " + e.getMessage());
        }
    }
}
