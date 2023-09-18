package lib.ui.mobileWeb;


import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "id:searchIcon";
        SEARCH_INPUT = "xpath://*[@class='search mw-ui-background-icon-search']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@class='title ']//*[text()='{SUBSTRING}']";
        SEARCH_CANCEL_BUTTON = "xpath://*[@class='mf-icon mw-ui-icon-mf-clear mf-icon--small ']";
        SEARCH_RESULT_ELEMENTS = "xpath://*[@class='page-list thumbs actionable']";
        RETURN_TO_SEARCH_RESULT_BUTTON = "//*[@content-desc='Navigate up']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@class='caption']";
        SEARCH_RESULT_CONTAINER = "xpath://strong";
        SEARCH_RESULT_ITEM_TITLE = "xpath://strong";
        SEARCH_RESULT_ITEM_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[contains(@title,'{TITLE}')]//div[text()='{DESCRIPTION}']";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
