package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "xpath://*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::*[@class='android.widget.TextView']";
        FOOTER_ELEMENT = "xpath://*[@content-desc = 'View article in browser']";
        ADD_TO_LIST_BUTTON = "id:org.wikipedia:id/page_save";
        SUBMIT_ADD_TO_LIST_BUTTON = "id:org.wikipedia:id/snackbar_action";
        NAME_OF_FOLDER_INPUT = "id:org.wikipedia:id/text_input";
        CONFIRM_ADD_TO_LIST_BUTTON = "id:android:id/button1";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

}
