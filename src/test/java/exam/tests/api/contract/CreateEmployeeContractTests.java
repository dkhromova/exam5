package exam.tests.api.contract;


import exam.base.BaseTestAPI;
import exam.entities.EmployeeRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

@Epic("API Contract Tests")
@Feature("Создание сотрудника")
public class CreateEmployeeContractTests extends BaseTestAPI {


    @DisplayName("Создание сотрудника с валидным телом")
    @Description("Проверка корректного создания сотрудника с валидными данными через API")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    @Test
    public void createEmployeeCode201() {
        given().
                body(new EmployeeRequest("Omsk", "Alice", "QA", "Ivanova")).
                contentType(ContentType.JSON).
                header("Authorization", "Bearer " + token).
                when().
                post("/employee").
                then().
                statusCode(201).body("message", is(equalTo("Employee created successfully")));
    }


    @DisplayName("Создание сотрудника без обязательных полей")
    @Description("Проверка невозможности создания сотрудника без обязательных полей через API")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    @Test
    public void createEmployeeCode400() {
        given().
                body(new EmployeeRequest("Omsk")).
                contentType(ContentType.JSON).
                header("Authorization", "Bearer " + token).
                when().
                post("/employee").
                then().
                statusCode(400).body("error", is(equalTo("Missing required fields"))).
                body("missing_fields", hasItems("name", "surname", "position"));
    }

    @DisplayName("Создание сотрудника с пустым телом запросам")
    @Description("Проверка, что сотрудник не был создан, если отправить пустое тело запроса")
    @Tags({@Tag("Негативный"), @Tag("Смоук")})
    @Test
    public void createEmployeeWithoutBody() {
        given().
                body("{}").
                contentType(ContentType.JSON).
                header("Authorization", "Bearer " + token).
                when().
                post("/employee").
                then().
                statusCode(400).body("error", is(equalTo("Missing required fields"))).
                body("missing_fields", hasItems("name", "surname", "position"));
    }

    @DisplayName("Создание сотрудника без авторизации")
    @Description("Проверка, что сотрудник не был создан, если администратор не был авторизован")
    @Tags({@Tag("Негативный"), @Tag("Смоук")})
    @Test
    public void createEmployeeCode401() {
        given().
                body(new EmployeeRequest("Moscow", "Alex", "IT", "Krytoi")).
                contentType(ContentType.JSON).
                when().
                post("/employee").
                then().
                statusCode(401);
    }

}