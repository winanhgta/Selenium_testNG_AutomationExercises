package com.huy.automationexercise.page;

import com.huy.automationexercise.driver.DriverManager;
import com.huy.automationexercise.utils.UserData;
import io.qameta.allure.Step;
import net.datafaker.Faker;
import net.datafaker.providers.base.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {
    private WebDriverWait wait;

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
        // Khởi tạo PageFactory để kích hoạt các @FindBy
        PageFactory.initElements(DriverManager.getDriver(), this);
        // Khởi tạo wait để tránh lỗi NullPointerException
        this.wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
    }

    @Step("Verify that the account information title is visible")
    public boolean isAccountInformationTitleVisible() {
        return wait.until(ExpectedConditions.visibilityOf(accountInformationTitle)).isDisplayed();
    }

    @Step("Enter account information")
    public void enterAccountInfo(UserData user) {
        wait.until(ExpectedConditions.elementToBeClickable(mrRadio));
        Faker faker = new Faker();
        if (faker.number().numberBetween(0, 1) == 0) {
            mrRadio.click(); // Nếu là 0 thì chọn Mr.
        } else {
            mrsRadio.click(); // Nếu là 1 thì chọn Mrs.
        }
        nameInput.clear();
        nameInput.sendKeys(user.getFirstName()+" "+user.getLastName());
        passwordInput.sendKeys(user.getPassword());
        Select selectDay = new Select(dayDropdown);
        Select selectMonth = new Select(monthDropdown);
        Select selectYear = new Select(yearDropdown);
        selectDay.selectByVisibleText(user.getDay());
        selectMonth.selectByVisibleText(user.getMonth());
        selectYear.selectByVisibleText(user.getYear());
    }

    @Step("Select 2 checkboxes")
    public void selectCheckboxes() {
        newsletterCheckbox.click();
        specialOffersCheckbox.click();
    }

    @Step("Enter address information")
    public void enterAddressInfo(UserData user) {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        firstNameInput.sendKeys(user.getFirstName());
        lastNameInput.sendKeys(user.getLastName());
        companyInput.sendKeys(user.getCompany());
        address1Input.sendKeys(user.getAddress1());
        address2Input.sendKeys(user.getAddress2());
        Select select = new Select(countryDropdown);
        select.selectByValue(user.getCountry().trim());
        stateInput.sendKeys(user.getState());
        cityInput.sendKeys(user.getCity());
        zipcodeInput.sendKeys(user.getZipCode());
        mobileNumberInput.sendKeys(user.getMobileNumber());
    }

    @Step("Click and navigate to Account Created page")
    public AccountCreatedPage clickCreateAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(createAccountButton)).click();
        return new AccountCreatedPage(); // This now correctly returns the next page
    }
}
