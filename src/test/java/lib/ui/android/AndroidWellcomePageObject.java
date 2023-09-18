package lib.ui.android;

import lib.ui.SearchPageObject;
import lib.ui.WellcomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidWellcomePageObject extends WellcomePageObject {

    static {
        SKIP_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']";
     }

    public AndroidWellcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}