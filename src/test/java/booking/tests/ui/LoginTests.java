package booking.tests.ui;

import booking.annotations.JiraIssue;
import booking.annotations.Layer;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Layer("web")
@Owner("barvinsk")
@Feature("Login")
public class LoginTests extends TestBase {

    @Tag("UI")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Login with valid credentials")
    void successLoginTest() {
        loginPage
                .open()
                .letMeHack()
                .checkHeader()
                .fillName("admin")
                .fillPass("password")
                .login();

    }

    @Tag("UI")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Login with invalid credentials")
    void wrongLoginTest() {
        loginPage
                .open()
                .letMeHack()
                .fillName("admin1")
                .fillPass("password1")
                .login();
    }
}
