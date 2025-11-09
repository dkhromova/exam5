package exam.tests.api.contract;


import exam.entities.User;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Epic("API Contract Tests")
@Feature("Авторизация")
public class LoginTests {

    private final User user = new User("admin", "admin");


    @Test
    @Story("Проверка успешной авторизации")
    @Description("Проверка, что авторизация по корректным кредам проходит успешно и приходит код 200")
    @DisplayName("Успешная авторизация")
    @Tags({@Tag("Позитивный"), @Tag("Смоук")})
    public void validLogin() {
        performLogin(user);
    }

    @Test
    @Story("Проверка неуспешной авторизации")
    @Description("Авторизация по некорректным кредам не прошла")
    @DisplayName("Неверный логин или пароль")
    @Tags({@Tag("Негативный"), @Tag("Смоук")})
    public void invalidLogin() {
        User user = new User("admin", "invalid");

        Response response = login(user);
        response.then().statusCode(401);

        String message = response.jsonPath().getString("error");
        assertEquals("Invalid credentials", message, "Ошибка должна сообщать о неверных кредах");

        assertNull(response.jsonPath().getString("token"), "Токен не должен приходить при невалидных кредах");
    }


    @Step("Авторизация пользователя с логином: {user.username}")
    private void performLogin(User user) {
        given()
                .baseUri("https://innopolispython.onrender.com")
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .body("token", is(not(blankString())));
    }

    @Step("Авторизация пользователя с логином: {user.username} и паролем: {user.password}")
    private Response login(User user) {
        return given()
                .baseUri("https://innopolispython.onrender.com")
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post("/login")
                .then()
                .extract()
                .response();
    }

}
