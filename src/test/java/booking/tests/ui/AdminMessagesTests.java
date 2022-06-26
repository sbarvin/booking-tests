package booking.tests.ui;

import booking.allure.JiraIssue;
import booking.api.client.ApiClient;
import booking.data.TestData;
import booking.model.message.Message;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;

@Owner("barvinsk")
@Feature("Admin messages")
public class AdminMessagesTests extends TestBase{

    private static Message message = TestData.newMessage();
    private static Message createdMessage;

    @BeforeEach
    void preconditions()
    {
        apiClient.auth().loginAsDefaultUser();

        createdMessage = apiClient.message().create(message)
                .then()
                .extract().as(Message.class);

        loginPage
                .open()
                .letMeHack()
                .fillName("admin")
                .fillPass("password")
                .login();
    }

    @Tag("UI")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Open message")
    void successOpenMessage() {


        adminMessagesPage
                .open()
                .showMessage(message.getSubject())
                .checkMessageInfo(message.getName())
                .closeMessage();

        apiClient
                .message()
                .del(createdMessage.getMessageid().toString());

    }

    @Tag("UI")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Check message status")
    void successCheckMessage() {

        adminMessagesPage
                .open()
                .checkMessageStatus(message.getSubject(), false)
                .showMessage(message.getSubject())
                .closeMessage()
                .checkMessageStatus(message.getSubject(), true);

        apiClient
                .message()
                .del(createdMessage.getMessageid().toString());

    }

    @Tag("UI")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Delete message")
    void successDeleteMessage() {

        adminMessagesPage
                .open()
                .deleteMessage(createdMessage.getSubject());

        apiClient.message().getById(createdMessage.getMessageid().toString())
                .then()
                .statusCode(500);

    }

}
