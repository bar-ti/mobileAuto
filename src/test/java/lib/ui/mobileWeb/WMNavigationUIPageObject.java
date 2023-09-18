package lib.ui.mobileWeb;

import lib.ui.NavigationUIPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WMNavigationUIPageObject extends NavigationUIPageObject {
    static {
        VIEW_LIST = "id:org.wikipedia:id/snackbar_action";

    }

    public WMNavigationUIPageObject(RemoteWebDriver driver) {
        super(driver);
    }

}
