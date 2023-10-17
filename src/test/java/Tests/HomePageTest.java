package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class HomePageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        logInForStandardUser();
    }

    @Test
    public void twitterPageCanBeOpened() {
        homePage.clickOnTwitterLogo();
        ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabList.get(1));

        Assert.assertEquals(driver.getCurrentUrl(), socialNetworkPage.twitterPageUrl());
        Assert.assertEquals(socialNetworkPage.twitterPage.getText(), "Sauce Labs");
    }
    @Test
    public void facebookPageCanBeOpened() {
        homePage.clickOnFacebookLogo();
        ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabList.get(1));

        Assert.assertEquals(driver.getCurrentUrl(), socialNetworkPage.facebookPageUrl());
        Assert.assertTrue(socialNetworkPage.facebookWindow.isDisplayed());
        Assert.assertTrue(socialNetworkPage.facebookPage.isDisplayed());
        Assert.assertEquals(socialNetworkPage.facebookPage.getText(), "Sauce Labs");
    }
    @Test
    public void linkedInPageCanBeOpened() {
        homePage.clickOnLinkedInLogo();
        ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabList.get(1));

        Assert.assertEquals(driver.getCurrentUrl(), socialNetworkPage.linkedInPageUrl());
        Assert.assertTrue(socialNetworkPage.linkedInWindow.isDisplayed());
        Assert.assertTrue(socialNetworkPage.linkedInPage.isDisplayed());
        Assert.assertEquals(socialNetworkPage.linkedInProfile.getText(), "Sauce Labs");
    }
    @Test
    public void aboutPageCanBeOpened() {
        homePage.clickOnMenuButton();
        menuPage.clickOnAboutButton();

        Assert.assertTrue(menuPage.aboutPage.isDisplayed());
        Assert.assertFalse(isElementDisplayed(homePage.inventoryContainer));
    }
}
