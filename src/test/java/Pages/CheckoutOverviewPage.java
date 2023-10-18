package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage extends BaseTest {

    public CheckoutOverviewPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "cancel")
    public WebElement cancelButton;
    @FindBy(id = "finish")
    public WebElement finishButton;
    @FindBy(className = "title")
    public WebElement checkoutTitle;
    @FindBy(className = "cart_item")
    public List<WebElement> cartItems;
    @FindBy(className = "inventory_item_price")
    public List<WebElement> prices;
    @FindBy(className = "summary_info_label")
    public List<WebElement> infoLabel;
    @FindBy(className = "summary_value_label")
    public List<WebElement> valueLabel;
    @FindBy(className = "summary_subtotal_label")
    public WebElement itemTotalPrice;
    @FindBy(className = "summary_tax_label")
    public WebElement tax;
    @FindBy(css = ".summary_info_label.summary_total_label")
    public WebElement total;

    //----------------

    public String checkoutOverviewPageUrl() {
        return "https://www.saucedemo.com/checkout-step-two.html";
    }
    public void clickOnCancelButton() {
        cancelButton.click();
    }
    public void clickOnFinishButton() {
        finishButton.click();
    }

    public String checkoutTitle() {
        return checkoutTitle.getText();
    }
    public double calculateItemTotalPrice() {
        double sum = 0;
        ArrayList<Double> pricesList = allPricesList(prices);
        for (Double price : pricesList) {
            sum += price;
        }
        return sum;
    }
    public double getItemTotalPrice() {
        return Double.parseDouble(itemTotalPrice.getText().replace("Item total: $", ""));
    }
    public double getTax() {
        return Double.parseDouble(tax.getText().replace("Tax: $", ""));
    }
    public double getTotal() {
        return Double.parseDouble(total.getText().replace("Total: $", ""));
    }
    public double calculateTotalPrice() {
        return getTax() + calculateItemTotalPrice();
    }

}
