package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObjects extends MainPageObject {
    private static final String
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";

    public MyListPageObjects(AppiumDriver driver) {
        super(driver);
    }

    private static String getSavedArticleXPathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    public void swipeArticleToDelete(String articleTitle) {
        this.waitForArticleAppearByTitle(articleTitle);
        this.swipeElementToLeft(By.xpath(getSavedArticleXPathByTitle(articleTitle)), "Не удалось свайпнуть элемент влево");
        this.waitForArticleDisappearByTitle(articleTitle);
    }

    public void waitForArticleDisappearByTitle(String articleTitle) {
        this.waitForElementNotPresent(By.xpath(getSavedArticleXPathByTitle(articleTitle)), "не удалось удалить статью из списка", 5);
    }

    public void waitForArticleAppearByTitle(String articleTitle) {
        this.waitForElementPresent(By.xpath(getSavedArticleXPathByTitle(articleTitle)), "статья не отображается в списке", 5);
    }
}
