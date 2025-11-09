package exam.tests.api.contract;


import exam.base.BaseTestAPI;
import exam.entities.EmployeeRequest;
import exam.helpers.AuthHelper;
import exam.helpers.EmployeeHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

@Epic("API Contract Tests")
@Feature("Обновление сотрудника")
public class UpdateEmployeeContractTests extends BaseTestAPI {

    @Test
    @Story("Проверка успешного обновления существующего сотрудника")
    @Description("Проверка, что после успешного обновления существующего сотрудника пришел код 200")
    @DisplayName("Проверка кода после обновления существующего сотрудника со всеми полями")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    public void updateEmployeeCode200() {

        EmployeeRequest employee = new EmployeeRequest("Omsk", "Alice", "QA", "Ivanova");
        int createdEmployeeId = employeeHelper.createEmployee(employee);

        given().
                body(new EmployeeRequest("Moscow", "Alice", "QA", "Ivanova")).
                contentType(ContentType.JSON).
                header("Authorization", "Bearer " + token).
                when().
                put("/employee/" + createdEmployeeId).
                then().
                statusCode(200);
    }

    @Test
    @Story("Проверка обновления несуществующего сотрудника")
    @Description("Проверка, что при попытке обновления несуществующего сотрудника пришел код 404")
    @DisplayName("Проверка кода после обновления несуществующего сотрудника")
    @Tags({@Tag("Негативный"), @Tag("Смоук")})
    public void updateEmployeeCode404() {
        int nonExistentId = 999999999;

        Response response = given().
                body(new EmployeeRequest("Moscow", "Alice", "QA", "Ivanova")).
                contentType(ContentType.JSON).
                header("Authorization", "Bearer " + token).
                when().
                put("/employee/" + nonExistentId).
                then().
                statusCode(404).extract().response();

        assertEquals("Employee with id '" + nonExistentId + "' not found", response.jsonPath().getString("error"));
    }
}
