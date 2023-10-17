package Tests;

import Base.BaseTest;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        sidebarMenuPage.clickOnAboutButton();

        Assert.assertTrue(sidebarMenuPage.aboutPage.isDisplayed());
        Assert.assertFalse(isElementDisplayed(homePage.inventoryContainer));
        Assert.assertEquals(driver.getTitle(), "Sauce Labs: Cross Browser Testing, Selenium Testing & Mobile Testing");
    }
    @Test
    public void allItemsCanBeDisplayedFromSidebarMenu() {
        homePage.clickOnCart();
        homePage.clickOnMenuButton();
        sidebarMenuPage.clickOnAllItemsButton();

        Assert.assertTrue(homePage.inventoryContainer.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), homePage.homePageUrl());
    }
    @Test
    public void appStateCanBeReset() {
        isCartEmpty();
        homePage.clickOnAddToCartButton("Sauce Labs Onesie");
        homePage.clickOnAddToCartButton("Sauce Labs Fleece Jacket");
        homePage.clickOnMenuButton();
        sidebarMenuPage.clickOnResetAppStateButton();
        driver.navigate().refresh();

        Assert.assertTrue(homePage.removeButtons.isEmpty());
        Assert.assertFalse(isElementDisplayed(homePage.cartValue));
    }
    @Test
    public void pricesCanBeSortedLowToHigh() {
        System.out.println(homePage.allPrices());
        Select objSelect = new Select(homePage.sortDropdown);
        objSelect.selectByVisibleText("Price (low to high)");
        System.out.println(homePage.allPrices());

        Assert.assertTrue(homePage.isSortedLowToHigh(homePage.allPrices()));
    }
    @Test
    public void pricesCanBeSortedHighToLow() {
        System.out.println(homePage.allPrices());
        Select objSelect = new Select(homePage.sortDropdown);
        objSelect.selectByVisibleText("Price (high to low)");
        System.out.println(homePage.allPrices());

        Assert.assertTrue(homePage.isSortedHighToLow(homePage.allPrices()));
    }
    @Test
    public void itemsCanBeSortedByNameZToA() {
        System.out.println(homePage.allItemsNames());
        Select objSelect = new Select(homePage.sortDropdown);
        objSelect.selectByVisibleText("Name (Z to A)");
        System.out.println(homePage.allItemsNames());

        Assert.assertEquals(homePage.allItemsNames(), homePage.sortItemsNamesInReverseOrder());
    }
    @Test
    public void itemsCanBeSortedByNameAToZ() {
        System.out.println(homePage.allItemsNames());
        Select objSelect = new Select(homePage.sortDropdown);
        objSelect.selectByVisibleText("Name (Z to A)");
        System.out.println(homePage.allItemsNames());

        objSelect.selectByVisibleText("Name (A to Z)");
        System.out.println(homePage.allItemsNames());

        Assert.assertEquals(homePage.allItemsNames(), homePage.sortItemsNamesInOrder());
    }
}
