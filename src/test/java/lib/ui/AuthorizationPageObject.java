package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class AuthorizationPageObject extends MainPageObject {

    private static final String
            LOGIN_BUTTON = "xpath://a/span[text()='Log in']",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton() {
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
