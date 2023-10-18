package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        logIn("standard_user");
    }

    @Test
    public void userCanCancelOnCheckoutInfo() {
        addItem("Test.allTheThings() T-Shirt (Red)");
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
    @Test
    public void userCanCancelOnCheckoutOverview() {
        addRandomItem(homePage.allItemsNames());
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
        checkoutPage.clickOnContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), checkoutOverviewPage.checkoutOverviewPageUrl());

        checkoutOverviewPage.clickOnCancelButton();
        Assert.assertEquals(driver.getCurrentUrl(), homePage.homePageUrl());
    }
    @Test
    public void userCanCheckout() {
        addRandomItem(homePage.allItemsNames());
        addRandomItem(homePage.allItemsNames());
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 2);

        headerSectionPage.clickOnCart();
        Assert.assertEquals(yourCartPage.cartItem.size(), 2);
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
    @Test
    public void cartIsEmptyAfterCheckoutAndUserCanStartShopping() {
        addRandomItem(homePage.allItemsNames());
        addRandomItem(homePage.allItemsNames());
        addRandomItem(homePage.allItemsNames());
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 3);

        headerSectionPage.clickOnCart();
        Assert.assertEquals(yourCartPage.cartItem.size(), 3);
        Assert.assertEquals(driver.getCurrentUrl(), yourCartPage.yourCartPageUrl());

        yourCartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.checkoutPageUrl());

        String firstName = excelReader.getStringData("CheckoutInfo", 1, 0);
        String lastName = excelReader.getStringData("CheckoutInfo", 1, 1);
        String postalCode = excelReader.getStringData("CheckoutInfo", 1, 2);

        checkoutPage.inputCheckoutInfo(firstName, lastName, postalCode);
        checkoutPage.clickOnContinueButton();

        Assert.assertEquals(driver.getCurrentUrl(), checkoutOverviewPage.checkoutOverviewPageUrl());
        Assert.assertEquals(checkoutOverviewPage.getItemTotalPrice(), checkoutOverviewPage.calculateItemTotalPrice());
        Assert.assertEquals(checkoutOverviewPage.getTotal(), checkoutOverviewPage.calculateTotalPrice());

        checkoutOverviewPage.clickOnFinishButton();
        Assert.assertEquals(driver.getCurrentUrl(), checkoutCompletePage.checkoutCompletePageUrl());
        Assert.assertTrue(checkoutCompletePage.message.isDisplayed());

        checkoutCompletePage.clickOnBackHomeButton();
        Assert.assertFalse(isElementDisplayed(headerSectionPage.cartValue));
        Assert.assertEquals(driver.getCurrentUrl(), homePage.homePageUrl());
        Assert.assertTrue(homePage.inventoryContainer.isDisplayed());
    }

    @Test
    public void userCannotCheckoutWithEmptyInfo() {
        addRandomItem(homePage.allItemsNames());
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 1);

        headerSectionPage.clickOnCart();
        Assert.assertEquals(driver.getCurrentUrl(), yourCartPage.yourCartPageUrl());

        yourCartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.checkoutPageUrl());

        checkoutPage.inputCheckoutInfo("", "", "");
        checkoutPage.clickOnContinueButton();

        Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.checkoutPageUrl());
        Assert.assertTrue(checkoutPage.error.isDisplayed());
    }
    @Test
    public void userCannotCheckoutWithEmptyFirstName() {
        addRandomItem(homePage.allItemsNames());
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 1);

        headerSectionPage.clickOnCart();
        Assert.assertEquals(driver.getCurrentUrl(), yourCartPage.yourCartPageUrl());

        yourCartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.checkoutPageUrl());

        String lastName = excelReader.getStringData("CheckoutInfo", 1, 1);
        String postalCode = excelReader.getStringData("CheckoutInfo", 1, 2);
        checkoutPage.inputCheckoutInfo("", lastName, postalCode);
        checkoutPage.clickOnContinueButton();

        Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.checkoutPageUrl());
        Assert.assertTrue(checkoutPage.error.isDisplayed());
        Assert.assertEquals(checkoutPage.errorMessage(), "Error: First Name is required");
    }
    @Test
    public void userCannotCheckoutWithEmptyLastName() {
        addRandomItem(homePage.allItemsNames());
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 1);

        headerSectionPage.clickOnCart();
        Assert.assertEquals(driver.getCurrentUrl(), yourCartPage.yourCartPageUrl());

        yourCartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.checkoutPageUrl());

        String firstName = excelReader.getStringData("CheckoutInfo", 1, 0);
        String postalCode = excelReader.getStringData("CheckoutInfo", 1, 2);
        checkoutPage.inputCheckoutInfo(firstName, "", postalCode);
        checkoutPage.clickOnContinueButton();

        Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.checkoutPageUrl());
        Assert.assertTrue(checkoutPage.error.isDisplayed());
        Assert.assertEquals(checkoutPage.errorMessage(), "Error: Last Name is required");
    }
    @Test
    public void userCannotCheckoutWithEmptyPostalCode() {
        addRandomItem(homePage.allItemsNames());
        Assert.assertEquals(headerSectionPage.numberOfItemsInCart(), 1);

        headerSectionPage.clickOnCart();
        Assert.assertEquals(driver.getCurrentUrl(), yourCartPage.yourCartPageUrl());

        yourCartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.checkoutPageUrl());

        String firstName = excelReader.getStringData("CheckoutInfo", 1, 0);
        String lastName = excelReader.getStringData("CheckoutInfo", 1, 1);
        checkoutPage.inputCheckoutInfo(firstName, lastName, "");
        checkoutPage.clickOnContinueButton();

        Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.checkoutPageUrl());
        Assert.assertTrue(checkoutPage.error.isDisplayed());
        Assert.assertEquals(checkoutPage.errorMessage(), "Error: Postal Code is required");
    }

    @Test
    public void userCannotCheckoutWithEmptyCart() {
        headerSectionPage.clickOnCart();
        Assert.assertTrue(yourCartPage.cartItem.isEmpty());
        Assert.assertEquals(driver.getCurrentUrl(), yourCartPage.yourCartPageUrl());

        yourCartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), yourCartPage.yourCartPageUrl());
    }
    @AfterMethod
    public void pageReset() {
        headerSectionPage.clickOnMenuButton();
        sidebarMenuPage.clickOnResetAppStateButton();
        sidebarMenuPage.clickOnLogoutButton();
    }
}
