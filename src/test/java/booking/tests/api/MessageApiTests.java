package booking.tests.api;

import booking.api.client.ApiClient;
import booking.data.TestData;
import booking.model.message.Count;
import booking.model.message.Message;
import booking.model.message.Messages;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageApiTests {

    public final ApiClient apiClient = ApiClient.api();

    @BeforeEach
    void preconditions() {
        apiClient.auth().loginAsDefaultUser();
    }

    @AfterEach
    void postconditions() {
        apiClient.auth().logout();
    }

    @Test
    @DisplayName("Get all message")
    void successGetAllMessageTest() {
        Message createdMessage = apiClient.message().create(TestData.newMessage())
                .then()
                .extract().as(Message.class);

        Messages messages = apiClient.message().getAll()
                .then()
                .statusCode(200)
                .extract().as(Messages.class);


        assertTrue(messages.getMessages().size() > 0);

        apiClient.message().del(createdMessage.getMessageid().toString());
    }

    @Test
    @DisplayName("Get message")
    void successGetMessageTest() {

        Message createdMessage = apiClient.message().create(TestData.newMessage())
                .then()
                .extract().as(Message.class);

        Message message = apiClient.message().getById(createdMessage.getMessageid().toString())
                .then()
                .statusCode(200)
                .extract().as(Message.class);

        assertEquals(createdMessage, message);

        apiClient.message().del(createdMessage.getMessageid().toString());

    }

    @Test
    @DisplayName("Create message")
    void successCreateMessageTest() {

        Message message = TestData.newMessage();

        Message createdMessage = apiClient.message().create(message)
                .then()
                .statusCode(201)
                .extract().as(Message.class);

        assertEquals(createdMessage.getSubject(), message.getSubject());
        assertNotNull(createdMessage.getMessageid());

        apiClient.message().getById(createdMessage.getMessageid().toString())
                .then()
                .statusCode(200);

        apiClient.message().del(createdMessage.getMessageid().toString());

    }


    @Test
    @DisplayName("Read message")
    void successReadMessageTest() {

        Message createdMessage = apiClient.message().create(TestData.newMessage())
                .then()
                .extract().as(Message.class);

        Integer unreadbleCount = apiClient.message().count()
                .then()
                .extract().as(Count.class).getCount();

        apiClient.message().read(createdMessage)
                .then()
                .statusCode(202);

        Integer newUnreadbleCount = apiClient.message().count()
                .then()
                .extract().as(Count.class).getCount();

        assertNotEquals(unreadbleCount, newUnreadbleCount);
        assertEquals(unreadbleCount - 1, newUnreadbleCount);

        apiClient.message().del(createdMessage.getMessageid().toString());

    }

    @Test
    @DisplayName("Get message unreadble count")
    void successGetMessageUnreadbleCountTest() {

        Integer unreadbleCount = apiClient.message().count()
                .then()
                .extract().as(Count.class).getCount();

        Message createdMessage = apiClient.message().create(TestData.newMessage())
                .then()
                .extract().as(Message.class);

        Integer newUnreadbleCount = apiClient.message().count()
                .then()
                .extract().as(Count.class).getCount();

        assertNotEquals(unreadbleCount, newUnreadbleCount);
        assertEquals(unreadbleCount + 1, newUnreadbleCount);

        apiClient.message().del(createdMessage.getMessageid().toString());

        Integer lastUnreadbleCount = apiClient.message().count()
                .then()
                .extract().as(Count.class).getCount();

        assertEquals(unreadbleCount, lastUnreadbleCount);

    }

    @Test
    @DisplayName("Delete message")
    void successDeleteMessageTest() {

        Message createdMessage = apiClient.message().create(TestData.newMessage())
                .then()
                .extract().as(Message.class);

        apiClient.message().del(createdMessage.getMessageid().toString())
                .then()
                .statusCode(202);

        apiClient.message().getById(createdMessage.getMessageid().toString())
                .then()
                .statusCode(500);

    }

}
