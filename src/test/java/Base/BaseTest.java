package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public HeaderSectionPage headerSectionPage;
    public CheckoutPage checkoutPage;
    public CheckoutOverviewPage checkoutOverviewPage;
    public CheckoutCompletePage checkoutCompletePage;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        loginPage = new LoginPage();
        excelReader = new ExcelReader("src/test/java/TestData.xlsx");
        homePage = new HomePage();
        socialNetworkPage = new SocialNetworkPage();
        yourCartPage = new YourCartPage();
        sidebarMenuPage = new SidebarMenuPage();
        itemPage = new ItemPage();
        headerSectionPage = new HeaderSectionPage();
        checkoutPage = new CheckoutPage();
        checkoutOverviewPage = new CheckoutOverviewPage();
        checkoutCompletePage = new CheckoutCompletePage();
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
        headerSectionPage.clickOnCart();
        if(!isElementDisplayed(headerSectionPage.cartValue) && yourCartPage.cartItem.isEmpty())
            System.out.println("Your cart is empty.");
        else
            System.out.println("Number of items in your cart:" + headerSectionPage.numberOfItemsInCart());
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

    public void addItem(String itemName) {
        isCartEmpty();
        homePage.clickOnAddToCartButton(itemName);
        Assert.assertTrue(driver.findElement(By.id(getRemoveButtonIdName(itemName))).isDisplayed());
    }
    public void addRandomItem(List<String> items) {
        isCartEmpty();
        Random random = new Random();
        int randomIndex = random.nextInt(items.size());
        String randomItem = items.get(randomIndex);
        homePage.clickOnAddToCartButton(randomItem);
        Assert.assertTrue(driver.findElement(By.id(getRemoveButtonIdName(randomItem))).isDisplayed());
    }

    public ArrayList<Double> allPricesList(List<WebElement> prices) {
        ArrayList<Double> pricesList = new ArrayList<>();
        for (WebElement itemPrice : prices) {
            double price = Double.parseDouble(itemPrice.getText().replace("$", ""));
            pricesList.add(price);
        }
        return pricesList;
    }

    @AfterClass
    public void teardown() {
        //driver.quit();
    }

}
