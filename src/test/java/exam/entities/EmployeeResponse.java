package exam.entities;

import io.qameta.allure.Step;

public class EmployeeResponse {
    private String city;
    private String name;
    private String position;
    private String surname;
    private int id;

    public EmployeeResponse() {
    }

    public EmployeeResponse(String city, String name, String position, String surname) {
        this.city = city;
        this.name = name;
        this.position = position;
        this.surname = surname;
    }

    public EmployeeResponse(String name, String position, String surname) {
        this.name = name;
        this.position = position;
        this.surname = surname;
    }

    public EmployeeResponse(String city, String name, String position, String surname, int id) {
        this.city = city;
        this.name = name;
        this.position = position;
        this.surname = surname;
        this.id = id;
    }

    @Step("Получаем город сотрудника")
    public String getCity() {
        return city;
    }

    @Step("Меняем город сотрудника на {city}")
    public void setCity(String city) {
        this.city = city;
    }

    @Step("Получаем имя сотрудника")
    public String getName() {
        return name;
    }

    @Step("Меняем имя сотрудника на {name}")
    public void setName(String name) {
        this.name = name;
    }

    @Step("Получаем должность сотрудника")
    public String getPosition() {
        return position;
    }

    @Step("Меняем должность сотрудника на {position}")
    public void setPosition(String position) {
        this.position = position;
    }

    @Step("Получаем фамилию сотрудника")
    public String getSurname() {
        return surname;
    }

    @Step("Меняем фамилию сотрудника на {surname}")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Step("Получаем id сотрудника")
    public int getId() {
        return id;
    }

    @Step("Меняем id сотрудника на {id}")
    public void setId(int id) {
        this.id = id;
    }

    @Step("Получаем данные по сотруднику")
    @Override
    public String toString() {
        return "EmployeeResponse{" +
                "city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

}
