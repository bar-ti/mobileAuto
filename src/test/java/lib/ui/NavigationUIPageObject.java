package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIPageObject extends MainPageObject {
    protected static String
            VIEW_LIST;

    public NavigationUIPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickMyLists() {
        this.waitForElementAndClick(VIEW_LIST, "Не удалось перейти в спискок для чтения", 5);
    }
}
