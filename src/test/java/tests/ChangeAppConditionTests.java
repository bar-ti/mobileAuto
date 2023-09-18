package tests;

import io.appium.java_client.AppiumDriver;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.android.AndroidSearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    public void testScreenOrientationSearchResults() {
        if (Platform.getInstance().isMW()) {
            return;
        }
        AndroidSearchPageObject searchPageObject = new AndroidSearchPageObject((AppiumDriver) driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        AndroidArticlePageObject articlePageObject = new AndroidArticlePageObject((AppiumDriver) driver);
        String titleBeforeRotation = articlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String titleAfterRotation = articlePageObject.getArticleTitle();
        assertEquals(
                "Заголовок статьи изменился после поворота экрана",
                titleBeforeRotation,
                titleAfterRotation);
        this.rotateScreenPortrait();
        String titleAfterSecondRotation = articlePageObject.getArticleTitle();
        assertEquals(
                "Заголовок статьи изменился после поворота экрана",
                titleBeforeRotation,
                titleAfterSecondRotation);
    }

    @Test
    public void testCheckSearchArticleInBackgrounf() {
        if (Platform.getInstance().isMW()) {
            return;
        }
        AndroidSearchPageObject searchPageObject = new AndroidSearchPageObject((AppiumDriver)driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
