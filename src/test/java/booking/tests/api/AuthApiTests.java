package booking.tests.api;

import booking.model.auth.User;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthApiTests {
    @BeforeAll
    static void prediction() {
        baseURI = "https://automationintesting.online";
    }

    @Tag("api")
    @Test
    @DisplayName("Аутентификация. Получение токена")
    void authTest() {

        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");

        Cookies cookies = given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(user)
                .log().all()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response()
                .getDetailedCookies();

        given()
                .filter(new AllureRestAssured())
                .cookie(cookies.get("token"))
                .log().all()
                .get("/booking")
                .then()
                .statusCode(200)
                .log().all();

    }
}
