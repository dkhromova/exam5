package exam.pageObjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutOverviewPage {

    @Step("Проверяем, что сумма заказа равна {expectedTotal}")
    public CheckoutOverviewPage checkTotal(String expectedTotal) {
        $("[data-test='total-label']").shouldHave(text(expectedTotal));
        return this;
    }

    @Step("Нажимаем Finish")
    public CompletePage clickFinish() {
        $("[data-test='finish']").click();
        return new CompletePage();
    }

}