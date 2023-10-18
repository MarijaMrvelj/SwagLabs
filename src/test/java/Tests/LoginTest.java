package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test
    public void verifyThatStandardUserCanLogIn() {
        logInForStandardUser();

        Assert.assertEquals(driver.getCurrentUrl(), homePage.homePageUrl());
        Assert.assertTrue(homePage.inventoryContainer.isDisplayed());
        Assert.assertFalse(isElementDisplayed(loginPage.loginButton));
    }
    @Test
    public void verifyThatProblemUserCanLogIn() {
        logIn("problem_user");

        Assert.assertEquals(driver.getCurrentUrl(), homePage.homePageUrl());
        Assert.assertTrue(homePage.inventoryContainer.isDisplayed());
        Assert.assertFalse(isElementDisplayed(loginPage.loginButton));
    }
    @Test
    public void verifyThatLockedOutUserCanLogIn() {
        logIn("locked_out_user");

        Assert.assertEquals(driver.getCurrentUrl(), homePage.homePageUrl());
        Assert.assertTrue(homePage.inventoryContainer.isDisplayed());
        Assert.assertFalse(isElementDisplayed(loginPage.loginButton));
    }
    @Test
    public void verifyThatPerformanceGlitchUserCanLogIn() {
        logIn("performance_glitch_user");

        Assert.assertEquals(driver.getCurrentUrl(), homePage.homePageUrl());
        Assert.assertTrue(homePage.inventoryContainer.isDisplayed());
        Assert.assertFalse(isElementDisplayed(loginPage.loginButton));
    }
    @Test
    public void verifyThatErrorUserCanLogIn() {
        logIn("error_user");

        Assert.assertEquals(driver.getCurrentUrl(), homePage.homePageUrl());
        Assert.assertTrue(homePage.inventoryContainer.isDisplayed());
        Assert.assertFalse(isElementDisplayed(loginPage.loginButton));
    }
    @Test
    public void verifyThatVisualUserCanLogIn() {
        logIn("visual_user");

        Assert.assertEquals(driver.getCurrentUrl(), homePage.homePageUrl());
        Assert.assertTrue(homePage.inventoryContainer.isDisplayed());
        Assert.assertFalse(isElementDisplayed(loginPage.loginButton));
    }
    @Test
    public void verifyThatUserCanLogOut() {
        logInForStandardUser();
        headerSectionPage.clickOnMenuButton();
        sidebarMenuPage.clickOnLogoutButton();

        Assert.assertEquals(driver.getCurrentUrl(), loginPage.loginPageUrl());
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }

    @Test
    public void verifyThatUserCannotLogInWithInvalidPassword() {
        for (int i = 1; i < excelReader.getLastRow("Login"); i++) {
            String validUsername = excelReader.getStringData("Login", 1, 0);
            loginPage.inputUsername(validUsername);
            String invalidPassword = excelReader.getStringData("Login", i, 3);
            loginPage.inputPassword(invalidPassword);
            loginPage.clickOnLoginButton();

            Assert.assertTrue(loginPage.error.isDisplayed());
            Assert.assertTrue(loginPage.error.isDisplayed());
            Assert.assertEquals(driver.getCurrentUrl(), loginPage.loginPageUrl());
        }
    }

    @Test
    public void verifyThatUserCannotLogInWithInvalidUsername() {
        for (int i = 1; i < excelReader.getLastRow("Login"); i++) {
            String invalidUsername = excelReader.getStringData("Login", i, 2);
            loginPage.inputUsername(invalidUsername);
            String validPassword = excelReader.getStringData("Login", 1, 1);
            loginPage.inputPassword(validPassword);
            loginPage.clickOnLoginButton();

            Assert.assertTrue(loginPage.error.isDisplayed());
            Assert.assertTrue(loginPage.error.isDisplayed());
            Assert.assertEquals(driver.getCurrentUrl(), loginPage.loginPageUrl());
        }
    }
}
