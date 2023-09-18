package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import junit.framework.TestCase;
import lib.ui.SearchPageObject;
import lib.ui.WellcomePageObject;
import lib.ui.factories.WellcomePageObjectFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase {

    protected RemoteWebDriver driver;
    private static String appiumURL = "http://127.0.0.1:4723";

    @Before
    @Step("Запуск драйвера и сессии")
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        this.skipSettings();
        this.openWikiWebPageForMobileWeb();
    }

    @After
    @Step("Завершение работы с драйвером и сессией")
    public void tearDown() {
        driver.quit();
    }

    @Step("Открытие страницы Википелии (только для web)")
    protected void openWikiWebPageForMobileWeb() {
        if (Platform.getInstance().isMW()) {
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Метод openWikiWebPageForMobileWeb() не поддерживается для " + Platform.getInstance());
        }
    }

    @Step("Пропуск настроек Википедии (только для Android)")
    private void skipSettings() {
        if (Platform.getInstance().isAndroid()) {
            WellcomePageObject wellcomePageObject = WellcomePageObjectFactory.get(driver);
            wellcomePageObject.skipSetting();
        } else {
            System.out.println("Метод skipSettings() не поддерживается для " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Поворот экрана в вертикальное положение")
    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Метод rotateScreenPortrait() не поддерживается для " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Поворот экрана в горизонтальное положение")
    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Метод rotateScreenLandscape() не поддерживается для " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Отправка приложения в бэкграунд (только для Android)")
    protected void backgroundApp(int seconds) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else {
            System.out.println("Метод backgroundApp() не поддерживается для " + Platform.getInstance().getPlatformVar());
        }
    }

    private void createAllurePropertyFile() {
        String path = System.getProperty("allure.results.directory");
        try {
            Properties properties = new Properties();
            FileOutputStream fileOutputStream = new FileOutputStream(path + "/environment.properties");
            properties.setProperty("Environment", Platform.getInstance().getPlatformVar());
            properties.store(fileOutputStream, "see wiki allure env");
            fileOutputStream.close();
        } catch (Exception e) {
            System.err.println("IO Problem when writing allure properties file");
            e.printStackTrace();
        }
    }
}
