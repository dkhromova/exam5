package exam.helpers;

import exam.entities.User;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;


public class AuthHelper {
    public AuthHelper() {
        RestAssured.baseURI = "https://innopolispython.onrender.com";
    }

    @Step("Логинимся и получаем токен")
    public String getToken(String username, String password) {
        return given().
                body(new User(username, password)).contentType(ContentType.JSON).
                when().
                post("/login").jsonPath().getString("token");

    }
}
