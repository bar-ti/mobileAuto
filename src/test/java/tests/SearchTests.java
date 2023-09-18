package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
@Epic("Search tests")
public class SearchTests extends CoreTestCase {
    @Test
    @Feature(value = "Search")
    @DisplayName("Проверка отображения результатов поиска")
    @Description("Ввод заданного слова в строку поиска и проверка того, что в отображенных результатах есть нужное значение")
    @Step("Старт выполнеия теста testSearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Проверка отмены поиска")
    @Description("Ввод заданного слова в строку поиска и проверка того, что результаты поиска исчезают при нажатии на кнопку отмены поиска")
    @Step("Старт выполнеия теста testCancelSearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCancelSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.waitForCancelButtonAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Проверка поличества результатов поиска")
    @Description("Проверяется, что в результате поиска по заданому слову было найдено несколько статей")
    @Step("Старт выполнеия теста testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.TRIVIAL)
    public void testAmountOfNotEmptySearch() {
        String searchLine = "Linkin Park Discography";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        int amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();
        //       SearchPageObject.takeScreenshot("search_page");
        Assert.assertTrue("Не найдены результаты поиска", amountOfSearchResults > 0);
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Провека пустого результата поиска")
    @Description("Проверяется, что если по заданному слову ничего не было найдено, отображается пустой список результатов")
    @Step("Старт выполнеия теста testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testAmountOfEmptySearch() {
        String searchLine = "ytjudtudtru";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForEmptyResultLabel();
        searchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Проверка отображения и удаления результатов поиска")
    @Description("Проверяется отображение результатов поиска по заданному слову и отсутствие результатов при отмене поиска")
    @Step("Старт выполнеия теста testCheckSearchResultAndCancelSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCheckSearchResultAndCancelSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.waitForSearchResultContainerAppear();
        searchPageObject.waitForCancelButtonAppearAndClick();
        searchPageObject.waitForSearchResultContainerDisappear();
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Проверка результатов поиска")
    @Description("Проверяется, что в каждом результате поиска присутствует заданное значение")
    @Step("Старт выполнеия теста testCheckTextInSearchResult")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckTextInSearchResult() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        String text = "java";

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(text);
        searchPageObject.assertEachSearchResultItemHasExpectedText(text);
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Проверка заголовка и описания")
    @Description("Проверяется, что в результатах поиска присутствует и заголовок и краткое описание статьи")
    @Step("Старт выполнеия теста testSearchAndCheckTitleAndDescription")
    @Severity(value = SeverityLevel.MINOR)
    public void testSearchAndCheckTitleAndDescription() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("tree");
        searchPageObject.waitForElementByTitleAndDescription("Tree", "Perennial woody plant with elongated trunk");
        searchPageObject.waitForElementByTitleAndDescription("Tree of life", "Motif in art and culture");
        searchPageObject.waitForElementByTitleAndDescription("Tree traversal", "Class of algorithms");
    }
}
