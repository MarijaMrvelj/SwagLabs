package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BaseTest {

    public CheckoutPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "cancel")
    public WebElement cancelButton;
    @FindBy(id = "continue")
    public WebElement continueButton;
    @FindBy(id = "first-name")
    public WebElement firstNameField;
    @FindBy(id = "last-name")
    public WebElement lastNameField;
    @FindBy(id = "postal-code")
    public WebElement zipPostalCodeField;
    @FindBy(css = ".error-message-container.error")
    public WebElement error;
    @FindBy(className = "title")
    public WebElement checkoutTitle;

    //------------------

    public String checkoutPageUrl() {
        return "https://www.saucedemo.com/checkout-step-one.html";
    }
    public void inputCheckoutInfo(String firstName, String lastName, String postalCode) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        zipPostalCodeField.clear();
        zipPostalCodeField.sendKeys(postalCode);
    }
    public void clickOnCancelButton() {
        cancelButton.click();
    }
    public void clickOnContinueButton() {
        continueButton.click();
    }
    public String errorMessage() {
        return error.getText();
    }
    public String checkoutTitle() {
        return checkoutTitle.getText();
    }
}
