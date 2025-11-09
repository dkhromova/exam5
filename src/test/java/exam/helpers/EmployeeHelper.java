package exam.helpers;

import exam.entities.EmployeeRequest;
import exam.entities.EmployeeResponse;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class EmployeeHelper extends AbstractHelper {
    private final AuthHelper authHelper;

    public EmployeeHelper() {
        authHelper = new AuthHelper();
        RestAssured.baseURI = "https://innopolispython.onrender.com";
    }


    @Step("Создаем сотрудника: {employee}")
    public int createEmployee(EmployeeRequest employee) {
        String token = authHelper.getToken("admin", "admin");

        JsonPath jsonPath = given()
                .body(employee)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token).
                when().
                post("/employee").jsonPath();
        try {
            return jsonPath.getInt("id");
        } catch (NullPointerException nullPointerException) {
            return -1;
        }
    }

    @Step("Получаем список сотрудников")
    public List<EmployeeResponse> getEmployeeList() {
        Response response = given()
                .when()
                .get("/employees");

        return Arrays.asList(response.as(EmployeeResponse[].class));
    }

    @Step("Получаем сотрудника по id {id}")
    public EmployeeResponse getEmployeeById(int id) {
        Response response = given().
                when().
                get("/employee/" + id);
        try {
            return response.as(EmployeeResponse.class);
        } catch (IllegalStateException exception) {
            return new EmployeeResponse();
        }
    }

    @Step("Получаем сотрудника по имени: {name}")
    public EmployeeResponse getEmployeeByName(String name) {
        Response response = given()
                .pathParam("name", name)
                .when()
                .get("/employee/name/{name}");

        try {
            return response.as(EmployeeResponse.class);
        } catch (IllegalStateException e) {
            return null; // или new EmployeeResponse() если хочешь пустой объект
        }
    }

    @Step("Удаляем сотрудника по id {id}")
    public void deleteEmployee(int id) {
        given().
                when().
                delete("/employee/" + id);
    }
}