package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lib.Platform;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected static RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание появления элемента")
    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    @Step("Ожидание появления коллекции элементов")
    public List<WebElement> waitForElementsCollectionPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 1));
    }

    @Step("Ожидание появления элемента с заданной задержкой")
    public WebElement waitForElementPresent(String locator, String error_message, Duration duration) {
        return waitForElementPresent(locator, error_message, 5);
    }

    @Step("Ожидание появления жлемента и клик на него")
    public WebElement waitForElementAndClick(String locator, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    @Step("Ожидание появления элемента и ввод текста")
    public WebElement waitForElementAndSendKeys(String locator, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    @Step("Ожидание исчезновения элемента")
    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));
    }

    @Step("Очистка поля ввода")
    public WebElement waitForElementAndClear(String locator, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }

    @Step("Проверка наличия текста у элемента")
    public WebElement assertElementHasText(String locator, String expectedText, String errorMessage) {
        WebElement element = waitForElementPresent(locator, "Не найден элемент", 5);
        String actualText = element.getText();
        Assert.assertEquals(
                errorMessage,
                expectedText,
                actualText
        );
        return element;
    }

    @Step("Провека наличия заданного результата '{expectrdText}' в результате поиска")
    public void assertFindResultsHasText(String locator, String expectedText, String errorMessage) {
        List<WebElement> elements = waitForElementsCollectionPresent(locator, "Не найден элемент", 5);
        for (WebElement element : elements) {
            String actualText = element.getText();
            Assert.assertTrue(
                    errorMessage,
                    actualText.toLowerCase().contains(expectedText.toLowerCase())
            );
        }
    }

    @Step("Прокрутка")
    public void swipeUp(int timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);
            action
                    .press(PointOption.point(x, start_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(PointOption.point(x, end_y))
                    .release()
                    .perform();
        } else {
            System.out.println("Метод swipeUp() не поддерживается для " + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String errorMessage, int maxSwipes) {
        By by = this.getLocatorByString(locator);
        if (driver instanceof AppiumDriver) {
            int alreadySwipe = 0;
            while (driver.findElements(by).size() == 0) {
                if (alreadySwipe > maxSwipes) {
                    waitForElementPresent(locator, "Не найден элемент при помощи свайпа \n" + errorMessage, 0);
                    return;
                }
                swipeUpQuick();
                alreadySwipe++;
            }
        } else {
            System.out.println("Метод swipeUpToFindElement() не поддерживается для " + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeElementToLeft(String locator, String errorMessage) {
        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(locator, errorMessage, 10);
            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action
                    .press(PointOption.point(right_x, middle_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
                    .moveTo(PointOption.point(left_x, middle_y))
                    .release()
                    .perform();
        } else {
            System.out.println("Метод swipeElementToLeft() не поддерживается для " + Platform.getInstance().getPlatformVar());
        }
    }

    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementsNotPresent(String locator, String errorMessage) {
        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements > 0) {
            String defaultMessage = "Предполагается, что элемент " + locator + " отсутствует";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public void assertElementPresent(String locator, String errorMessage) {
        By by = this.getLocatorByString(locator);
        try {
            driver.findElement(by);
        } catch (Exception e) {
            throw new AssertionError(errorMessage);
        }
    }

    public boolean isElementPresent(String locator) {
        return getAmountOfElements(locator) > 0;
    }

    private By getLocatorByString(String locatorWithType) {
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];
        if (byType.equals("xpath")) {
            return By.xpath(locator);
        } else if (byType.equals("id")) {
            return By.id(locator);
        } else if (byType.equals("css")) {
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Неизвестный тип локатора " + locatorWithType);
        }
    }

    @Step("Снимок экрана")
    public static String takeScreenshot(String name) {
        TakesScreenshot takesScreenshot = driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/" + name + "_screenshot.png";
        try {
            FileUtils.copyFile(source, new File(path));
            System.out.println("The screenshot was taken: " + path);
        } catch (Exception e) {
            System.out.println("Cannot take screenshot. Error: " + e.getMessage());
        }
        return path;
    }

    @Attachment
    public static byte[] screenshot(String path) {
        byte[] bytes = new byte[0];

        try {
            bytes = Files.readAllBytes(Path.of(path));
        } catch (IOException e) {
            System.out.println("Cannot get bytes from screenshot. Error: " + e.getLocalizedMessage());
        }
        return bytes;
    }
}
