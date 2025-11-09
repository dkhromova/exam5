package exam.tests.api.business;

import exam.base.BaseTestAPI;
import exam.entities.EmployeeRequest;
import exam.entities.EmployeeResponse;
import exam.helpers.AuthHelper;
import exam.helpers.EmployeeHelper;
import exam.helpers.EmployeeHelperDB;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("API Business Tests")
@Feature("Удаление сотрудника")
public class DeleteEmployeeBusinessTests extends BaseTestAPI {


    @DisplayName("Проверка, что после удаления сотрудника его больше нет в БД")
    @Description("Удаление сотрудника и проверка, что он корректно удалился и его нет в БД")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    @Test
    public void deleteEmployeeDB() throws Exception {

        EmployeeRequest employee = new EmployeeRequest("Omsk", "Alice", "QA", "Ivanova");
        int createdEmployeeId = employeeHelper.createEmployee(employee);
        given().
                header("Authorization", "Bearer " + token).
                when().
                delete("/employee/" + createdEmployeeId).
                then().
                statusCode(200);

        EmployeeResponse deletedEmployee = employeeHelperDB.getEmployee(createdEmployeeId);

        assertEquals(0, deletedEmployee.getId(), "Сотрудника нет в БД");

    }
}