import lib.CoreTestCase;
import org.junit.Test;
import org.openqa.selenium.By;

public class FirstTest extends CoreTestCase {


    @Test
    public void testCheckWikiSearchText() {
        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                        "Не найдена кнопка пропуска настроек",
                        5);
        mainPageObject
                .assertElementHasText(
                        By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@class='android.widget.TextView']"),
                        "Search Wikipedia",
                        "Элемент не содержит ожидаемого текста");
    }

    @Test
    public void testCheckSearchResultAndCancelSearch() {
        mainPageObject
                .waitForElementAndClick(
                        By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                        "Не найдена кнопка пропуска настроек",
                        5);
        mainPageObject
                .waitForElementAndClick(
                        By.id("org.wikipedia:id/search_container"),
                        "Не найдена строка поиска",
                        5);
        mainPageObject
                .waitForElementAndSendKeys(
                        By.id("org.wikipedia:id/search_src_text"),
                        "java",
                        "Не удалось ввести значение в строку поиска",
                        5);
        mainPageObject
                .waitForElementPresent(
                        By.id("org.wikipedia:id/search_results_display"),
                        "Не отображаются результаты поиска",
                        15);
        mainPageObject
                .waitForElementAndClick(
                        By.id("org.wikipedia:id/search_close_btn"),
                        "Не найдена кнопка X отмены поиска",
                        5);
        mainPageObject
                .waitForElementNotPresent(
                        By.id("org.wikipedia:id/search_results_display"),
                        "Не исчезли результаты поиска",
                        5);
    }

    @Test
    public void testCheckTextInSearchResult() {
        mainPageObject
                .waitForElementAndClick(
                        By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                        "Не найдена кнопка пропуска настроек",
                        5);
        mainPageObject
                .waitForElementAndClick(
                        By.id("org.wikipedia:id/search_container"),
                        "Не найдена строка поиска",
                        5);
        mainPageObject
                .waitForElementAndSendKeys(
                        By.id("org.wikipedia:id/search_src_text"),
                        "java",
                        "Не удалось ввести значение в строку поиска",
                        5);
        mainPageObject
                .assertFindResultsHasText(
                        By.id("org.wikipedia:id/page_list_item_title"),
                        "java",
                        "Не все результаты поиска содержат заданый текст");
    }




    @Test
    public void testSaveTwoArticlesToMyListAndDeleteOneOfThem() {
        mainPageObject
                .waitForElementAndClick(
                        By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                        "Не найдена кнопка пропуска настроек",
                        5);
        mainPageObject
                .waitForElementAndClick(
                        By.id("org.wikipedia:id/search_container"),
                        "Не найдена строка поиска",
                        5);
        mainPageObject
                .waitForElementAndSendKeys(
                        By.id("org.wikipedia:id/search_src_text"),
                        "java",
                        "Не удалось ввести значение в строку поиска",
                        5);
        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                        "Не отображается результат поиска",
                        15);
        mainPageObject
                .waitForElementAndClick(
                        By.id("org.wikipedia:id/page_save"),
                        "Не удалось нажать кнопку добавления в список для чтения",
                        5);
        mainPageObject
                .waitForElementAndClick(
                        By.id("org.wikipedia:id/snackbar_action"),
                        "Не удалось нажать кнопку подтверждения добавления в список для чтения",
                        5);
        String readingListName = "Reading list";
        mainPageObject
                .waitForElementAndSendKeys(
                        By.id("org.wikipedia:id/text_input"),
                        readingListName,
                        "Не удалось ввести название списка для чтения",
                        5);
        mainPageObject
                .waitForElementAndClick(
                        By.id("android:id/button1"),
                        "Не удалось подвердить создание списка для чтения",
                        5);
        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[@content-desc='Navigate up']"),
                        "Не удалось вернуться к результатам поиска",
                        5);
        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='High-level programming language']"),
                        "Не отображается результат поиска",
                        15);
        mainPageObject
                .waitForElementAndClick(
                        By.id("org.wikipedia:id/page_save"),
                        "Не удалось нажать кнопку добавления в список для чтения",
                        5);
        mainPageObject
                .waitForElementAndClick(
                        By.id("org.wikipedia:id/snackbar_action"),
                        "Не удалось нажать кнопку подтверждения добавления в список для чтения",
                        5);
        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[@text='" + readingListName + "']"),
                        "Не удалось нажать кнопку подтверждения добавления в список для чтения",
                        5);
        mainPageObject
                .waitForElementAndClick(
                        By.id("org.wikipedia:id/snackbar_action"),
                        "Не удалось перейти в спискок для чтения",
                        5);
        mainPageObject
                .swipeElementToLeft(
                        By.xpath("//*[@text='Java (programming language)']"),
                        "Не удалось свайпнуть элемент влево");
        mainPageObject
                .waitForElementNotPresent(
                        By.xpath("//*[@text='Java (programming language)']"),
                        "Не удалось удалить элемент из списка",
                        5);
        mainPageObject
                .waitForElementPresent(
                        By.xpath("//*[@text='JavaScript']"),
                        "В списке для чтения не осталось нужной статьи",
                        5);
    }

    @Test
    public void testTitlePresent() {
        mainPageObject
                .waitForElementAndClick(
                        By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                        "Не найдена кнопка пропуска настроек",
                        5);
        mainPageObject
                .waitForElementAndClick(
                        By.id("org.wikipedia:id/search_container"),
                        "Не найдена строка поиска",
                        5);
        mainPageObject
                .waitForElementAndSendKeys(
                        By.id("org.wikipedia:id/search_src_text"),
                        "java",
                        "Не удалось ввести значение в строку поиска",
                        5);
        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                        "Не отображается результат поиска",
                        15);
        mainPageObject
                .assertElementPresent(
                        By.xpath("//*[@content-desc='Java (programming language)']"),
                        "Не найден заголовок страницы");

    }
}

