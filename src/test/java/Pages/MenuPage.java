package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuPage extends BaseTest {

    public MenuPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "inventory_sidebar_link")
    public WebElement allItemsButton;
    @FindBy(id = "about_sidebar_link")
    public WebElement aboutButton;
    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutButton;
    @FindBy(id = "reset_sidebar_link")
    public WebElement resetAppStateButton;

    @FindBy(id = "__next")
    public WebElement aboutPage;
    //-------------------

    public void clickOnAllItemsButton() {
        allItemsButton.click();
    }
    public void clickOnAboutButton() {
        aboutButton.click();
    }
    public void clickOnLogoutButton() {
        logoutButton.click();
    }
    public void clickOnResetAppStateButton() {
        resetAppStateButton.click();
    }
}
