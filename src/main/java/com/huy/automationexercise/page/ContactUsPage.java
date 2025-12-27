package com.huy.automationexercise.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    // TODO: Upload file testcase
    @Step("Upload file to contact us form")
    public void uploadFile(String filePath){
        uploadFile(inputFileField, filePath);
    }



}
