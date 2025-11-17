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
@DisplayName("Авторизация в магазине")
public class LoginTests extends BaseUI {
    LoginPage loginPage = new LoginPage();

    @DisplayName("Успешная авторизация")
    @Story("Как пользователь я могу авторизоваться в магазине")
    @Description("Авторизация по корректным кредам не заблокированного пользователя")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    @Test
    public void successfulLoginTest() {
        loginPage
                .setUsername("standard_user")
                .setPassword("secret_sauce")
                .clickLoginSuccess()
                .checkIsOpened();
    }

    @DisplayName("Авторизация замедленного пользователя")
    @Story("Как пользователь с плохим интернетом я могу авторизоваться в магазине")
    @Description("Авторизация по корректным кредам пользователя с задержкой")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    @Test
    public void performanceGlitchUserLoginTest() {
        loginPage
                .setUsername("performance_glitch_user")
                .setPassword("secret_sauce")
                .clickLoginSuccess()
                .checkIsOpened();
    }

}