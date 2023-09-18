package lib.ui;


import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;


public class MyListPageObject extends MainPageObject {
    protected static String
            ARTICLE_BY_TITLE_TPL,
            REMOVE_FROM_SAVED_BUTTON;

    public MyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static String getSavedArticleXPathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    private static String getRemoveButtonByTitle(String articleTitle) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", articleTitle);
    }

    public void swipeArticleToDelete(String articleTitle) {
        this.waitForArticleAppearByTitle(articleTitle);

        if (Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(getSavedArticleXPathByTitle(articleTitle),
                    "Cannot find saved article");
        } else {
            String removeLocator = getRemoveButtonByTitle(articleTitle);
            this.waitForElementAndClick(removeLocator,
                    "Не удалось удалить статью из сохраненных", 5);
        }

        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }
    }

    public void waitForArticleDisappearByTitle(String articleTitle) {
        this.waitForElementNotPresent(getSavedArticleXPathByTitle(articleTitle), "не удалось удалить статью из списка", 5);
    }

    public void waitForArticleAppearByTitle(String articleTitle) {
        this.waitForElementPresent(getSavedArticleXPathByTitle(articleTitle), "статья не отображается в списке", 5);
    }
}
