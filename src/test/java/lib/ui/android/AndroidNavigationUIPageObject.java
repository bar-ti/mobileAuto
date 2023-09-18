package lib.ui.android;

import lib.ui.NavigationUIPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUIPageObject extends NavigationUIPageObject {
    static {
        VIEW_LIST = "id:org.wikipedia:id/snackbar_action";
    }

    public AndroidNavigationUIPageObject(RemoteWebDriver driver) {
        super(driver);
    }

}
