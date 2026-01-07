package com.huy.automationexercise.page;

import com.huy.automationexercise.utils.UserData;
import io.qameta.allure.Step;
import net.datafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends BasePage {

    @FindBy(xpath = "//h2/b[text()='Enter Account Information']")
    private WebElement accountInformationTitle;

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

    @FindBy(xpath = "//select[@data-qa='country']")
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
    public RegisterPage() {
        super();
    }

    @Step("Verify that the account information title is visible")
    public boolean isAccountInformationTitleVisible() {
        return wait.until(ExpectedConditions.visibilityOf(accountInformationTitle)).isDisplayed();
    }

    @Step("Enter account information")
    public void enterAccountInfo(UserData user) {
        Faker faker = new Faker();
        if (faker.number().numberBetween(0, 1) == 0) {
            clickToElement(mrRadio);
        } else {
            clickToElement(mrsRadio);
        }
        sendKeysToElement(nameInput,user.getFirstName()+" "+user.getLastName());
        sendKeysToElement(passwordInput, user.getPassword());
        Select selectDay = new Select(dayDropdown);
        Select selectMonth = new Select(monthDropdown);
        Select selectYear = new Select(yearDropdown);
        selectDay.selectByVisibleText(user.getDay());
        selectMonth.selectByVisibleText(user.getMonth());
        selectYear.selectByVisibleText(user.getYear());
    }

    @Step("Select 2 checkboxes {newsletterCheckbox} and {specialOffersCheckbox}")
    public void selectCheckboxes() {
        scrollToElement(newsletterCheckbox);
        clickToElement(newsletterCheckbox);
        clickToElement(specialOffersCheckbox);
    }

    @Step("Enter address information")
    public void enterAddressInfo(UserData user) {
        sendKeysToElement(firstNameInput, user.getFirstName());
        sendKeysToElement(lastNameInput, user.getLastName());
        sendKeysToElement(companyInput, user.getCompany());
        sendKeysToElement(address1Input, user.getAddress1());
        sendKeysToElement(address2Input, user.getAddress2());

        Select select = new Select(countryDropdown);
        select.selectByValue(user.getCountry().trim());

        sendKeysToElement(stateInput, user.getState());
        sendKeysToElement(cityInput, user.getCity());
        sendKeysToElement(zipcodeInput, user.getZipCode());
        sendKeysToElement(mobileNumberInput, user.getMobileNumber());
    }

    @Step("Click and navigate to Account Created page")
    public AccountCreatedPage clickCreateAccount() {
        clickToElement(createAccountButton);
        return new AccountCreatedPage(); // This now correctly returns the next page
    }
}
