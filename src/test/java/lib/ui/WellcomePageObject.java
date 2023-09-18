package lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WellcomePageObject extends MainPageObject {

    protected static String
            SKIP_BUTTON;

    public WellcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void skipSetting() {
        this.waitForElementAndClick(SKIP_BUTTON, "Не найдена кнопка пропуска настроек", 5);
    }
}