package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListPageObject extends MainPageObject {
    protected static String
            ARTICLE_BY_TITLE_TPL;

    public MyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static String getSavedArticleXPathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    public void swipeArticleToDelete(String articleTitle) {
        this.waitForArticleAppearByTitle(articleTitle);
        this.swipeElementToLeft(getSavedArticleXPathByTitle(articleTitle), "Не удалось свайпнуть элемент влево");
        this.waitForArticleDisappearByTitle(articleTitle);
    }

    public void waitForArticleDisappearByTitle(String articleTitle) {
        this.waitForElementNotPresent(getSavedArticleXPathByTitle(articleTitle), "не удалось удалить статью из списка", 5);
    }

    public void waitForArticleAppearByTitle(String articleTitle) {
        this.waitForElementPresent(getSavedArticleXPathByTitle(articleTitle), "статья не отображается в списке", 5);
    }
}
