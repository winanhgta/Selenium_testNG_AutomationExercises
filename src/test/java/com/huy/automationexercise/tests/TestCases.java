package com.huy.automationexercise.tests;

import com.huy.automationexercise.TestListener.AllureManagerListener;
import com.huy.automationexercise.common.BaseTest;
import com.huy.automationexercise.utils.EmailData;
import com.huy.automationexercise.utils.TestDataUtil;
import com.huy.automationexercise.utils.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.huy.automationexercise.page.*;

@Epic("Regression Test")
@Feature("User Management")
@Story("Create and Delete Account Flow")

public class TestCases extends BaseTest {

    @Test
    @Description("Test register user and delete user account successfully")
    public void testRegisterAndDeleteUserAccountSuccessfully() {
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
    @Description("Test login user successfully with correct email and password")
    public void testLoginSuccessfullyWithCorrectEmailAndPassword(){
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
    @Description("Test login user unsuccessfully with incorrect email and password")
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
    @Description("Test logout user successfully")
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
    @Description("Test register user with existing email")
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
    @Description("Test contact us form")
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

    @Test
    @Description("Test verify test cases page")
    public void testVerifyTestCasesPage() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isPageVisible(), "Home page is not visible");
        TestCasesPage testCasesPage = homePage.clickTestCases();
        Assert.assertTrue(testCasesPage.isCurrentUrl(), "User is navigated to test cases page unsuccessfully");
    }

    @Test
    @Description("Test verify all products and product detail page")
    public void testVerifyAllProductAndProductDetailPage(){
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isPageVisible(), "Home page is not visible");
        ProductsPage allProductsPage = homePage.clickAllProducts();
        Assert.assertTrue(allProductsPage.isProductsListVisible(), "Products list is not visible");
        ProductDetailsPage productDetailsPage = allProductsPage.clickViewProductButton(1);
        Assert.assertTrue(productDetailsPage.isDetailPageLanded(), "User is not landed to product detail page");
        Assert.assertTrue(productDetailsPage.isDetailVisible(), "Product detail is not visible");
    }

    @Test
    @Description("Test search product")
    public void testSearchProduct(){
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isPageVisible(), "Home page is not visible");
        ProductsPage productsPage = homePage.clickAllProducts();
        Assert.assertTrue(productsPage.isProductPageLanded(), "User is navigated to ALL PRODUCTS page unsuccessfully");
        productsPage.searchedProduct();
        Assert.assertTrue(productsPage.isSearchedProductsTitleVisible(),"Searched Products title is not visible");
        Assert.assertTrue(productsPage.areAllSearchedProductsVisible(), "All the products related to search are not visible");
    }

    @Test
    @Description("Verify subscription in home page")
    public void testSubcription(){
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isPageVisible(), "Home page is not visible");
        Assert.assertTrue(homePage.isSubscriptionTitleVisible(),"Subscription title is not visible");
        homePage.subcribeEmail();
        Assert.assertTrue(homePage.isSubcribeMessageVisible(), "Success message 'You have been successfully subscribed!' is not visible");
    }

}
