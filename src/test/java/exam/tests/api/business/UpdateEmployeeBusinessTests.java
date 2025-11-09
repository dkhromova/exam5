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
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

@Epic("API Business Tests")
@Feature("Обновление сотрудника")
public class UpdateEmployeeBusinessTests extends BaseTestAPI {


    @DisplayName("Проверка, что после обновления сотрудника в БД тоже обновились данные")
    @Description("Проверяем, что после обновления данных о сотруднике через API в БД также обновились его данные")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    @Test
    public void updateEmployeeDB() throws Exception {

        EmployeeRequest employee = new EmployeeRequest("Omsk", "Alice", "QA", "Ivanova");
        int createdEmployeeId = employeeHelper.createEmployee(employee);

        EmployeeRequest updatedEmployee = new EmployeeRequest("Moscow", "Alice", "QA", "Ivanova");

        given().
                body(updatedEmployee).
                contentType(ContentType.JSON).
                header("Authorization", "Bearer " + token).
                when().
                put("/employee/" + createdEmployeeId).
                then().
                statusCode(200);

        EmployeeResponse employeeFromDB = employeeHelperDB.getEmployee(createdEmployeeId);

        assertEquals("Город должен быть обновлён",updatedEmployee.getCity(), employeeFromDB.getCity());
        assertEquals( "Имя должно совпадать", updatedEmployee.getName(), employeeFromDB.getName());
        assertEquals("Фамилия должна совпадать", updatedEmployee.getSurname(), employeeFromDB.getSurname());
        assertEquals( "Должность должна совпадать", updatedEmployee.getPosition(), employeeFromDB.getPosition());
    }
}
