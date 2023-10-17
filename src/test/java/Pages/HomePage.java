package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BaseTest {

    public HomePage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "react-burger-menu-btn")
    public WebElement menuButton;
    @FindBy(id = "inventory_container")
    public WebElement inventoryContainer;
    @FindBy(className = "social_twitter")
    public WebElement twitterLogo;
    @FindBy(className = "social_facebook")
    public WebElement facebookLogo;
    @FindBy(className = "social_linkedin")
    public WebElement linkedInLogo;
    @FindBy(id = "shopping_cart_container")
    public WebElement cart;
    @FindBy(className = "shopping_cart_badge")
    public WebElement cartValue;
    @FindBy(css = "div.inventory_item_name")
    public List<WebElement> itemNames;
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    public WebElement addToCartBackpackButton;
    @FindBy(id = "remove-sauce-labs-backpack")
    public WebElement removeBackpackButton;


    //------------------------
    public String homePageUrl() {
        return "https://www.saucedemo.com/inventory.html";
    }
    public void clickOnMenuButton() {
        menuButton.click();
    }

    public void clickOnTwitterLogo() {
        twitterLogo.click();
    }
    public void clickOnFacebookLogo() {
        facebookLogo.click();
    }
    public void clickOnLinkedInLogo() {
        linkedInLogo.click();
    }
    public void clickOnCart() {
        cart.click();
    }
    public void clickOnItemName(String itemName) {
        for (int i = 0; i < itemNames.size(); i++) {
            if(itemNames.get(i).getText().equals(itemName)) {
                //scroll(itemNames.get(i));
                itemNames.get(i).click();
            }
        }
    }
    public void clickOnAddToCartBackpackButton() {
        addToCartBackpackButton.click();
    }
    public void clickOnRemoveBackpackButton() {
        removeBackpackButton.click();
    }

}
