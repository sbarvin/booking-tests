package booking.tests.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {

    @Test
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

    @Test
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
