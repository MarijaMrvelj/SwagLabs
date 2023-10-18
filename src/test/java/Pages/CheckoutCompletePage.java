package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage extends BaseTest {

    public CheckoutCompletePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "back-to-products")
    public WebElement backHomeButton;
    @FindBy(className = "title")
    public WebElement checkoutTitle;
    @FindBy(className = "complete-header")
    public WebElement message;

    //-----------------------

    public String checkoutCompletePageUrl() {
        return "https://www.saucedemo.com/checkout-complete.html";
    }
    public void clickOnBackHomeButton() {
        backHomeButton.click();
    }
    public String checkoutTitle() {
        return checkoutTitle.getText();
    }
    public String thankYouMessage() {
        return message.getText();
    }
}
