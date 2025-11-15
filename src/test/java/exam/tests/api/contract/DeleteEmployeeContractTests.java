package exam.tests.api.contract;


import exam.base.BaseTestAPI;
import exam.entities.EmployeeRequest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("API Contract Tests")
@Feature("Удаление сотрудника")
public class DeleteEmployeeContractTests extends BaseTestAPI {


    @Test
    @Story("Проверка кода ответа при удалении сотрудника")
    @Description("Проверка, что сотрудник был успешно удален")
    @DisplayName("Проверка кода ответа после удаления существующего сотрудника")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    public void deleteEmployeeCode200() {

        EmployeeRequest employee = new EmployeeRequest("Omsk", "Alice", "QA", "Ivanova");
        int createdEmployeeId = employeeHelper.createEmployee(employee);

        Response response = given().
                header("Authorization", "Bearer " + token).
                when().
                delete("/employee/" + createdEmployeeId).
                then().
                statusCode(200).extract().response();

        assertEquals("Deleted", response.jsonPath().getString("message"));
    }

}

