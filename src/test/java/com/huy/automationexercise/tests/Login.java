package com.huy.automationexercise.tests;
import com.huy.automationexercise.common.BaseTest;
import com.huy.automationexercise.driver.*;
import com.huy.automationexercise.page.*;
import com.huy.automationexercise.utils.TestDataUtil;
import com.huy.automationexercise.utils.UserData;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Login extends BaseTest {
    @Test
    public void testLoginSuccessfullyWithCorrectUserAndPassword() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isPageVisible(), "Trang chưa hiển thị được");
        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
        Assert.assertTrue(signupLoginPage.isSignupFormDisplayed(), "New user signup text is not visible");
        UserData user = TestDataUtil.generateUser();
        RegisterPage registerPage = signupLoginPage.signup(user.getFirstName()+" "+user.getLastName(), user.getEmail());
        Assert.assertTrue(registerPage.isAccountInformationTitleVisible(), "Account information title is not visible");
        registerPage.enterAccountInfo(user);
        registerPage.selectCheckboxes();
        registerPage.enterAddressInfo(user);

    }

//    @Test
//    public void testLoginSuccessfullyWithCorrectUserAndPassword() {
//        // --- ARRANGE: Create a new user using the reusable workflow ---
//        UserData newUserData = UserFlows.registerNewUser(driver);
//
//        // Verify we are logged in after registration
//        HomePage homePage = new HomePage(driver);
//        Assert.assertTrue(homePage.isLoggedInAsTextVisible(), "'Logged in as' should be visible after registration.");
//
//        // --- ACT: Log out and then log back in ---
//
//        // 1. Log out
//        SignupLoginPage signupLoginPage = homePage.clickLogout();
//
//        // 2. Log back in with the credentials of the user we just created
//        homePage = signupLoginPage.login(newUserData.getEmail(), newUserData.getPassword());
//
//        // --- ASSERT: Verify successful login ---
//        Assert.assertTrue(homePage.isLoggedInAsTextVisible(), "'Logged in as' text should be visible after logging back in.");
//
//
//    }

//    @Test
//    public void testLoginUnsuccessfullyWithIncorrectEmailAndPassword() {
//        UserData unexistUserData = TestDataUtil.generateUnexistUser();
//        HomePage homePage = new HomePage(driver);
//
//        // Hard Assertion: Verify that the Home Page is visible successfully
//        Assert.assertTrue(homePage.isFeaturesItemsVisible(), "Home page should be visible.");
//
//        // Navigate to login page
//        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
//
//        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/h2")).isDisplayed(), "Login form should be displayed.");
//
////        Assert.assertTrue(signupLoginPage.isLoginFormDisplayed(), "Login form should be displayed.");
//
//        signupLoginPage.login(unexistUserData.getEmail(), unexistUserData.getPassword());
//
//        Assert.assertTrue(driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div/form/p")).isDisplayed(), "Error message should be displayed.");
//
//        driver.quit();
//    }
}
