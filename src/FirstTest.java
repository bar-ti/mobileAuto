import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\USER\\IdeaProjects\\mobileAuto\\apks\\Wikipedia_2.7.50437-r-2023-04-12_Apkpure.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Не найдена кнопка пропуска настроек",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Не открывается главная страница приложения",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "java",
                "Не найдена строка поиска",
                5);

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Не отображается результат поиска",
                15);
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Не найдена кнопка пропуска настроек",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдена строка поиска",
                5);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                " ",
                "Не удалось ввести значение в строку поиска",
                5);

        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),
                "Не удалось очистить строку поиска",
                5);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                " ",
                "Не удалось ввести значение в строку поиска",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Не найдена кнопка X отмены поиска",
                5);

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Не исчезла кнопка X отмены поиска",
                5);
    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Не найдена кнопка пропуска настроек",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдена строка поиска",
                5);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "Не удалось ввести значение в строку поиска",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Не отображается результат поиска",
                15);

        WebElement titleElement = waitForElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Не удалось найти заголовок статьи",
                15);

        String articleTitle = titleElement.getAttribute("name");

        Assert.assertEquals(
                "Заловок статьи не соответсвует ожидаемому результату",
                "Object-oriented programming language",
                articleTitle
        );
    }

    @Test
    public void checkWikiSearchTextTest() {
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Не найдена кнопка пропуска настроек",
                5);

        assertElementHasText(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@class='android.widget.TextView']"),
                "Search Wikipedia",
                "Элемент не содержит ожидаемого текста");
    }

    @Test
    public void checkSearchResultAndCancelSearchTest() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Не найдена кнопка пропуска настроек",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдена строка поиска",
                5);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "Не удалось ввести значение в строку поиска",
                5);

        waitForElementPresent(
                By.id("org.wikipedia:id/search_results_display"),
                "Не отображаются результаты поиска",
                15);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Не найдена кнопка X отмены поиска",
                5);

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_results_display"),
                "Не исчезли результаты поиска",
                5);
    }

    @Test
    public void checkTextInSearchResultTest() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Не найдена кнопка пропуска настроек",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдена строка поиска",
                5);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "Не удалось ввести значение в строку поиска",
                5);

        assertFindResultsHasText(
                By.id("org.wikipedia:id/page_list_item_title"),
                "java",
                "Не все результаты поиска содержат заданый текст");
    }

    @Test
    public void testSwipeArticle() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Не найдена кнопка пропуска настроек",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдена строка поиска",
                5);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "Не удалось ввести значение в строку поиска",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'  and @text = 'Appium']"),
                "Не отображается результат поиска",
                15);

        swipeUpToFindElement(
                By.xpath("//*[@content-desc = 'View article in browser']"),
                "Не удалось проскроллить статью до конца",
                20);

    }

    @Test
    public void SaveFirstArticleToMyList() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Не найдена кнопка пропуска настроек",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдена строка поиска",
                5);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "Не удалось ввести значение в строку поиска",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Не отображается результат поиска",
                15);

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Не удалось нажать кнопку добавления в список для чтения",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Не удалось нажать кнопку подтверждения добавления в список для чтения",
                5);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Reading list",
                "Не удалось ввести название списка для чтения",
                5);

        waitForElementAndClick(
                By.id("android:id/button1"),
                "Не удалось подвердить создание списка для чтения",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Не удалось перейти в спискок для чтения",
                5);

        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Не удалось свайпнуть элемент влево");

        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "не удалось удалить элемент из списка",
                5);
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Не найдена кнопка пропуска настроек",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдена строка поиска",
                5);

        String searchLine = "Linkin Park Discography";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                searchLine,
                "Не удалось ввести значение в строку поиска",
                5);

        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']";
        waitForElementPresent(
                By.xpath(searchResultLocator),
                "Не отображается результат поиска по запросу " + searchLine,
                15);

        int amountOfSearchResults = getAmountOfElements(By.xpath(searchResultLocator));
        Assert.assertTrue("Не найдены результаты поиска", amountOfSearchResults > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Не найдена кнопка пропуска настроек",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдена строка поиска",
                5);

        String searchLine = "ytjudtudtru";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                searchLine,
                "Не удалось ввести значение в строку поиска",
                5);

        String searchResultLocator = "org.wikipedia:id/page_list_item_description";
        String emptyResultLabel = "//*[@text='No results']";
        waitForElementPresent(
                By.xpath(emptyResultLabel),
                "Не отображается окно отсутствия результатов",
                15);

        assertElementsNotPresent(
                By.id(searchResultLocator),
                "Результаты по запросу " + searchLine + " были найдены");
    }

    @Test
    public void testScreenOrientationSearchResults() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Не найдена кнопка пропуска настроек",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдена строка поиска",
                5);

        String searchLine = "java";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                searchLine,
                "Не удалось ввести значение в строку поиска",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Не отображается результат поиска",
                15);

        String attribute = "name";
        String titleBeforeRotation = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                attribute,
                "У элемента не найден атрибут " + attribute,
                15);

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String titleAfterRotation = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                attribute,
                "Не у элемента не найден атрибут ",
                15);

        Assert.assertEquals(
                "Заголовок статьи изменился после поворота экрана",
                titleBeforeRotation,
                titleAfterRotation);

        driver.rotate(ScreenOrientation.PORTRAIT);

        String titleAfterSecondRotation = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                attribute,
                "Не у элемента не найден атрибут ",
                15);

        Assert.assertEquals(
                "Заголовок статьи изменился после поворота экрана",
                titleBeforeRotation,
                titleAfterSecondRotation);
    }

    @Test
    public void testCheckSearchArticleInBackgrounf() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Не найдена кнопка пропуска настроек",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдена строка поиска",
                5);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "Не удалось ввести значение в строку поиска",
                5);

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Не отображается результат поиска",
                15);

        driver.runAppInBackground(2);

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Не отображается результат поиска после возвращения из бэкграунда",
                15);
    }

    @Test
    public void saveTwoArticlesToMyListAndDeleteOneOfThemTest() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Не найдена кнопка пропуска настроек",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдена строка поиска",
                5);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "Не удалось ввести значение в строку поиска",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Не отображается результат поиска",
                15);

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Не удалось нажать кнопку добавления в список для чтения",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Не удалось нажать кнопку подтверждения добавления в список для чтения",
                5);
        String readingListName = "Reading list";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                readingListName,
                "Не удалось ввести название списка для чтения",
                5);

        waitForElementAndClick(
                By.id("android:id/button1"),
                "Не удалось подвердить создание списка для чтения",
                5);

        waitForElementAndClick(
                By.xpath("//*[@content-desc='Navigate up']"),
                "Не удалось вернуться к результатам поиска",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='High-level programming language']"),
                "Не отображается результат поиска",
                15);

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Не удалось нажать кнопку добавления в список для чтения",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Не удалось нажать кнопку подтверждения добавления в список для чтения",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text='" + readingListName + "']"),
                "Не удалось нажать кнопку подтверждения добавления в список для чтения",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Не удалось перейти в спискок для чтения",
                5);

        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Не удалось свайпнуть элемент влево");

        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Не удалось удалить элемент из списка",
                5);
        waitForElementPresent(
                By.xpath("//*[@text='JavaScript']"),
                "В списке для чтения не осталось нужной статьи",
                5);
    }

    @Test
    public void titlePresentTest() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Не найдена кнопка пропуска настроек",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найдена строка поиска",
                5);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "Не удалось ввести значение в строку поиска",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Не отображается результат поиска",
                15);

        assertElementPresent(
                By.xpath("//*[@content-desc='Java (programming language)']"),
                "Не найден заголовок страницы");

    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private List<WebElement> waitForElementsCollectionPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 1));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }

    private WebElement assertElementHasText(By by, String expectedText, String errorMessage) {
        WebElement element = waitForElementPresent(by, "Не найден элемент", 5);
        String actualText = element.getText();
        Assert.assertEquals(
                errorMessage,
                expectedText,
                actualText
        );
        return element;
    }

    private void assertFindResultsHasText(By by, String expectedText, String errorMessage) {
        List<WebElement> elements = waitForElementsCollectionPresent(by, "Не найден элемент", 5);
        for (WebElement element : elements) {
            String actualText = element.getText();
            Assert.assertTrue(
                    errorMessage,
                    actualText.toLowerCase().contains(expectedText.toLowerCase())
            );
        }
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String errorMessage, int maxSwipes) {
        int alreadySwipe = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwipe > maxSwipes) {
                waitForElementPresent(by, "Не найден элемент при помощи свайпа \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            alreadySwipe++;
        }
    }

    protected void swipeElementToLeft(By by, String errorMessage) {
        WebElement element = waitForElementPresent(by, errorMessage, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementsNotPresent(By by, String errorMessage) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String defaultMessage = "Предполагается, что элемент " + by.toString() + " отсутствует";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        return element.getAttribute(attribute);
    }


    private void assertElementPresent(By by, String errorMessage) {
        try {
            driver.findElement(by);
        }
        catch (Exception e ){
            throw new AssertionError(errorMessage);
        }
    }
}

