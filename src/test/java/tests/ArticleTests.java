package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Article tests")
public class ArticleTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Проверка отображения заданного заголовка статьи")
    @Description("Сравнение отображенного заголовка статьи с заданным")
    @Step("Старт теста testCompareArticleTitle()")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCompareArticleTitle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        String articleTitle = articlePageObject.getArticleTitle();
        Assert.assertEquals(
                "Заловок статьи не соответсвует ожидаемому результату",
                "Java (programming language)",
                articleTitle
        );
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Проверка прокрутки страницы")
    @Description("Прокручиваем страницу, пока не увидим футер")
    @Step("Старт теста testSwipeArticle()")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSwipeArticle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Automation for Apps");

        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Проверка отображения заголовка страницы")
    @Description("Проверяем, что в результате поиска отображается заданный заголовок")
    @Step("Старт теста testTitlePresent()")
    @Severity(value = SeverityLevel.MINOR)
    public void testTitlePresent() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        articlePageObject.waitForTitleElement();
    }
}
