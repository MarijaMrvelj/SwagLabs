package Tests;

import Base.BaseTest;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class HomePageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        logIn("standard_user");
    }

    @Test
    public void aboutPageCanBeOpened() {
        headerSectionPage.clickOnMenuButton();
        sidebarMenuPage.clickOnAboutButton();

        Assert.assertTrue(sidebarMenuPage.aboutPage.isDisplayed());
        Assert.assertFalse(isElementDisplayed(homePage.inventoryContainer));
        Assert.assertEquals(driver.getTitle(), "Sauce Labs: Cross Browser Testing, Selenium Testing & Mobile Testing");
    }
    @Test
    public void allItemsCanBeDisplayedFromSidebarMenu() {
        headerSectionPage.clickOnCart();
        headerSectionPage.clickOnMenuButton();
        sidebarMenuPage.clickOnAllItemsButton();

        Assert.assertTrue(homePage.inventoryContainer.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), homePage.homePageUrl());
    }
    @Test
    public void appStateCanBeReset() {
        addRandomItem(homePage.allItemsNames());
        addRandomItem(homePage.allItemsNames());
        headerSectionPage.clickOnMenuButton();
        sidebarMenuPage.clickOnResetAppStateButton();
        driver.navigate().refresh();

        Assert.assertTrue(homePage.removeButtons.isEmpty());
        Assert.assertFalse(isElementDisplayed(headerSectionPage.cartValue));
    }
    @Test
    public void pricesCanBeSortedLowToHigh() {
        //System.out.println(allPricesList(homePage.prices));
        Select sortItems = new Select(homePage.sortDropdown);
        sortItems.selectByVisibleText("Price (low to high)");
        //System.out.println(allPricesList(homePage.prices));

        Assert.assertTrue(homePage.isSortedLowToHigh(allPricesList(homePage.prices)));
    }
    @Test
    public void pricesCanBeSortedHighToLow() {
        //System.out.println(allPricesList(homePage.prices));
        Select sortItems = new Select(homePage.sortDropdown);
        sortItems.selectByVisibleText("Price (high to low)");
        //System.out.println(allPricesList(homePage.prices));

        Assert.assertTrue(homePage.isSortedHighToLow(allPricesList(homePage.prices)));
    }
    @Test
    public void itemsCanBeSortedByNameZToA() {
        //System.out.println(homePage.allItemsNames());
        Select sortItems = new Select(homePage.sortDropdown);
        sortItems.selectByVisibleText("Name (Z to A)");
        //System.out.println(homePage.allItemsNames());

        Assert.assertEquals(homePage.allItemsNames(), homePage.sortItemsNamesInReverseOrder());
    }
    @Test
    public void itemsCanBeSortedByNameAToZ() {
        //System.out.println(homePage.allItemsNames());
        Select sortItems = new Select(homePage.sortDropdown);
        sortItems.selectByVisibleText("Name (Z to A)");
        //System.out.println(homePage.allItemsNames());

        sortItems.selectByVisibleText("Name (A to Z)");
        //System.out.println(homePage.allItemsNames());

        Assert.assertEquals(homePage.allItemsNames(), homePage.sortItemsNamesInOrder());
    }
    @Test
    public void allItemsContainRequiredElements() {
        Assert.assertEquals(homePage.itemNames.size(), 6);
        Assert.assertEquals(homePage.addToCartButtons.size(), 6);
        Assert.assertEquals(homePage.prices.size(), 6);
        Assert.assertTrue(homePage.doPricesHaveSameCurrency());
        Assert.assertEquals(homePage.itemsDescriptions.size(), 6);
        Assert.assertEquals(homePage.itemsImages.size(), 6);
    }
    @Test
    public void twitterPageCanBeOpened() {
        homePage.clickOnTwitterLogo();
        ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
        //driver.switchTo().window(tabList.get(3));
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

   /* @AfterMethod
    public void tabClose() {
        ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
        if(tabList.size() > 1)
            driver.switchTo().window(tabList.get(1)).close();
    }*/
}
