package lib.ui.factories;

import lib.Platform;
import lib.ui.MyListPageObject;
import lib.ui.android.AndroidMyListPageObject;
import lib.ui.mobileWeb.WMMyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListPageObjectFactory {
    public static MyListPageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()){
            return new AndroidMyListPageObject(driver);
        } else if (Platform.getInstance().isMW()) {
            return new WMMyListPageObject(driver);
        } else {
            System.out.println("Невозможно запустить тесты ios");
            return null;
        }
    }
}
