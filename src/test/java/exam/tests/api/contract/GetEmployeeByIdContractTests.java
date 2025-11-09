package exam.tests.api.contract;


import exam.base.BaseTestAPI;
import exam.entities.EmployeeRequest;
import exam.entities.EmployeeResponse;
import exam.helpers.EmployeeHelper;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

@Epic("API Contract Tests")
@Feature("Получение сотрудника по ID")
public class GetEmployeeByIdContractTests extends BaseTestAPI {

    @BeforeEach
    public void setUp() {
        EmployeeRequest employee = new EmployeeRequest("Omsk", "Alice", "QA", "Ivanova");
        createdEmployeeId = employeeHelper.createEmployee(employee);

    }

    @Test
    @Story("Получение сотрудника по существующему id")
    @Description("Проверка, что сотрудник с заданным id возвращается корректно")
    @DisplayName("Получение сотрудника по id")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    public void getEmployeeByIdCode200() {

        given()
                .pathParam("id", createdEmployeeId)
                .when()
                .get("/employee/{id}")
                .then()
                .statusCode(200);

    }

    @Test
    @Story("Получение сотрудника по несуществующему id")
    @Description("Проверка, что запрос с несуществующим id возвращает 404")
    @DisplayName("Получение сотрудника по несуществующему id")
    @Tags({@Tag("Негативный"), @Tag("Смоук")})
    public void getEmployeeByNonExistentId() {
        int nonExistentId = 99999;

        given()
                .pathParam("id", nonExistentId)
                .when()
                .get("/employee/{id}")
                .then()
                .statusCode(404)
                .body("error", equalTo("Employee with id '" + nonExistentId + "' not found"));
    }

    @Test
    @Story("Получение сотрудника по id")
    @Description("Проверка, что запрос вернул только одного сотрудника")
    @DisplayName("Проверка, что пришел только один сотрудник")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    public void getEmployeeByIdSingleCheck() {

        Response response = given()
                .pathParam("id", createdEmployeeId)
                .when()
                .get("/employee/{id}");

        response.then().statusCode(200);

        EmployeeResponse employeeResponse = response.as(EmployeeResponse.class);

        assertEquals(createdEmployeeId, employeeResponse.getId());
        assertEquals("Alice", employeeResponse.getName());
        assertEquals("Ivanova", employeeResponse.getSurname());
        assertEquals("QA", employeeResponse.getPosition());
        assertEquals("Omsk", employeeResponse.getCity());
    }

}
