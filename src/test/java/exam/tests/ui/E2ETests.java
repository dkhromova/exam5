package exam.tests.ui;


import exam.base.BaseUI;
import exam.pageObjects.LoginPage;
import exam.pageObjects.ProductsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

@Epic("UI Tests")
@Feature("Оформление заказа")
public class E2ETests extends BaseUI {

    private LoginPage loginPage;

    @BeforeEach
    void login() {
        loginPage = new LoginPage();
    }


    @DisplayName("Оформление заказа стандартным пользователем")
    @Story("Как пользователь я могу авторизоваться")
    @Story("Как пользователь я могу добавить товар в корзину")
    @Story("Как пользователь я могу перейти в корзину и увидеть добавленные товары")
    @Story("Как пользователь я могу оформить доставку, заполнив форму")
    @Description("Добавление товаров в корзину и оформление доставки обычным пользователем")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    @Test
    public void e2eStandardUserTest() {
        String[] products = {"Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie"};

        loginPage
                .setUsername("standard_user")
                .setPassword("secret_sauce")
                .clickLoginSuccess()
                .checkIsOpened();

        ProductsPage productsPage = new ProductsPage();
        for (String product : products) {
            productsPage.addToCart(product);
        }

        productsPage.goToCart()
                .checkItemsCount(products.length)
                .clickCheckout()
                .fillForm("Daria", "Khromova", "640345")
                .clickContinue()
                .checkTotal("$58.29")
                .clickFinish()
                .checkOrderCompleted();
    }

    @DisplayName("Оформление заказа пользователем с замедленным интернетом")
    @Story("Как пользователь с замедленным интернетом я могу авторизоваться в магазине")
    @Story("Как пользователь с замедленным интернетом я могу добавить товар в корзину")
    @Story("Как пользователь с замедленным интернетом я могу перейти в корзину и увидеть добавленные товары")
    @Story("Как пользователь с замедленным интернетом я могу оформить доставку, заполнив форму")
    @Description("Добавление товаров в корзину и оформление доставки пользователь с замедленным интернетом")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    @Test
    public void e2ePerformanceGlitchUserTest() {
        String[] products = {"Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie"};

        loginPage
                .setUsername("performance_glitch_user")
                .setPassword("secret_sauce")
                .clickLoginSuccess()
                .checkIsOpened();

        ProductsPage productsPage = new ProductsPage();
        for (String product : products) {
            productsPage.addToCart(product);
        }

        productsPage.goToCart()
                .checkItemsCount(products.length)
                .clickCheckout()
                .fillForm("Daria", "Khromova", "640345")
                .clickContinue()
                .checkTotal("$58.29")
                .clickFinish()
                .checkOrderCompleted();
    }


}