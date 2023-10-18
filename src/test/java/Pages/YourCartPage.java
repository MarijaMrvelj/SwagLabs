package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class YourCartPage extends BaseTest {

    public YourCartPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(className = "cart_list")
    public WebElement cartList;
    @FindBy(className = "inventory_item_name")
    public List<WebElement> itemNames;
    @FindBy(className = "cart_item")
    public List<WebElement> cartItem;
    @FindBy(css = ".btn.btn_secondary.btn_small.cart_button")
    public List<WebElement> removeButtons;
    @FindBy(id = "checkout")
    public WebElement checkoutButton;
    @FindBy(id = "continue-shopping")
    public WebElement continueShoppingButton;

    //------------------

    public String yourCartPageUrl() {
        return "https://www.saucedemo.com/cart.html";
    }
    public void clickOnCheckoutButton() {
        checkoutButton.click();
    }
    public void clickOnContinueShoppingButton() {
        continueShoppingButton.click();
    }
    public void clickOnItem(String itemName) {
        for (int i = 0; i < itemNames.size(); i++) {
            if(itemNames.get(i).getText().equals(itemName))
                itemNames.get(i).click();
        }
    }
    public String getItemName(int index) {
        return itemNames.get(index).getText();
    }
    public void removeItem(int index) {
        removeButtons.get(index).click();
    }
}
