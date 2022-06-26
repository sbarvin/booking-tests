package booking.tests.ui;

import booking.api.client.ApiClient;
import booking.helpers.AllureAttachments;
import booking.helpers.DriverSettings;
import booking.tests.ui.pages.AdminMessagesPage;
import booking.tests.ui.pages.FrontPage;
import booking.tests.ui.pages.LoginPage;
import booking.tests.ui.pages.components.LetMeHackComponent;
import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.RemoteWebDriver;

@ExtendWith({AllureJunit5.class})
public class TestBase {

    public static final ApiClient apiClient = ApiClient.api();

    protected FrontPage frontPage = new FrontPage();
    protected static LoginPage loginPage = new LoginPage();
    protected static AdminMessagesPage adminMessagesPage = new AdminMessagesPage();

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DriverSettings.configure();
    }

    @AfterEach
    public void addAttachments() {
        if (WebDriverRunner.driver().hasWebDriverStarted()) {
            AllureAttachments.addPageSource();
            if (Configuration.browser.equals(Browsers.CHROME))
                AllureAttachments.addBrowserConsoleLogs();
            AllureAttachments.addScreenshotAs("Last screenshot");
           // AllureAttachments.addVideo(WebDriverRunner.driver().getSessionId().toString());
        }

        WebDriverRunner.closeWebDriver();
    }
}
