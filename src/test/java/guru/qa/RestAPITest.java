package guru.qa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class RestAPITest {

    @DisplayName("Проверка фамилии пользователя с 5 id")
    @Test
    void successGetUser() {

        given()
                .when()
                .get("https://reqres.in/api/users/5")
                .then()
                .body("data.last_name", is("Morris"));
    }

    @DisplayName("Проверка о ненахождении сайта")
    @Test
    void checkSingleUser404Error() {
        given()
                .when()
                .get("https://reqres.in/api/users/404")
                .then()
                .statusCode(404);
    }

    @DisplayName("Создание новго пользователя и проверка его имени")
    @Test
    void createUserTest() {
        File newUser = new File("src/test/resources/JSON/newUser.json");
        given()
                .contentType(JSON)
                .body(newUser)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("Denis"));
    }

    @DisplayName("Удаление пользователя")
    @Test
    void deleteUserTest() {
        given()
                .when()
                .delete("https://reqres.in/api/register")
                .then()
                .statusCode(204);
    }

    @DisplayName("Тест на регистрацию")
    @Test
    void registryTest() {

        File toRegistry = new File("src/test/resources/JSON/registry.json");

        given()
                .contentType(JSON)
                .body(toRegistry)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }
}
