package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class AuthorizationPageObject extends MainPageObject {

    private static final String
            MAIN_MENU = "id:mw-mf-main-menu-button",
            LOGIN_BUTTON = "xpath://*[@data-event-name='menu.login']",
            LOGIN_INPUT = "id:wpName1",
            PASSWORD_INPUT = "id:wpPassword1",
            SUBMIT_BUTTON = "xpath://*[@name='wploginattempt']";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton() {
        this.waitForElementAndClick(MAIN_MENU, "Не удалось кликнуть главное меню", 10);
        this.waitForElementPresent(LOGIN_BUTTON, "Не найдена кнопка авторизации", Duration.ofSeconds(5));
        this.waitForElementAndClick(LOGIN_BUTTON, "Не удалось кликнуть кнопку авторизации", 10);
    }

    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Не удалось ввести логин", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Не удалось ввести пароль", 5);
    }

    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Не удалось подтвердить ввод логина-пароля", 5);
    }
}
