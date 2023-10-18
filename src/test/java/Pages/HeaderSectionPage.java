package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderSectionPage extends BaseTest {

    public HeaderSectionPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "react-burger-menu-btn")
    public WebElement menuButton;
    @FindBy(id = "shopping_cart_container")
    public WebElement cart;
    @FindBy(className = "shopping_cart_badge")
    public WebElement cartValue;

    //---------------------
    public void clickOnMenuButton() {
        menuButton.click();
    }
    public void clickOnCart() {
        cart.click();
    }
    public int numberOfItemsInCart() {
        return Integer.parseInt(cartValue.getText());
    }

}
