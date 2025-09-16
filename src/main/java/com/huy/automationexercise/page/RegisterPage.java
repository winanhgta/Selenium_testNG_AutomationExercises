package com.huy.automationexercise.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;

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

    // --- Checkboxes ---
    @FindBy(id = "newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(id = "optin")
    private WebElement specialOffersCheckbox;

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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // --- Verification ---
    public boolean isAccountInformationTitleVisible() {
        // Check for the visibility of a stable element on the page, like the password field.
        // This confirms we are on the correct page.
        return wait.until(ExpectedConditions.visibilityOf(passwordInput)).isDisplayed();
    }

    // --- Actions ---
    public void selectTitle(String title) {
        wait.until(ExpectedConditions.elementToBeClickable(mrRadio));
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

    public void selectCheckboxes() {
        newsletterCheckbox.click();
        specialOffersCheckbox.click();
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
        wait.until(ExpectedConditions.elementToBeClickable(createAccountButton)).click();
        return new AccountCreatedPage(driver); // This now correctly returns the next page
    }
}
