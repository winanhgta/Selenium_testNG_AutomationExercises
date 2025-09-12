package com.huy.automationexercise.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    private WebDriver driver;

    // --- Title ---
    @FindBy(id = "id_gender1")
    private WebElement mrRadio;

    @FindBy(id = "id_gender2")
    private WebElement mrsRadio;

    // --- Account Information ---
    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "days")
    private WebElement dayDropdown;

    @FindBy(id = "months")
    private WebElement monthDropdown;

    @FindBy(id = "years")
    private WebElement yearDropdown;

    // --- Address Information ---
    @FindBy(id = "first_name")
    private WebElement firstNameInput;

    @FindBy(id = "last_name")
    private WebElement lastNameInput;

    @FindBy(id = "company")
    private WebElement companyInput;

    @FindBy(id = "address1")
    private WebElement address1Input;

    @FindBy(id = "address2")
    private WebElement address2Input;

    @FindBy(id = "country")
    private WebElement countryDropdown;

    @FindBy(id = "state")
    private WebElement stateInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "zipcode")
    private WebElement zipcodeInput;

    @FindBy(id = "mobile_number")
    private WebElement mobileNumberInput;

    // --- Create Account button ---
    @FindBy(xpath = "//button[@data-qa='create-account']")
    private WebElement createAccountButton;

    // --- Constructor ---
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // --- Actions ---
    public void selectTitle(String title) {
        if (title.equalsIgnoreCase("Mr")) {
            mrRadio.click();
        } else {
            mrsRadio.click();
        }
    }

    public void enterAccountInfo(String name, String password, String day, String month, String year) {
        nameInput.clear();
        nameInput.sendKeys(name);
        passwordInput.sendKeys(password);
        dayDropdown.sendKeys(day);       // hoặc dùng Select class
        monthDropdown.sendKeys(month);
        yearDropdown.sendKeys(year);
    }

    public void enterAddressInfo(String firstName, String lastName, String company, String address1,
                                 String address2, String country, String state, String city,
                                 String zipcode, String mobile) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        companyInput.sendKeys(company);
        address1Input.sendKeys(address1);
        address2Input.sendKeys(address2);
        countryDropdown.sendKeys(country);
        stateInput.sendKeys(state);
        cityInput.sendKeys(city);
        zipcodeInput.sendKeys(zipcode);
        mobileNumberInput.sendKeys(mobile);
    }

    public AccountCreatedPage clickCreateAccount() {
        createAccountButton.click();
        return new AccountCreatedPage(driver);
    }
}
