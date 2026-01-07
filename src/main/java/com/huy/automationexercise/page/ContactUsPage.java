package com.huy.automationexercise.page;

import com.huy.automationexercise.driver.DriverManager;
import com.huy.automationexercise.utils.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.io.File;

public class ContactUsPage extends BasePage{

    @FindBy(xpath = "//h2[text()='Get In Touch']")
    private WebElement getInTouchTitle;

    @FindBy(xpath = "//input[@data-qa='name']")
    private WebElement inputNameField;

    @FindBy(xpath = "//input[@data-qa='email']")
    private WebElement inputEmailField;

    @FindBy(xpath = "//input[@data-qa='subject']")
    private WebElement inputSubjectField;

    @FindBy(xpath = "//textarea[@data-qa='message']")
    private WebElement inputMessageField;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement inputFileField;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[contains(@class, 'status')]")
    private WebElement statusAlert;

    @FindBy(xpath = "//a[contains(@class, 'btn-success') and contains(., 'Home')]")
    private WebElement homeButton;

    public ContactUsPage(){
        super();
    }

    @Step("Verify that GET IN TOUCH title is visible")
    public boolean isGetInTouchVisible(){
        return isDisplayed(getInTouchTitle);
    }

    @Step("Enter {name}, {email}, {subject}, {message} to contact us form")
    public void enterContactUsForm(String name, String email, String subject, String message){
        inputNameField.sendKeys(name);
        inputEmailField.sendKeys(email);
        inputSubjectField.sendKeys(subject);
        inputMessageField.sendKeys(message);
    }

    @Step("Upload file to contact us form")
    public void uploadFile(String fileName){
        // Chỉ truyền tên file, còn đường dẫn thư mục chứa file thì để cố định trong framework
        String fullPath = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "test" + File.separator + "resources" +
                File.separator + "testdata" + File.separator + fileName;

        uploadFile(inputFileField, fullPath);
    }

    @Step("Click submit button")
    public void clickSubmitButton(){
        WebUI.removeAds();
        clickToElement(submitButton);
    }

    @Step("Accept alert")
    public void acceptAlert(){
        try {
            isAlertDisplayed();
            DriverManager.getDriver().switchTo().alert().accept();
        } catch (Exception e) {
            throw new RuntimeException("Error: Could not accept Alert. Detail: " + e.getMessage());
        }
    }

    @Step("Verify that success message is visible")
    public boolean isStatusAlertVisible(){
        return isDisplayed(statusAlert);
    }

    @Step("Click Home button and redirect to home page successfully")
    public HomePage clickHomeButton(){
        clickToElement(homeButton);
        return new HomePage();
    }
}
