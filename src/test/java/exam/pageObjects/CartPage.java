package exam.pageObjects;


import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

    @Step("Проверяем, что в корзине {expectedCount} товара(ов)")
    public CartPage checkItemsCount(int expectedCount) {
        $$("[data-test='inventory-item']").shouldHave(size(expectedCount));
        return this;
    }

    @Step("Нажимаем кнопку Checkout")
    public CheckoutPage clickCheckout() {
        $("[data-test='checkout']").click();
        return new CheckoutPage();
    }
}
