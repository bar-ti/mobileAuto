package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {
    private static final String
            VIEW_LIST = "org.wikipedia:id/snackbar_action";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyLists() {
        this.waitForElementAndClick(By.id(VIEW_LIST), "Не удалось перейти в спискок для чтения", 5);
    }
}
