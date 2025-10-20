package com.huy.automationexercise.tests;
import com.huy.automationexercise.base.BaseTest;
import com.huy.automationexercise.page.*;
import com.huy.automationexercise.utils.UserData;
import com.huy.automationexercise.utils.UserFlows;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login extends BaseTest {

    @Test
    public void testLoginSuccessfullyWithCorrectUserAndPassword() {
        // --- ARRANGE: Create a new user using the reusable workflow ---
        UserData newUserData = UserFlows.registerNewUser(driver);

        // Verify we are logged in after registration
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isLoggedInAsTextVisible(), "'Logged in as' should be visible after registration.");

        // --- ACT: Log out and then log back in ---

        // 1. Log out
        SignupLoginPage signupLoginPage = homePage.clickLogout();

        // 2. Log back in with the credentials of the user we just created
        homePage = signupLoginPage.login(newUserData.getEmail(), newUserData.getPassword());

        // --- ASSERT: Verify successful login ---
        Assert.assertTrue(homePage.isLoggedInAsTextVisible(), "'Logged in as' text should be visible after logging back in.");
    }
}
