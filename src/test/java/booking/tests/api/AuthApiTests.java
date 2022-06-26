package booking.tests.api;

import booking.annotations.JiraIssue;
import booking.annotations.Layer;
import booking.api.client.ApiClient;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.http.Cookie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Layer("rest")
@Owner("barvinsk")
@Feature("Auth")
public class AuthApiTests {

    public final ApiClient apiClient = ApiClient.api();

    @Tag("API")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Login")
    void successLoginTest() {
        Cookie authToken = apiClient.auth().loginAsDefaultUser()
                .then()
                .statusCode(200)
                .extract().detailedCookie("token");

        assertNotNull(authToken);

        apiClient.booking().getAll()
                .then()
                .statusCode(200);

        apiClient.auth().logout();
    }

    @Tag("API")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Logout")
    void successLogoutTest() {
        apiClient.auth().loginAsDefaultUser();

        apiClient.auth().logout()
                .then()
                .statusCode(200);

        apiClient.booking().getAll()
                .then()
                .statusCode(403);
    }

    @Tag("API")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Validate token")
    void successValidateTokenTest() {
        apiClient.auth().loginAsDefaultUser();

        apiClient.auth().validate()
                .then()
                .statusCode(200);

        apiClient.auth().logout();

        apiClient.auth().validate()
                .then()
                .statusCode(403);
    }
}
