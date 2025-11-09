package exam.pageObjects;


import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductsPage {

    @Step("Проверяем, что открылась страница товаров")
    public void checkIsOpened() {
        $(".app_logo").shouldHave(text("Swag Labs"));
        $("[data-test='inventory-container']").shouldBe(visible);
        $$("[data-test='inventory-item']").shouldHave(sizeGreaterThan(0));

    }


    @Step("Добавляем товар '{productName}' в корзину")
    public void addToCart(String productName) {
        try {
            String productId = productName
                    .toLowerCase()
                    .replace(" ", "-")
                    .replace("™", "")
                    .replace("®", "");

            String selector = String.format("[data-test='add-to-cart-%s']", productId);

            if ($(selector).exists()) {
                $(selector).click();
                System.out.println("✅ Товар добавлен в корзину: " + productName);
            } else {
                throw new IllegalArgumentException("Элемент не найден для товара: " + productName);
            }

        } catch (Exception e) {
            System.err.println("⚠ Ошибка при добавлении товара в корзину: " + productName);
            e.printStackTrace();
            throw e;
        }

    }


    @Step("Переходим в корзину")
    public CartPage goToCart() {
        $("[data-test='shopping-cart-link']").click();
        return new CartPage();
    }
}