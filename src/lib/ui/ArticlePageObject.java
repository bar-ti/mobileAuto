package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
            TITLE = "pcs-edit-section-title-description",
            FOOTER_ELEMENT = "//*[@content-desc = 'View article in browser']",
            ADD_TO_LIST_BUTTON = "org.wikipedia:id/page_save",
            SUBMIT_ADD_TO_LIST_BUTTON = "org.wikipedia:id/snackbar_action",
            NAME_OF_FOLDER_INPUT = "org.wikipedia:id/text_input",
            CONFIRM_ADD_TO_LIST_BUTTON = "android:id/button1";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.id(TITLE), "Не удалось найти заголовок статьи", 15);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("name");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "Не удалось проскроллить статью до конца", 20);
    }

    public void addArticleToList(String nameOfFolder) {
        this.waitForElementAndClick(
                By.id(ADD_TO_LIST_BUTTON),
                "Не удалось нажать кнопку добавления в список для чтения",
                5);
        this.waitForElementAndClick(
                By.id(SUBMIT_ADD_TO_LIST_BUTTON),
                "Не удалось нажать кнопку подтверждения добавления в список для чтения",
                5);
        this.waitForElementAndSendKeys(
                By.id(NAME_OF_FOLDER_INPUT),
                nameOfFolder,
                "Не удалось ввести название списка для чтения",
                5);
        this.waitForElementAndClick(
                By.id(CONFIRM_ADD_TO_LIST_BUTTON),
                "Не удалось подвердить создание списка для чтения",
                5);

    }
}
