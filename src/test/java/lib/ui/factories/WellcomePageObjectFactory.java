package lib.ui.factories;

import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.WellcomePageObject;
import lib.ui.android.AndroidSearchPageObject;
import lib.ui.android.AndroidWellcomePageObject;
import lib.ui.mobileWeb.MWSearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WellcomePageObjectFactory {
    public static WellcomePageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidWellcomePageObject(driver);
        } else {
            System.out.println("Невозможно запустить тесты ios");
            return null;
        }
    }
}
