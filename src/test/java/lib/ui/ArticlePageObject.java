package lib.ui;

import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class ArticlePageObject extends MainPageObject {
    protected static String
            TITLE,
            FOOTER_ELEMENT,
            ADD_TO_LIST_BUTTON,
            SUBMIT_ADD_TO_LIST_BUTTON,
            NAME_OF_FOLDER_INPUT,
            CONFIRM_ADD_TO_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Не удалось найти заголовок статьи", 15);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return titleElement.getAttribute("name");
        } else {
            return titleElement.getText();
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Не удалось проскроллить статью до конца",
                    40);
        } else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Не удалось проскроллить статью до конца",
                    40);
        }
    }

    public void addArticleToNewList(String nameOfFolder) {
        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Не удалось нажать кнопку добавления в список для чтения",
                5);
        this.waitForElementAndClick(
                SUBMIT_ADD_TO_LIST_BUTTON,
                "Не удалось нажать кнопку подтверждения добавления в список для чтения",
                5);
        this.waitForElementAndSendKeys(
                NAME_OF_FOLDER_INPUT,
                nameOfFolder,
                "Не удалось ввести название списка для чтения",
                5);
        this.waitForElementAndClick(
                CONFIRM_ADD_TO_LIST_BUTTON,
                "Не удалось подвердить создание списка для чтения",
                5);
    }

    public void addArticleToExistingList(String nameOfFolder) {
        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Не удалось нажать кнопку добавления в список для чтения",
                5);
        this.waitForElementAndClick(
                SUBMIT_ADD_TO_LIST_BUTTON,
                "Не удалось нажать кнопку подтверждения добавления в список для чтения",
                5);
        this.waitForElementAndClick(
                "//*[@text='" + nameOfFolder + "']",
                "Не удалось нажать кнопку подтверждения добавления в список для чтения",
                5);
    }

    public void scrollWebPageUp() {
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor javascriptExecutor = driver;
            javascriptExecutor.executeScript("window.scrollBy(0, 255)");
        } else {
            System.out.println("Метод scrollWebPageUp() не поддерживается для " + Platform.getInstance());
        }
    }

    public void scrollWebPageTillElementNotVisible(String locator, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;
        WebElement webElement = this.waitForElementPresent(locator, errorMessage, Duration.ofSeconds(10));
        while (!this.isElementLocatedOnTheScreen(locator)) {
            scrollWebPageUp();
            ++alreadySwiped;
            if (alreadySwiped > maxSwipes) {
                Assert.assertTrue(errorMessage, webElement.isDisplayed());
            }
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        int elementLocationByY = this.waitForElementPresent(locator, "Не найден элемент", Duration.ofSeconds(5))
                .getLocation()
                .getY();
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor javascriptExecutor = driver;
            Object jsResult = javascriptExecutor.executeScript("return window.pageYOffset");
            elementLocationByY -= Integer.parseInt(jsResult.toString());
        }
        int screenSizeByY = driver.manage().window().getSize().getHeight();
        return elementLocationByY < screenSizeByY;
    }

    public void addArticlesToMySaved() {
        if (Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Не найдена кнопка добавления в сохраненное", 5);
    }

    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Не найдена кнопка удаления статьи из сохраненных", 5);
            this.waitForElementPresent(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Не найдена кнопка добавления статьи после удаления ее из списка",
                    Duration.ofSeconds(5)
            );
        }
    }
}
