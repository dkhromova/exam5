package exam.tests.api.contract;


import exam.base.BaseTestAPI;
import exam.entities.EmployeeRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.isA;

@Epic("API Contract Tests")
@Feature("Получение информации по сотруднику")
public class GetEmployeeContractTests extends BaseTestAPI {

    @BeforeEach
    public void setUp() {
        EmployeeRequest employee = new EmployeeRequest("Omsk", "Alice", "QA", "Ivanova");
        createdEmployeeId = employeeHelper.createEmployee(employee);
    }

    @Test
    @Story("Проверка, что пришел список существующих сотрудников")
    @Description("Проверка, что при получении списка сотрудников пришел код 200")
    @DisplayName("Получение списка сотрудников")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    public void getEmployeesCode200() {
        given().
                when().
                get("/employees").
                then().
                statusCode(200);
    }

    @Test
    @Story("Проверка полей у существующих сотрудников")
    @Description("Проверка, что по приходят корректные поля в списке сотрудников")
    @DisplayName("Проверка полей в теле ответа при получении списка сотрудников")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    public void getEmployeesResponseBodyFields() {
        given().
                when().
                get("/employees").
                then().
                contentType(ContentType.JSON)
                .body("", isA(List.class))
                .body("id", everyItem(notNullValue()))
                .body("name", everyItem(notNullValue()))
                .body("surname", everyItem(notNullValue()))
                .body("position", everyItem(notNullValue()));
    }

}
