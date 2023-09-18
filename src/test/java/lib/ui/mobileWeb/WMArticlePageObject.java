package lib.ui.mobileWeb;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WMArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "xpath://*[@id='firstHeading']/*[@class='mw-page-title-main']";
        FOOTER_ELEMENT = "xpath://*[@class='minerva-footer-logo']";
        ADD_TO_LIST_BUTTON = "xpath://*[@title='Add this page to your watchlist [alt-shift-w]']";
      }

    public WMArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

}
