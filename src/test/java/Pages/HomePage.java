package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    public List<WebElement> addToCartButtons;
    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")
    public List<WebElement> removeButtons;
    @FindBy(className = "inventory_item_price")
    public List<WebElement> prices;
    @FindBy(className = "product_sort_container")
    public WebElement sortDropdown;

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
                itemNames.get(i).click();
            }
        }
    }
    /*public void clickOnAddToCartButton(int index) {
        addToCartButtons.get(index).click();
    }
    public void clickOnRemoveButton(int index) {
        removeButtons.get(index).click();
    }*/
    public void clickOnAddToCartButton(String itemName) {
        driver.findElement(By.id(getAddToCartButtonIdName(itemName))).click();
    }
    public void clickOnRemoveButton(String itemName) {
        driver.findElement(By.id(getRemoveButtonIdName(itemName))).click();
    }

    public ArrayList<Double> allPrices() {
        ArrayList<Double> pricesList = new ArrayList<>();
        for (WebElement itemPrice : prices) {
            double price = Double.parseDouble(itemPrice.getText().replace("$", ""));
            pricesList.add(price);
        }
        return pricesList;
    }
    public boolean isSortedLowToHigh(ArrayList<Double> doubleList) {
        for (int i = 0; i < doubleList.size()-1; i++) {
            if(doubleList.get(i) > doubleList.get(i + 1))
                return false;
        }
        return true;
    }
    public boolean isSortedHighToLow(ArrayList<Double> doubleList) {
        for (int i = 0; i < doubleList.size()-1; i++) {
            if(doubleList.get(i) < doubleList.get(i + 1))
                return false;
        }
        return true;
    }
    public ArrayList<String> allItemsNames() {
        ArrayList<String> itemsList = new ArrayList<>();
        for (WebElement item : itemNames) {
            itemsList.add(item.getText());
        }
        return itemsList;
    }
    public ArrayList<String> sortItemsNamesInReverseOrder() {
        ArrayList<String> itemsList = new ArrayList<>(allItemsNames());
        itemsList.sort(Collections.reverseOrder());
        return itemsList;
    }
    public ArrayList<String> sortItemsNamesInOrder() {
        ArrayList<String> itemsList = new ArrayList<>(allItemsNames());
        itemsList.sort(Comparator.naturalOrder());
        return itemsList;
    }
}
