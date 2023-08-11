package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObjects;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        NavigationUI navigationUI = new NavigationUI(driver);
        MyListPageObjects myListPageObjects = new MyListPageObjects(driver);
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
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        NavigationUI navigationUI = new NavigationUI(driver);
        MyListPageObjects myListPageObjects = new MyListPageObjects(driver);
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
