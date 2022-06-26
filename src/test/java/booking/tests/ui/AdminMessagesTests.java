package booking.tests.ui;

import booking.api.client.ApiClient;
import booking.data.TestData;
import booking.model.message.Message;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class AdminMessagesTests extends TestBase{

    private static Message message = TestData.newMessage();
    private static Message createdMessage;

    @BeforeEach
    void presteps()
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

    @Test
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

    @Test
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

    @Test
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
