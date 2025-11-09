package exam.pageObjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    @Step("Вводим логин: {username}")
    public LoginPage setUsername(String username) {
        $("[data-test='username']").shouldBe(visible).setValue(username);
        return this;
    }

    @Step("Вводим пароль: {password}")
    public LoginPage setPassword(String password) {
        $("[data-test='password']").shouldBe(visible).setValue(password);
        return this;
    }

    @Step("Нажимаем на кнопку авторизации ")
    public ProductsPage clickLoginSuccess() {
        $("[data-test='login-button']").shouldBe(visible).click();
        return new ProductsPage();
    }

    @Step("Пробуем авторизоваться неуспешно")
    public LoginPage clickLoginFail() {
        $("[data-test='login-button']").click();
        return this;
    }

    @Step("Проверяем появление ошибки авторизации: {expectedText}")
    public void checkError(String expectedText) {
        $("[data-test='error']").shouldBe(visible).shouldHave(text(expectedText));
    }

}
