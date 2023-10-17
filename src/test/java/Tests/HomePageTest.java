package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        logInForStandardUser();
    }

    @Test
    public void aboutPageCanBeOpened() {
        homePage.clickOnMenuButton();
        menuPage.clickOnAboutButton();

        Assert.assertTrue(menuPage.aboutPage.isDisplayed());
        Assert.assertFalse(isElementDisplayed(homePage.inventoryContainer));
    }
}
