//package com.huy.automationexercise.tests;
//
//import com.huy.automationexercise.page.*;
//import com.huy.automationexercise.utils.TestDataUtil;
//import com.huy.automationexercise.utils.UserData;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//public class RegisterUser {
//    @Test
//    public void testRegisterUserSuccessfully(){
//
//        // Create fake user data
//        UserData userData = TestDataUtil.generateUser();
//
//        // HomePage → SignupLoginPage
//        HomePage homePage = new HomePage(driver);
//
//        // Hard Assertion: Verify that the Home Page is visible successfully
//        Assert.assertTrue(homePage.isFeaturesItemsVisible(), "Home page should be visible.");
//
//        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
//
//        Assert.assertTrue(signupLoginPage.isSignupFormDisplayed(),
//                "Signup form should be displayed");
//
//        // SignupLoginPage → RegisterPage
//        RegisterPage registerPage = signupLoginPage.signup(userData.getFirstName(), userData.getEmail());
//
//        // Hard Assertion: Verify that the Register Page is loaded successfully
//        Assert.assertTrue(registerPage.isAccountInformationTitleVisible(),
//                "User should be navigated to the registration page.");
//
//        // --- Fill details: Title, Name, Email, Password, Date of birth ---
//        registerPage.selectTitle("Mr");
//        registerPage.enterAccountInfo(userData.getFirstName(), userData.getPassword(), "10", "May", "1990");
//
//        // --- Select checkboxes ---
//        registerPage.selectCheckboxes();
//
//        // --- Fill details: Address Information ---
//        registerPage.enterAddressInfo(
//                userData.getFirstName(),
//                userData.getLastName(),
//                userData.getCompany(),
//                userData.getAddress1(),
//                userData.getAddress2(),
//                userData.getCountry(),
//                userData.getState(),
//                userData.getCity(),
//                userData.getZipCode(),
//                userData.getMobileNumber()
//        );
//
//        // --- Click 'Create Account Button'
//        AccountCreatedPage accountCreatedPage = registerPage.clickCreateAccount();
//
//        // Hard Assertion: Verify that 'ACCOUNT CREATED!' is visible
//        Assert.assertTrue(accountCreatedPage.isAccountCreatedTitleVisible(), "'ACCOUNT CREATED!' message should be visible.");
//
//        // --- Click 'Continue' button ---
//        homePage = accountCreatedPage.clickContinue();
//
//        // Hard Assertion: Verify that 'Logged in as username' is visible
//        Assert.assertTrue(homePage.isLoggedInAsTextVisible(), "'Logged in as' text should be visible after registration.");
//
//        // --- Click 'Delete Account' button ---
//        AccountDeletedPage accountDeletedPage = homePage.clickDeleteAccount();
//
//        // Hard Assertion: Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
//        Assert.assertTrue(accountDeletedPage.isAccountDeletedTitleVisible(), "'ACCOUNT DELETED!' message should be visible.");
//        accountDeletedPage.clickContinue();
//    }
//}
