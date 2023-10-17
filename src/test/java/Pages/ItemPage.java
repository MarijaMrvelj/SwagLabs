package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage extends BaseTest {

    public ItemPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    public WebElement addToCartButton;
    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")
    public WebElement removeButton;
    @FindBy(id = "back-to-products")
    public WebElement backToProductsButton;

    //----------------

    public void clickOnAddToCartButton() {
        if(addToCartButton.getText().equals("Add to cart"))
            addToCartButton.click();
    }
    public void clickOnRemoveButton() {
        if(removeButton.getText().equals("Remove"))
            removeButton.click();
    }
    public void clickOnBackToProductsButton() {
        backToProductsButton.click();
    }
}
