package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class E2ETest extends BaseTest {

    @Test(priority = 10)
    public void verifyThatUserCanLogIn() {
        driver.navigate().to("https://www.saucedemo.com/");
        logIn("standard_user");

        Assert.assertEquals(driver.getCurrentUrl(), homePage.homePageUrl());
        Assert.assertTrue(homePage.inventoryContainer.isDisplayed());
        Assert.assertFalse(isElementDisplayed(loginPage.loginButton));
    }

    @Test(priority = 15)
    public void pricesCanBeSortedLowToHigh() {
        //System.out.println(allPricesList(homePage.prices));
        Select sortItems = new Select(homePage.sortDropdown);
        sortItems.selectByVisibleText("Price (low to high)");
        //System.out.println(allPricesList(homePage.prices));

        Assert.assertTrue(homePage.isSortedLowToHigh(allPricesList(homePage.prices)));
    }

    @Test(priority = 20)
    public void userCanAddItemToCartFromHomepage() {
        isCartEmpty();
        homePage.clickOnAddToCartButton("Test.allTheThings() T-Shirt (Red)");

        Assert.assertFalse(homePage.removeButtons.isEmpty());
        Assert.assertTrue(driver.findElement(By.id(getRemoveButtonIdName("Test.allTheThings() T-Shirt (Red)"))).isDisplayed());
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 1);
    }

    @Test(priority = 25)
    public void userCanRemoveItemFromHomepage() {
        addItem("Sauce Labs Bolt T-Shirt");

        homePage.clickOnRemoveButton("Sauce Labs Bolt T-Shirt");
        Assert.assertTrue(driver.findElement(By.id(getAddToCartButtonIdName("Sauce Labs Bolt T-Shirt"))).isDisplayed());
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 1);

    }
    @Test(priority = 30)
    public void userCanAddItemToCartFromItemPage() {
        homePage.clickOnItemName("Sauce Labs Backpack");
        itemPage.clickOnAddToCartButton();

        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 2);
        Assert.assertTrue(itemPage.removeButton.isDisplayed());
    }
    @Test(priority = 35)
    public void userCanRemoveItemFromItemPage() {
        itemPage.clickOnRemoveButton();

        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 1);
        Assert.assertTrue(itemPage.addToCartButton.isDisplayed());
    }

    @Test(priority = 40)
    public void userCanRemoveItemFromCartPage() {
        itemPage.clickOnBackToProductsButton();
        homePage.clickOnAddToCartButton("Sauce Labs Onesie");
        headerSectionPage.clickOnCart();
        Assert.assertEquals(driver.getCurrentUrl(), yourCartPage.yourCartPageUrl());
        Assert.assertFalse(yourCartPage.cartItem.isEmpty());
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 2);

        yourCartPage.removeItem(0);
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 1);
        Assert.assertEquals(yourCartPage.cartItem.size(), 1);
    }
    @Test(priority = 45)
    public void userCanContinueShopping() {
        yourCartPage.clickOnContinueShoppingButton();
        addItem("Sauce Labs Fleece Jacket");

        Assert.assertTrue(homePage.inventoryContainer.isDisplayed());
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 2);
        Assert.assertEquals(driver.getCurrentUrl(), homePage.homePageUrl());
    }
    @Test(priority = 55)
    public void userCanCancelOnCheckoutInfo() {
        addItem("Sauce Labs Fleece Jacket");
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 1);

        headerSectionPage.clickOnCart();
        Assert.assertEquals(yourCartPage.cartItem.size(), 1);
        Assert.assertEquals(driver.getCurrentUrl(), yourCartPage.yourCartPageUrl());

        yourCartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.checkoutPageUrl());

        String firstName = excelReader.getStringData("CheckoutInfo", 1, 0);
        String lastName = excelReader.getStringData("CheckoutInfo", 1, 1);
        String postalCode = excelReader.getStringData("CheckoutInfo", 1, 2);

        checkoutPage.inputCheckoutInfo(firstName, lastName, postalCode);
        checkoutPage.clickOnCancelButton();

        Assert.assertEquals(driver.getCurrentUrl(), yourCartPage.yourCartPageUrl());
        Assert.assertTrue(yourCartPage.checkoutButton.isDisplayed());
        Assert.assertTrue(yourCartPage.continueShoppingButton.isDisplayed());
        Assert.assertEquals(yourCartPage.cartItem.size(), 1);
    }
    @Test(priority = 50)
    public void userCanRemoveItemsWithResetAppStateButton() {
        headerSectionPage.clickOnMenuButton();
        sidebarMenuPage.clickOnResetAppStateButton();
        driver.navigate().refresh();

        Assert.assertFalse(isElementDisplayed(headerSectionPage.cartValue));
        Assert.assertTrue(homePage.removeButtons.isEmpty());
    }

    @Test(priority = 60)
    public void userCanCancelOnCheckoutOverview() {
        yourCartPage.clickOnContinueShoppingButton();
        addItem("Sauce Labs Backpack");
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 2);

        headerSectionPage.clickOnCart();
        Assert.assertEquals(yourCartPage.cartItem.size(), 2);
        Assert.assertEquals(driver.getCurrentUrl(), yourCartPage.yourCartPageUrl());

        yourCartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.checkoutPageUrl());

        String firstName = excelReader.getStringData("CheckoutInfo", 1, 0);
        String lastName = excelReader.getStringData("CheckoutInfo", 1, 1);
        String postalCode = excelReader.getStringData("CheckoutInfo", 1, 2);

        checkoutPage.inputCheckoutInfo(firstName, lastName, postalCode);
        checkoutPage.clickOnContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), checkoutOverviewPage.checkoutOverviewPageUrl());

        checkoutOverviewPage.clickOnCancelButton();
        Assert.assertEquals(driver.getCurrentUrl(), homePage.homePageUrl());
    }
    @Test(priority = 65)
    public void userCanCheckout() {
        addItem("Test.allTheThings() T-Shirt (Red)");
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 3);

        headerSectionPage.clickOnCart();
        Assert.assertEquals(yourCartPage.cartItem.size(), 3);
        Assert.assertEquals(driver.getCurrentUrl(), yourCartPage.yourCartPageUrl());

        yourCartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.checkoutPageUrl());
        Assert.assertEquals(checkoutPage.checkoutTitle(), "Checkout: Your Information");

        String firstName = excelReader.getStringData("CheckoutInfo", 1, 0);
        String lastName = excelReader.getStringData("CheckoutInfo", 1, 1);
        String postalCode = excelReader.getStringData("CheckoutInfo", 1, 2);

        checkoutPage.inputCheckoutInfo(firstName, lastName, postalCode);
        checkoutPage.clickOnContinueButton();

        Assert.assertEquals(driver.getCurrentUrl(), checkoutOverviewPage.checkoutOverviewPageUrl());
        Assert.assertEquals(checkoutOverviewPage.checkoutTitle(), "Checkout: Overview");
        Assert.assertEquals(checkoutOverviewPage.getItemTotalPrice(), checkoutOverviewPage.calculateItemTotalPrice());
        Assert.assertEquals(checkoutOverviewPage.getTotal(), checkoutOverviewPage.calculateTotalPrice());

        checkoutOverviewPage.clickOnFinishButton();
        Assert.assertEquals(driver.getCurrentUrl(), checkoutCompletePage.checkoutCompletePageUrl());
        Assert.assertEquals(checkoutCompletePage.checkoutTitle(), "Checkout: Complete!");
        Assert.assertTrue(checkoutCompletePage.message.isDisplayed());
    }
    @Test(priority = 70)
    public void cartIsEmptyAfterCheckoutAndUserCanStartShopping() {
        checkoutCompletePage.clickOnBackHomeButton();
        Assert.assertFalse(isElementDisplayed(headerSectionPage.cartValue));
        Assert.assertEquals(driver.getCurrentUrl(), homePage.homePageUrl());
        Assert.assertTrue(homePage.inventoryContainer.isDisplayed());
    }

    @Test(priority = 75)
    public void verifyThatUserCanLogOut() {
        headerSectionPage.clickOnMenuButton();
        sidebarMenuPage.clickOnLogoutButton();

        Assert.assertEquals(driver.getCurrentUrl(), loginPage.loginPageUrl());
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }
}
