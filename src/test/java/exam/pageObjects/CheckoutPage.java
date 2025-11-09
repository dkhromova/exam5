package exam.pageObjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {

    @Step("Заполняем форму Checkout")
    public CheckoutPage fillForm(String firstName, String lastName, String postalCode) {
        $("[data-test='firstName']").setValue(firstName);
        $("[data-test='lastName']").setValue(lastName);
        $("[data-test='postalCode']").setValue(postalCode);
        return this;
    }

    @Step("Нажимаем кнопку Continue")
    public CheckoutOverviewPage clickContinue() {
        $("[data-test='continue']").click();
        return new CheckoutOverviewPage();
    }

}
