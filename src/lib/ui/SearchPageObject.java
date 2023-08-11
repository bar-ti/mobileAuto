package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String

            SKIP_BUTTON = "//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']",
            SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "//*[@resource-id='org.wikipedia:id/search_src_text']",
            SEARCH_RESULT_BY_SUBSTRING_TPL = " //*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_ELEMENTS = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']",
    //    String searchResultLocator = "org.wikipedia:id/page_list_item_description",
    SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SKIP_BUTTON), "Не найдена кнопка пропуска настроек", 5);
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Не удалось найти/кликнуть на строку поиска", 5);
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), searchLine, "Не найдена строка поиска", 5);
    }

    public void waitForSearchResult(String substring) {
        this.waitForElementPresent(By.xpath(getResultSearchElement(substring)), "Не отображается результат поиска", 15);
    }

    public void clickByArticleWithSubstring(String substring) {
        this.waitForElementAndClick(By.xpath(getResultSearchElement(substring)), "Не удалось кликнуть на статью в результате поиска", 10);
    }

    public void waitForCancelButtonAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Не найдена кнопка X отмены поиска", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Не исчезла кнопка X отмены поиска", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Не удалось найти/нажать кнопку X отмены поиска", 5);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENTS), "Не отображается результат поиска по запросу ", 15);
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENTS));
    }

    public void waitForEmptyResultLabel() {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Не отображается окно отсутствия результатов", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementsNotPresent(By.id(SEARCH_RESULT_ELEMENTS), "Результаты по запросу были найдены");
    }


}