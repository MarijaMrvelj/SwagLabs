package Tests;

import Base.BaseTest;
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
        homePage.clickOnAddToCartBackpackButton();

        Assert.assertTrue(homePage.removeBackpackButton.isDisplayed());
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
        homePage.clickOnAddToCartBackpackButton();
        homePage.clickOnCart();

        Assert.assertEquals(driver.getCurrentUrl(), yourCartPage.yourCartPageUrl());
        Assert.assertTrue(yourCartPage.cartItem.isDisplayed());
    }

    @Test
    public void userCanRemoveItemFromCartPage() {
        isCartEmpty();
        homePage.clickOnAddToCartBackpackButton();
        homePage.clickOnCart();
        Assert.assertEquals(driver.getCurrentUrl(), yourCartPage.yourCartPageUrl());
        Assert.assertTrue(yourCartPage.cartItem.isDisplayed());

        yourCartPage.removeItem(0);
        Assert.assertFalse(isElementDisplayed(homePage.cartValue));
        Assert.assertEquals(yourCartPage.cartList.getText(), "QTYDescription");
    }

    @Test
    public void userCanRemoveItemFromHomepage() {
        isCartEmpty();
        homePage.clickOnAddToCartBackpackButton();

        homePage.clickOnRemoveBackpackButton();
        Assert.assertFalse(isElementDisplayed(homePage.cartValue));
        Assert.assertTrue(homePage.addToCartBackpackButton.isDisplayed());
    }

    @Test
    public void userCanRemoveItemFromItemPage() {
        isCartEmpty();
        homePage.clickOnAddToCartBackpackButton();
        homePage.clickOnItemName("Sauce Labs Backpack");
        itemPage.clickOnRemoveButton();

        Assert.assertFalse(isElementDisplayed(homePage.cartValue));
        Assert.assertTrue(itemPage.addToCartButton.isDisplayed());
        itemPage.clickOnBackToProductsButton();
        Assert.assertTrue(homePage.addToCartBackpackButton.isDisplayed());
    }

    @Test
    public void userCanContinueShopping() {
        homePage.clickOnCart();
        yourCartPage.clickOnContinueShoppingButton();

        Assert.assertEquals(driver.getCurrentUrl(), homePage.homePageUrl());
    }

}
