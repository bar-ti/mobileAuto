package lib.ui.factories;

import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.mobileWeb.WMArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()){
            return new AndroidArticlePageObject(driver);
        } else if (Platform.getInstance().isMW()) {
            return new WMArticlePageObject(driver);
        } else {
            System.out.println("Невозможно запустить тесты ios");
            return null;
        }
    }
}
