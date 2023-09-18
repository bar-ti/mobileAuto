package lib.ui.mobileWeb;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WMMyListPageObject extends MyListPageObject {
static {
    ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
}
    public WMMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
