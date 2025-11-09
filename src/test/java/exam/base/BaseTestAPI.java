//package exam.base;
//
//import exam.helpers.AuthHelper;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//
//import static io.restassured.RestAssured.baseURI;
//
//public class BaseTestAPI {
//
//    public static AuthHelper authHelper;
//    public static String token;
//
//    @BeforeAll
//    public static void setUri() {
//        baseURI = "https://innopolispython.onrender.com";
//    }
//
//    @BeforeEach
//    public void setUp() {
//        authHelper = new AuthHelper();
//        token = authHelper.getToken("admin", "admin");
//    }
//}

package exam.base;

import exam.helpers.AuthHelper;
import exam.helpers.EmployeeHelper;
import exam.helpers.EmployeeHelperDB;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.sql.SQLException;

public class BaseTestAPI {

    protected static EmployeeHelper employeeHelper;
    protected static EmployeeHelperDB employeeHelperDB;
    protected static AuthHelper authHelper;
    protected static String token;
    protected static int createdEmployeeId;


    @BeforeAll
    public static void setUpLink() throws SQLException, IOException {
        RestAssured.baseURI = "https://innopolispython.onrender.com";

        employeeHelper = new EmployeeHelper();
        employeeHelperDB = new EmployeeHelperDB();
        authHelper = new AuthHelper();

        token = authHelper.getToken("admin", "admin");
    }

    @BeforeEach
    public void setUp() {

        employeeHelper = new EmployeeHelper();
        authHelper = new AuthHelper();
    }
}

