package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        logInForStandardUser();
    }

    @Test
    public void userCanAddItemToCartFromHomepage() {
        isCartEmpty();
        homePage.clickOnAddToCartButton("Test.allTheThings() T-Shirt (Red)");

        Assert.assertFalse(homePage.removeButtons.isEmpty());
        Assert.assertTrue(driver.findElement(By.id(getRemoveButtonIdName("Test.allTheThings() T-Shirt (Red)"))).isDisplayed());
        Assert.assertEquals(homePage.cartValue.getText(), "1");
    }
    @Test
    public void userCanAddAnyItemToCartFromHomepage() {
        isCartEmpty();
        homePage.clickOnAddToCartButton("Sauce Labs Backpack");

        Assert.assertEquals(homePage.removeButtons.size(), 1);
        Assert.assertTrue(driver.findElement(By.id(getRemoveButtonIdName("Sauce Labs Backpack"))).isDisplayed());
        Assert.assertEquals(homePage.cartValue.getText(), "1");
    }

    @Test
    public void userCanAddItemToCartFromItemPage() {
        isCartEmpty();
        homePage.clickOnItemName("Sauce Labs Backpack");
        itemPage.clickOnAddToCartButton();

        Assert.assertEquals(homePage.cartValue.getText(), "1");
        Assert.assertTrue(itemPage.removeButton.isDisplayed());
    }

    @Test
    public void userCanSeeTheItemInTheCart() {
        isCartEmpty();
        homePage.clickOnAddToCartButton("Sauce Labs Backpack");
        homePage.clickOnCart();

        Assert.assertEquals(driver.getCurrentUrl(), yourCartPage.yourCartPageUrl());
        Assert.assertTrue(yourCartPage.cartItem.isDisplayed());
    }

    @Test
    public void userCanRemoveItemFromCartPage() {
        isCartEmpty();
        homePage.clickOnAddToCartButton("Test.allTheThings() T-Shirt (Red)");
        homePage.clickOnCart();
        Assert.assertEquals(driver.getCurrentUrl(), yourCartPage.yourCartPageUrl());
        Assert.assertTrue(yourCartPage.cartItem.isDisplayed());

        yourCartPage.removeItem(0);
        Assert.assertFalse(isElementDisplayed(homePage.cartValue));
        Assert.assertFalse(isElementDisplayed(yourCartPage.cartItem));
        Assert.assertEquals(yourCartPage.cartList.getText(), "QTYDescription");
    }

    @Test
    public void userCanRemoveItemFromHomepage() {
        isCartEmpty();
        homePage.clickOnAddToCartButton("Sauce Labs Bolt T-Shirt");

        homePage.clickOnRemoveButton("Sauce Labs Bolt T-Shirt");
        Assert.assertFalse(isElementDisplayed(homePage.cartValue));
        Assert.assertTrue(driver.findElement(By.id(getAddToCartButtonIdName("Sauce Labs Bolt T-Shirt"))).isDisplayed());
    }

    @Test
    public void userCanRemoveItemFromItemPage() {
        isCartEmpty();
        homePage.clickOnAddToCartButton("Sauce Labs Backpack");
        homePage.clickOnItemName("Sauce Labs Backpack");
        itemPage.clickOnRemoveButton();

        Assert.assertFalse(isElementDisplayed(homePage.cartValue));
        Assert.assertTrue(itemPage.addToCartButton.isDisplayed());
    }
    @Test
    public void userCanRemoveItemWithResetAppStateButton() {
        isCartEmpty();
        homePage.clickOnItemName("Sauce Labs Bolt T-Shirt");
        itemPage.clickOnAddToCartButton();
        Assert.assertEquals(homePage.cartValue.getText(), "1");
        Assert.assertTrue(itemPage.removeButton.isDisplayed());

        homePage.clickOnMenuButton();
        sidebarMenuPage.clickOnResetAppStateButton();
        driver.navigate().refresh();

        Assert.assertFalse(isElementDisplayed(homePage.cartValue));
        Assert.assertTrue(itemPage.addToCartButton.isDisplayed());
    }

    @Test
    public void userCanContinueShopping() {
        homePage.clickOnCart();
        yourCartPage.clickOnContinueShoppingButton();

        Assert.assertEquals(driver.getCurrentUrl(), homePage.homePageUrl());
    }

}
