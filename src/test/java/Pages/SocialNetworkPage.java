package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SocialNetworkPage extends BaseTest {

    public SocialNetworkPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "#react-root > div > div > div.css-1dbjc4n.r-18u37iz.r-13qz1uu.r-417010 > main > div > div > div > div > div > div:nth-child(3) > div > div > div > div > div.css-1dbjc4n.r-6gpygo.r-14gqq1x > div.css-1dbjc4n.r-1wbh5a2.r-dnmrzs.r-1ny4l3l > div > div.css-1dbjc4n.r-1wbh5a2.r-dnmrzs.r-1ny4l3l > div > div > span > span:nth-child(1)")
    public WebElement twitterPage;
    @FindBy(css = ".x1egiwwb.x4l50q0")
    public WebElement facebookWindow;

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

