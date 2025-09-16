package com.huy.automationexercise.tests;

import com.huy.automationexercise.base.BaseTest;
import com.huy.automationexercise.page.HomePage;
import com.huy.automationexercise.page.SignupLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test
    public void testOpenHomePageAndClickSignupLogin() {
        // HomePage đã được mở sẵn từ BaseTest.setUp()
        HomePage homePage = new HomePage(driver);

        // Click Signup/Login
        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();

        // Kiểm tra field name hiển thị chưa (signup form)
        Assert.assertTrue(signupLoginPage.isSignupFormDisplayed(),
                "Name field should be visible on Signup/Login page");
    }
}
