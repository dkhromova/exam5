package exam.tests.api.contract;


import exam.base.BaseTestAPI;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("API Contract Tests")
@Feature("Получение сотрудника по имени")
@DisplayName("Получение сотрудника по Name - Контрактные тесты")
public class GetEmployeeByNameContractTests extends BaseTestAPI {


    @Disabled("Ошибка из-за сваггера")
    @Test
    @Story("Проверка, что сотрудник находится по имени")
    @Description("Проверка, что по имени можно успешно найти существующего сотрудника")
    @DisplayName("Получение сотрудника по имени")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    public void getEmployeeByNameCode200() {
        Response response = given()
                .pathParam("name", "Alice")
                .when()
                .get("/employee/name/{name}");

        assertEquals(200, response.statusCode());
    }

    @Test
    @Story("Проверка, что сотрудник не найден")
    @Description("Проверка, что по несуществующему имени сотрудник не найден и пришел код 404")
    @DisplayName("Получение сотрудника по несуществующему имени")
    @Tags({@Tag("Негативный"), @Tag("Смоук")})
    public void getEmployeeByCode404() {
        String nonExistentName = "NonExistentName";

        given()
                .pathParam("name", nonExistentName)
                .when()
                .get("/employee/name/{name}")
                .then()
                .statusCode(404)
                .body("error", equalTo("Employee with name '" + nonExistentName + "' not found")); // если API возвращает сообщение об ошибке
    }


}
