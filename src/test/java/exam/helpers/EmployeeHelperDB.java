package exam.helpers;

import exam.entities.EmployeeRequest;
import exam.entities.EmployeeResponse;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static io.restassured.RestAssured.given;

public class EmployeeHelperDB extends AbstractHelper {

    public EmployeeHelperDB() throws SQLException, IOException {
        connection = getConnection();
    }

    public int createEmployee(EmployeeRequest employee) throws SQLException {
        String INSERT_EMPLOYEE = "INSERT INTO employee(\"name\",\"surname\",\"city\",\"position\") values(?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getSurname());
        preparedStatement.setString(3, employee.getCity());
        preparedStatement.setString(4, employee.getPosition());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            return resultSet.getInt("id");
        } else {
            return -1;
        }
    }

    public EmployeeResponse getEmployee(int id) throws Exception {
        String SELECT_NAME_SURNAME = "SELECT * FROM employee where id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NAME_SURNAME);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new EmployeeResponse(
                    resultSet.getString("city"),
                    resultSet.getString("name"),
                    resultSet.getString("position"),
                    resultSet.getString("surname"),
                    resultSet.getInt("id")
            );
        } else {
            return new EmployeeResponse();
        }
    }

    public void deleteEmployee(int id) {
        given().
                when().
                delete("/employee/" + id);
    }

}