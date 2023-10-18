package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        logIn("standard_user");
    }

    @Test
    public void userCanAddItemToCartFromHomepage() {
        isCartEmpty();
        homePage.clickOnAddToCartButton("Test.allTheThings() T-Shirt (Red)");

        Assert.assertFalse(homePage.removeButtons.isEmpty());
        Assert.assertTrue(driver.findElement(By.id(getRemoveButtonIdName("Test.allTheThings() T-Shirt (Red)"))).isDisplayed());
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 1);
    }
    @Test
    public void userCanAddAnyItemToCartFromHomepage() {
        addRandomItem(homePage.allItemsNames());

        Assert.assertEquals(homePage.removeButtons.size(), 1);
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 1);
    }

    @Test
    public void userCanAddItemToCartFromItemPage() {
        isCartEmpty();
        homePage.clickOnItemName("Sauce Labs Backpack");
        itemPage.clickOnAddToCartButton();

        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 1);
        Assert.assertTrue(itemPage.removeButton.isDisplayed());
    }

    @Test
    public void userCanSeeTheItemInTheCart() {
        addItem("Sauce Labs Backpack");
        headerSectionPage.clickOnCart();

        Assert.assertEquals(driver.getCurrentUrl(), yourCartPage.yourCartPageUrl());
        Assert.assertEquals(yourCartPage.cartItem.size(), 1);
        Assert.assertEquals(yourCartPage.getItemName(0), "Sauce Labs Backpack");
    }

    @Test
    public void userCanRemoveItemFromCartPage() {
        addRandomItem(homePage.allItemsNames());
        headerSectionPage.clickOnCart();
        Assert.assertEquals(driver.getCurrentUrl(), yourCartPage.yourCartPageUrl());
        Assert.assertFalse(yourCartPage.cartItem.isEmpty());
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 1);

        yourCartPage.removeItem(0);
        Assert.assertFalse(isElementDisplayed(headerSectionPage.cartValue));
        Assert.assertTrue(yourCartPage.cartItem.isEmpty());
    }

    @Test
    public void userCanRemoveItemFromHomepage() {
        addItem("Sauce Labs Bolt T-Shirt");

        homePage.clickOnRemoveButton("Sauce Labs Bolt T-Shirt");
        Assert.assertFalse(isElementDisplayed(headerSectionPage.cartValue));
        Assert.assertTrue(driver.findElement(By.id(getAddToCartButtonIdName("Sauce Labs Bolt T-Shirt"))).isDisplayed());
    }

    @Test
    public void userCanRemoveItemFromItemPage() {
        isCartEmpty();
        homePage.clickOnAddToCartButton("Sauce Labs Backpack");
        homePage.clickOnItemName("Sauce Labs Backpack");
        itemPage.clickOnRemoveButton();

        Assert.assertFalse(isElementDisplayed(headerSectionPage.cartValue));
        Assert.assertTrue(itemPage.addToCartButton.isDisplayed());
    }
    @Test
    public void userCanRemoveItemWithResetAppStateButton() {
        addRandomItem(homePage.allItemsNames());
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 1);
        Assert.assertFalse(homePage.removeButtons.isEmpty());

        headerSectionPage.clickOnMenuButton();
        sidebarMenuPage.clickOnResetAppStateButton();
        driver.navigate().refresh();

        Assert.assertFalse(isElementDisplayed(headerSectionPage.cartValue));
        Assert.assertTrue(homePage.removeButtons.isEmpty());
    }

    @Test
    public void userCanContinueShopping() {
        addRandomItem(homePage.allItemsNames());
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 1);

        headerSectionPage.clickOnCart();
        yourCartPage.clickOnContinueShoppingButton();
        addRandomItem(homePage.allItemsNames());

        Assert.assertTrue(homePage.inventoryContainer.isDisplayed());
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 2);
        Assert.assertEquals(driver.getCurrentUrl(), homePage.homePageUrl());
    }

    @AfterMethod
    public void pageReset() {
        headerSectionPage.clickOnMenuButton();
        sidebarMenuPage.clickOnResetAppStateButton();
        sidebarMenuPage.clickOnLogoutButton();
    }
}
