package lib.ui.mobileWeb;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WMArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "xpath://*[@class='mw-page-title-main']";
        FOOTER_ELEMENT = "xpath://*[@class='minerva-footer-logo']";
        ADD_TO_LIST_BUTTON = "id:org.wikipedia:id/page_save";
        SUBMIT_ADD_TO_LIST_BUTTON = "id:org.wikipedia:id/snackbar_action";
        NAME_OF_FOLDER_INPUT = "id:org.wikipedia:id/text_input";
        CONFIRM_ADD_TO_LIST_BUTTON = "id:android:id/button1";
      }

    public WMArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

}
