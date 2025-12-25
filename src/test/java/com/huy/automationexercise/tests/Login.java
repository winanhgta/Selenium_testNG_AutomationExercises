package com.huy.automationexercise.tests;

import com.huy.automationexercise.TestListener.TestListener;
import com.huy.automationexercise.common.BaseTest;
import com.huy.automationexercise.driver.*;
import com.huy.automationexercise.page.*;
import com.huy.automationexercise.utils.TestDataUtil;
import com.huy.automationexercise.utils.UserData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class}) // Gọi Listener để chụp ảnh khi fail
@Epic("Regression Test")
@Feature("User Management")
@Story("Create and Delete Account Flow")

public class Login extends BaseTest {
    @Test
    public void testLoginSuccessfullyWithCorrectUserAndPassword() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isPageVisible(), "Trang chưa hiển thị được");
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

}
