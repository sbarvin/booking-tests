package booking.tests.api;

import booking.api.client.ApiClient;
import io.restassured.http.Cookie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthApiTests extends ApiTestBase{

    @Test
    @DisplayName("Login")
    void loginTest() {
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

    @Test
    @DisplayName("Logout")
    void logoutTest() {
        apiClient.auth().loginAsDefaultUser();

        apiClient.auth().logout()
                .then()
                .statusCode(200);

        apiClient.booking().getAll()
                .then()
                .statusCode(403);
    }

    @Test
    @DisplayName("Validate token")
    void validateTokenTest() {
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
