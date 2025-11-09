package exam.entities;

import io.qameta.allure.Step;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Step("Получаем логин сотрудника")
    public String getUsername() {
        return username;
    }

    @Step("Получаем пароль сотрудника")
    public String getPassword() {
        return password;
    }

    @Step("Меняем логин сотрудника")
    public void setUsername(String username) {
        this.username = username;
    }

    @Step("Меняем пароль сотрудника")
    public void setPassword(String password) {
        this.password = password;
    }
}
