package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class SearchPageObject extends MainPageObject {

    protected static String

            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_ELEMENTS,
            RETURN_TO_SEARCH_RESULT_BUTTON,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_RESULT_CONTAINER,
            SEARCH_RESULT_ITEM_TITLE,
            SEARCH_RESULT_ITEM_BY_TITLE_AND_DESCRIPTION_TPL;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTitleAndDescription(String title, String description) {
        return SEARCH_RESULT_ITEM_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Не удалось найти/кликнуть на строку поиска", 5);
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, searchLine, "Не найдена строка поиска", 5);
    }

    public void waitForSearchResult(String substring) {
        this.waitForElementPresent(getResultSearchElement(substring), "Не отображается результат поиска", 15);
    }

    public void clickByArticleWithSubstring(String substring) {
        this.waitForElementAndClick(getResultSearchElement(substring), "Не удалось кликнуть на статью в результате поиска", 10);
    }

    public void waitForCancelButtonAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Не найдена кнопка X отмены поиска", 5);
    }

    public void waitForCancelButtonAppearAndClick() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Не удалось найти/нажать кнопку X отмены поиска", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Не исчезла кнопка X отмены поиска", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Не удалось найти/нажать кнопку X отмены поиска", 5);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENTS, "Не отображается результат поиска по запросу ", 15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENTS);
    }

    public void waitForEmptyResultLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Не отображается окно отсутствия результатов", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementsNotPresent(SEARCH_RESULT_ELEMENTS, "Результаты по запросу были найдены");
    }

    public void returnToSearchResult() {
        this.waitForElementAndClick(RETURN_TO_SEARCH_RESULT_BUTTON, "Не удалось вернуться к результатам поиска", 5);
    }

    public void waitForSearchResultContainerAppear() {
        this.waitForElementPresent(SEARCH_RESULT_CONTAINER, "Не отображаются результаты поиска", 15);
    }

    public void waitForSearchResultContainerDisappear() {
        this.waitForElementNotPresent(SEARCH_RESULT_CONTAINER, "Не исчезли результаты поиска", 5);
    }

    public void assertEachSearchResultItemHasExpectedText(String expectedText) {
        this.assertFindResultsHasText(SEARCH_RESULT_ITEM_TITLE, expectedText, "Не все результаты поиска содержат заданый текст");
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        this.waitForElementPresent(
                getResultSearchElementByTitleAndDescription(title, description),
                "\nВ результатах поиска не найден элемент с заголовком и описанием\n" + title + "\n" + description, Duration.ofSeconds(5));
    }
}