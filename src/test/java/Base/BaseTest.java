package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;
    public WebDriverWait wait;
    public ExcelReader excelReader;
    public LoginPage loginPage;
    public HomePage homePage;
    public SocialNetworkPage socialNetworkPage;
    public YourCartPage yourCartPage;
    public MenuPage menuPage;
    public ItemPage itemPage;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage();
        excelReader = new ExcelReader("src/test/java/TestData.xlsx");
        homePage = new HomePage();
        socialNetworkPage = new SocialNetworkPage();
        yourCartPage = new YourCartPage();
        menuPage = new MenuPage();
        itemPage = new ItemPage();
    }

    public void logInForStandardUser() {
        String validUsername = excelReader.getStringData("Login", 1, 0);
        String validPassword = excelReader.getStringData("Login", 1, 1);
        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnLoginButton();
    }
    public boolean isElementDisplayed(WebElement element) {
        boolean isDisplayed = false;
        try {
            isDisplayed = element.isDisplayed();
        } catch (Exception e) {
            System.out.println(e);
        }
        return isDisplayed;
    }
    public void isCartEmpty() {
        homePage.clickOnCart();
        Assert.assertFalse(isElementDisplayed(homePage.cartValue));
        Assert.assertEquals(yourCartPage.cartList.getText(), "QTYDescription");
        driver.navigate().back();
    }
    public void scroll(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @AfterClass
    public void teardown() {
        //driver.quit();
    }

}
