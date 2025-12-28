package com.huy.automationexercise.tests;

import com.huy.automationexercise.TestListener.TestListener;
import com.huy.automationexercise.common.BaseTest;
import com.huy.automationexercise.utils.EmailData;
import com.huy.automationexercise.utils.TestDataUtil;
import com.huy.automationexercise.utils.UserData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.huy.automationexercise.page.*;

@Listeners({TestListener.class}) // Gọi Listener để chụp ảnh khi fail
@Epic("Regression Test")
@Feature("User Management")
@Story("Create and Delete Account Flow")

public class AccountLifeCycle extends BaseTest {
    @Test
    public void testLoginSuccessfullyWithCorrectUserAndPassword() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isPageVisible(), "Home page is not visible");
        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
        Assert.assertTrue(signupLoginPage.isSignupFormDisplayed(), "New user signup text is not visible");
        UserData user = TestDataUtil.generateUser();
        RegisterPage registerPage = signupLoginPage.signup(user.getFirstName() + " " + user.getLastName(), user.getEmail());
        Assert.assertTrue(registerPage.isAccountInformationTitleVisible(), "Account information title is not visible");
        registerPage.enterAccountInfo(user);
        registerPage.selectCheckboxes();
        registerPage.enterAddressInfo(user);
        AccountCreatedPage accountCreatedPage = registerPage.clickCreateAccount();
        Assert.assertTrue(accountCreatedPage.isAccountCreatedVisible(), "Account created title is not visible");
        homePage = accountCreatedPage.clickContinueButton();
        Assert.assertTrue(homePage.isLoggedInAs(user.getFirstName() + " " + user.getLastName()), "Logged in as text is not visible");
        AccountDeletedPage accountDeletedPage = homePage.clickDeleteAccount();
        Assert.assertTrue(accountDeletedPage.isDeletedTitleVisible(), "Account deleted title is not visible");
        homePage = accountDeletedPage.clickContinue();
    }

    @Test
    public void testLoginSuccessfullyWithCorrectEmailAndPassword() throws InterruptedException {
        HomePage homePage = new HomePage();

        //Create account first
        Assert.assertTrue(homePage.isPageVisible(), "Home page is not visible");
        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
        UserData user = TestDataUtil.generateUser();
        RegisterPage registerPage = signupLoginPage.signup(user.getFirstName() + " " + user.getLastName(), user.getEmail());
        registerPage.enterAccountInfo(user);
        registerPage.selectCheckboxes();
        registerPage.enterAddressInfo(user);
        AccountCreatedPage accountCreatedPage = registerPage.clickCreateAccount();
        homePage = accountCreatedPage.clickContinueButton();
        signupLoginPage = homePage.clickLogout();

        //Login and delete account

        Assert.assertTrue(signupLoginPage.isLoginToYourAccountVisible(), "Login to your account text is not visible");
        homePage = signupLoginPage.login(user.getEmail(),user.getPassword());
        Assert.assertTrue(homePage.isLoggedInAs(user.getFirstName() + " " + user.getLastName()), "Logged in as text is not visible");
        AccountDeletedPage accountDeletedPage = homePage.clickDeleteAccount();
        Assert.assertTrue(accountDeletedPage.isDeletedTitleVisible(), "Account deleted title is not visible");
    }

    @Test
    public void testLoginUserUnsuccessfullyWithIncorrectEmailAndPassword(){
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isPageVisible(), "Home page is not visible");
        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
        Assert.assertTrue(signupLoginPage.isLoginToYourAccountVisible(), "Login to your account text is not visible");
        UserData user = TestDataUtil.generateUser();
        signupLoginPage.loginExpectingError(user.getEmail(),user.getPassword());
        Assert.assertTrue(signupLoginPage.isIncorrectEmailPasswordVisible(), "Incorrect email or password is not visivle");
    }

    @Test
    public void testLogoutUser(){
        HomePage homePage = new HomePage();

        //Create account first
        Assert.assertTrue(homePage.isPageVisible(), "Home page is not visible");
        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
        UserData user = TestDataUtil.generateUser();
        RegisterPage registerPage = signupLoginPage.signup(user.getFirstName() + " " + user.getLastName(), user.getEmail());
        registerPage.enterAccountInfo(user);
        registerPage.selectCheckboxes();
        registerPage.enterAddressInfo(user);
        AccountCreatedPage accountCreatedPage = registerPage.clickCreateAccount();
        homePage = accountCreatedPage.clickContinueButton();
        signupLoginPage = homePage.clickLogout();

        //Login and logout account
        Assert.assertTrue(signupLoginPage.isLoginToYourAccountVisible(), "Login to your account text is not visible");
        homePage = signupLoginPage.login(user.getEmail(),user.getPassword());
        Assert.assertTrue(homePage.isLoggedInAs(user.getFirstName() + " " + user.getLastName()), "Logged in as text is not visible");
        signupLoginPage = homePage.clickLogout();
        Assert.assertTrue(signupLoginPage.isSignUpLoginPageVisible(), "User is not navigated to signup/login page");
    }

    @Test
    public void testRegisterUserWithExistingEmail() {
        HomePage homePage = new HomePage();

        //Create account first
        Assert.assertTrue(homePage.isPageVisible(), "Home page is not visible");
        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
        UserData user = TestDataUtil.generateUser();
        RegisterPage registerPage = signupLoginPage.signup(user.getFirstName() + " " + user.getLastName(), user.getEmail());
        registerPage.enterAccountInfo(user);
        registerPage.selectCheckboxes();
        registerPage.enterAddressInfo(user);
        AccountCreatedPage accountCreatedPage = registerPage.clickCreateAccount();
        homePage = accountCreatedPage.clickContinueButton();
        signupLoginPage = homePage.clickLogout();
        homePage = signupLoginPage.clickLogo();

        //Register with existing email
        signupLoginPage = homePage.clickSignupLogin();
        signupLoginPage.signup(user.getFirstName()+" "+user.getLastName(), user.getEmail());
        Assert.assertTrue(signupLoginPage.isEmailExistNotiVisible(), "Email is existing notification is not visible");
    }

    @Test
    public void testContactUsForm(){
        UserData user = TestDataUtil.generateUser();
        EmailData email = TestDataUtil.emailGenerate();

        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isPageVisible(), "Home page is not visible");
        ContactUsPage contactUsPage = homePage.clickContactUs();
        Assert.assertTrue(contactUsPage.isGetInTouchVisible(), "Get in touch title is not visible");
        contactUsPage.enterContactUsForm(user.getFirstName(), user.getEmail(), email.getSubject(), email.getContent());
        contactUsPage.uploadFile("testImage.png");
        contactUsPage.clickSubmitButton();
        contactUsPage.acceptAlert();
        Assert.assertTrue(contactUsPage.isStatusAlertVisible(), "Status alert is not visible");
        homePage = contactUsPage.clickHomeButton();
        Assert.assertTrue(homePage.isPageVisible(), "Home page is not visible");
    }

}
