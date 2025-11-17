package exam.tests.api.business;

import exam.base.BaseTestAPI;
import exam.entities.EmployeeRequest;
import exam.entities.EmployeeResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@Epic("API Business Tests")
@Feature("Создание сотрудника")
@DisplayName("Создание сотрудника - Бизнес тесты")
public class CreateEmployeeBusinessTests extends BaseTestAPI {


    @DisplayName("Создание сотрудника и проверка в БД")
    @Description("Создание сотрудника через API и проверка, что он появился в БД")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    @Test
    public void createEmployeeDB() throws Exception {
        int employeeId = employeeHelper.createEmployee(new EmployeeRequest("Omsk", "Alice", "QA", "Ivanova"));
        EmployeeResponse employee = employeeHelperDB.getEmployee(employeeId);

        assertEquals(employeeId, employee.getId());
        assertEquals("Alice", employee.getName());
        assertEquals("Ivanova", employee.getSurname());
        assertEquals("QA", employee.getPosition());
        assertEquals("Omsk", employee.getCity());
    }


    @DisplayName("Создание сотрудника и проверка его в GET /employee")
    @Description("Создание сотрудника через API и проверка, что он пришел в ответе метода на получение списка сотрудников")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    @Test
    public void getCreatedEmployeeExist() {
        EmployeeRequest employee = new EmployeeRequest("Omsk", "Alice", "QA", "Ivanova");
        int createdEmployeeId = employeeHelper.createEmployee(employee);
        EmployeeResponse createdEmployee = employeeHelper.getEmployeeById(createdEmployeeId);


        assertAll(
                "Проверка данных созданного сотрудника",
                () -> assertEquals(createdEmployeeId, createdEmployee.getId(), "ID сотрудника не совпадает"),
                () -> assertEquals(employee.getName(), createdEmployee.getName(), "Имя не совпадает"),
                () -> assertEquals(employee.getSurname(), createdEmployee.getSurname(), "Фамилия не совпадает"),
                () -> assertEquals(employee.getCity(), createdEmployee.getCity(), "Город не совпадает"),
                () -> assertEquals(employee.getPosition(), createdEmployee.getPosition(), "Должность не совпадает")
        );


    }


}
