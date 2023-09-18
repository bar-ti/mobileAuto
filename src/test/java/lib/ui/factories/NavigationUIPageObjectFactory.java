package lib.ui.factories;

import lib.Platform;
import lib.ui.NavigationUIPageObject;
import lib.ui.android.AndroidNavigationUIPageObject;
import lib.ui.mobileWeb.WMNavigationUIPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIPageObjectFactory {
    public static NavigationUIPageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()){
            return new AndroidNavigationUIPageObject(driver);
        } else if (Platform.getInstance().isMW()) {
            return new WMNavigationUIPageObject(driver);
        } else {
            System.out.println("Невозможно запустить тесты ios");
            return null;
        }
    }
}
