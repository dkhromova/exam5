package exam.tests.ui;

import exam.base.BaseUI;
import exam.pageObjects.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Epic("UI Tests")
@Feature("Авторизация")
public class LoginTests extends BaseUI {
    LoginPage loginPage = new LoginPage();

    @DisplayName("Успешная авторизация")
    @Story("Как пользователь я могу авторизоваться в магазине")
    @Description("Авторизация по корректным кредам не заблокированного пользователя")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    @Test
    void successfulLoginTest() {
        loginPage
                .setUsername("standard_user")
                .setPassword("secret_sauce")
                .clickLoginSuccess()
                .checkIsOpened();
    }

    @DisplayName("Авторизация заблокированного пользователя")
    @Story("Как заблокированный пользователь я не могу авторизоваться в магазине")
    @Description("Авторизация по заблокированным кредам пользователя")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    @Test
    void blockedUserLoginTest() {
        loginPage
                .setUsername("locked_out_user")
                .setPassword("secret_sauce")
                .clickLoginFail()  // используем метод для неуспешного логина
                .checkError("Epic sadface: Sorry, this user has been locked out.");
    }

}