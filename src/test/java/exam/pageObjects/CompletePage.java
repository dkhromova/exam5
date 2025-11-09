package exam.pageObjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CompletePage {

    @Step("Проверяем, что заказ завершен")
    public void checkOrderCompleted() {
        $(".complete-header").shouldHave(text("THANK YOU FOR YOUR ORDER"));
    }
}
