package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.waitForCancelButtonAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        String searchLine = "Linkin Park Discography";
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        int amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();
        assertTrue("Не найдены результаты поиска", amountOfSearchResults > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        String searchLine = "ytjudtudtru";
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForEmptyResultLabel();
        searchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testCheckSearchResultAndCancelSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.waitForSearchResultContainerAppear();
        searchPageObject.waitForCancelButtonAppearAndClick();
        searchPageObject.waitForSearchResultContainerDisappear();
    }

    @Test
    public void testCheckTextInSearchResult() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        String text = "java";

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(text);
        searchPageObject.assertEachSearchResultItemHasExpectedText(text);
    }

    public void testSearchAndCheckTitleAndDescription() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("tree");
        searchPageObject.waitForElementByTitleAndDescription("Tree","Perennial woody plant with elongated trunk");
        searchPageObject.waitForElementByTitleAndDescription("Tree of life","Motif in art and culture");
        searchPageObject.waitForElementByTitleAndDescription("Tree traversal","Class of algorithms");
    }

}
