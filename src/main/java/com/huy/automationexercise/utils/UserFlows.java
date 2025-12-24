//package com.huy.automationexercise.utils;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//
//public class UserFlows {
//
//    /**
//     * A reusable workflow that registers a new user from the home page.
//     * It performs the registration and verifies the account creation.
//     *
//     * @param driver The WebDriver instance.
//     * @return The UserData object containing the details of the newly created user.
//     */
//    public static UserData registerNewUser(WebDriver driver) {
//        // Generate unique user data for this registration
//        UserData userData = TestDataUtil.generateUser();
//
//        // Start from the home page
//        HomePage homePage = new HomePage(driver);
//        Assert.assertTrue(homePage.isFeaturesItemsVisible(), "Home page should be visible before starting registration.");
//
//        // Navigate to signup page
//        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
//        Assert.assertTrue(signupLoginPage.isSignupFormDisplayed(), "Signup form should be displayed.");
//
//        // Enter signup details and navigate to registration page
//        RegisterPage registerPage = signupLoginPage.signup(userData.getFirstName(), userData.getEmail());
//        Assert.assertTrue(registerPage.isAccountInformationTitleVisible(), "User should be navigated to the registration page.");
//
//        // Fill the entire registration form
//        registerPage.selectTitle("Mr");
//        registerPage.enterAccountInfo(userData.getFirstName(), userData.getPassword(), "10", "May", "1990");
//        registerPage.selectCheckboxes();
//        registerPage.enterAddressInfo(
//                userData.getFirstName(), userData.getLastName(), userData.getCompany(),
//                userData.getAddress1(), userData.getAddress2(), userData.getCountry(),
//                userData.getState(), userData.getCity(), userData.getZipCode(),
//                userData.getMobileNumber()
//        );
//
//        // Create account and verify
//        AccountCreatedPage accountCreatedPage = registerPage.clickCreateAccount();
//        Assert.assertTrue(accountCreatedPage.isAccountCreatedTitleVisible(), "'ACCOUNT CREATED!' message should be visible.");
//        accountCreatedPage.clickContinue();
//
//        return userData;
//    }
//}