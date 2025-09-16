package com.huy.automationexercise.tests;

import com.huy.automationexercise.base.BaseTest;
import com.huy.automationexercise.page.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterUser extends BaseTest {
    @Test
    public void testRegisterUserSuccessfully(){
        // HomePage → SignupLoginPage
        HomePage homePage = new HomePage(driver);

        // Hard Assertion: Verify that the Home Page is visible successfully
        Assert.assertTrue(homePage.isFeaturesItemsVisible(), "Home page should be visible.");

        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();

        Assert.assertTrue(signupLoginPage.isSignupFormDisplayed(),
                "Signup form should be displayed");

        // SignupLoginPage → RegisterPage
        String uniqueEmail = "huy" + System.currentTimeMillis() + "@mail.com";
        RegisterPage registerPage = signupLoginPage.signup("Huy", uniqueEmail);

        // Hard Assertion: Verify that the Register Page is loaded successfully
        Assert.assertTrue(registerPage.isAccountInformationTitleVisible(),
                "User should be navigated to the registration page.");
    }
}
