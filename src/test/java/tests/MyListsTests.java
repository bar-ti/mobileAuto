package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUIPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUIPageObject navigationUI = NavigationUIPageObjectFactory.get(driver);
        MyListPageObject myListPageObjects = MyListPageObjectFactory.get(driver);
        String nameOfFolder = "Reading list";

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        articlePageObject.waitForTitleElement();
        articlePageObject.addArticleToNewList(nameOfFolder);

        navigationUI.clickMyLists();

        myListPageObjects.swipeArticleToDelete("Java (programming language)");
    }

    @Test
    public void testSaveTwoArticlesToMyListAndDeleteOneOfThem() {
        SearchPageObject searchPageObject =SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUIPageObject navigationUI = NavigationUIPageObjectFactory.get(driver);
        MyListPageObject myListPageObjects = MyListPageObjectFactory.get(driver);
        String nameOfFolder = "Reading list";

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        articlePageObject.waitForTitleElement();
        articlePageObject.addArticleToNewList(nameOfFolder);

        searchPageObject.returnToSearchResult();
        searchPageObject.clickByArticleWithSubstring("High-level programming language");

        articlePageObject.addArticleToExistingList(nameOfFolder);

        navigationUI.clickMyLists();

        myListPageObjects.swipeArticleToDelete("Java (programming language)");
        myListPageObjects.waitForArticleAppearByTitle("JavaScript");
    }
}
