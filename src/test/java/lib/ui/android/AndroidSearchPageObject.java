package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[@resource-id='org.wikipedia:id/search_src_text']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_ELEMENTS = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']";
        RETURN_TO_SEARCH_RESULT_BUTTON = "xpath://*[@content-desc='Navigate up']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results']";
        SEARCH_RESULT_CONTAINER = "id:org.wikipedia:id/search_results_display";
        SEARCH_RESULT_ITEM_TITLE = "id:org.wikipedia:id/page_list_item_title";
        SEARCH_RESULT_ITEM_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@text='{TITLE}']/following::*[@text='{DESCRIPTION}']/parent::*[@class='android.view.ViewGroup']";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}