package lib.ui.mobileWeb;

import lib.ui.NavigationUIPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WMNavigationUIPageObject extends NavigationUIPageObject {
    static {
        VIEW_LIST = "xpath://*[@data-event-name='menu.watchlist'";

    }

    public WMNavigationUIPageObject(RemoteWebDriver driver) {
        super(driver);
    }

}
