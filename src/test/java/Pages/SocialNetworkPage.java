package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SocialNetworkPage extends BaseTest {

    public SocialNetworkPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "h2[role='heading']")
    public WebElement twitterPage;
    @FindBy(css = ".x1egiwwb.x4l50q0")
    public WebElement facebookWindow;
    @FindBy(css = "h1")
    public WebElement facebookPage;
    @FindBy(id = "organization_guest_contextual-sign-in-modal-header")
    public WebElement linkedInWindow;
    @FindBy(id = "main-content")
    public WebElement linkedInPage;
    @FindBy(css = "h1")
    public WebElement linkedInProfile;

    //------------------------

    public String twitterPageUrl() {
        return "https://twitter.com/saucelabs";
    }
    public String facebookPageUrl() {
        return "https://www.facebook.com/saucelabs";
    }
    public String linkedInPageUrl() {
        return "https://www.linkedin.com/company/sauce-labs/";
    }
}

