package lib.ui;

import io.qameta.allure.Step;
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

    @Step("Инициализация окна поиска")
    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Не удалось найти/кликнуть на строку поиска", 5);
    }

    @Step("Ввод значения '{searchLine}' в поле ввода поиска")
    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, searchLine, "Не найдена строка поиска", 5);
    }

    @Step("Ожидание появления результатов поиска по подстоке '{substring}'")
    public void waitForSearchResult(String substring) {
        screenshot(this.takeScreenshot("search_page"));
        this.waitForElementPresent(getResultSearchElement(substring), "Не отображается результат поиска", 15);
    }

    @Step("Клик на статью по содержащейся в ней подстроке  '{substring}'")
    public void clickByArticleWithSubstring(String substring) {
        this.waitForElementAndClick(getResultSearchElement(substring), "Не удалось кликнуть на статью в результате поиска", 10);
    }

    @Step("Ожидание появления кнопки Х отмены поиска")
    public void waitForCancelButtonAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Не найдена кнопка X отмены поиска", 5);
    }

    @Step("Ожидание и клик на кнопку Х отмены поиска")
    public void waitForCancelButtonAppearAndClick() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Не удалось найти/нажать кнопку X отмены поиска", 5);
    }

    @Step("Ожидание исчезновения кнопки Х отмены поиска")
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Не исчезла кнопка X отмены поиска", 5);
    }
    @Step("Клик на кнопку Х отмены поиска")
    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Не удалось найти/нажать кнопку X отмены поиска", 5);
    }

    @Step("Опеределение количества статей в результате поиска")
    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENTS, "Не отображается результат поиска по запросу ", 15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENTS);
    }

    @Step("Ожидание отображения пустого результата поиска")
    public void waitForEmptyResultLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Не отображается окно отсутствия результатов", 15);
    }

    @Step("Проверка отстутсвия результатов поиска")
    public void assertThereIsNoResultOfSearch() {
        this.assertElementsNotPresent(SEARCH_RESULT_ELEMENTS, "Результаты по запросу были найдены");
    }

    @Step("Возврат к результатам поиска")
    public void returnToSearchResult() {
        this.waitForElementAndClick(RETURN_TO_SEARCH_RESULT_BUTTON, "Не удалось вернуться к результатам поиска", 5);
    }

    @Step("Ожидание отображения результатов поиска")
    public void waitForSearchResultContainerAppear() {
        this.waitForElementPresent(SEARCH_RESULT_CONTAINER, "Не отображаются результаты поиска", 15);
    }

    @Step("Ожидание исчезновения результатов поиска")
    public void waitForSearchResultContainerDisappear() {
        this.waitForElementNotPresent(SEARCH_RESULT_CONTAINER, "Не исчезли результаты поиска", 5);
    }

    @Step("Проверка наличия заданного теста  '{expectedText}' в результате поиска")
    public void assertEachSearchResultItemHasExpectedText(String expectedText) {
        this.assertFindResultsHasText(SEARCH_RESULT_ITEM_TITLE, expectedText, "Не все результаты поиска содержат заданый текст");
    }

    @Step("Ожидание отображения и заголовка '{title}' и описания статьи '{description}'")
    public void waitForElementByTitleAndDescription(String title, String description) {
        this.waitForElementPresent(
                getResultSearchElementByTitleAndDescription(title, description),
                "\nВ результатах поиска не найден элемент с заголовком и описанием\n" + title + "\n" + description, Duration.ofSeconds(5));
    }
}