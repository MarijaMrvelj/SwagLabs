package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
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
    public SidebarMenuPage sidebarMenuPage;
    public ItemPage itemPage;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        loginPage = new LoginPage();
        excelReader = new ExcelReader("src/test/java/TestData.xlsx");
        homePage = new HomePage();
        socialNetworkPage = new SocialNetworkPage();
        yourCartPage = new YourCartPage();
        sidebarMenuPage = new SidebarMenuPage();
        itemPage = new ItemPage();
    }

    public void logInForStandardUser() {
        String validUsername = excelReader.getStringData("Login", 1, 0);
        String validPassword = excelReader.getStringData("Login", 1, 1);
        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnLoginButton();
    }
    public void logIn(String user) {
        String validUsername = "";
        String validPassword = excelReader.getStringData("Login", 1, 1);

        switch (user) {
            case "standard_user" -> validUsername = excelReader.getStringData("Login", 1, 0);
            case "locked_out_user" -> validUsername = excelReader.getStringData("Login", 2, 0);
            case "problem_user" -> validUsername = excelReader.getStringData("Login", 3, 0);
            case "performance_glitch_user" -> validUsername = excelReader.getStringData("Login", 4, 0);
            case "error_user" -> validUsername = excelReader.getStringData("Login", 5, 0);
            case "visual_user" -> validUsername = excelReader.getStringData("Login", 6, 0);
        }
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
        Assert.assertFalse(isElementDisplayed(yourCartPage.cartItem));
        driver.navigate().back();
    }

    public String getAddToCartButtonIdName(String itemName) {
        String base = "add-to-cart-";
        String item = itemName.toLowerCase().replaceAll(" ", "-");
        return base + item;
    }
    public String getRemoveButtonIdName(String itemName) {
        String base = "remove-";
        String item = itemName.toLowerCase().replaceAll(" ", "-");
        return base + item;
    }

    @AfterClass
    public void teardown() {
        //driver.quit();
    }

}
