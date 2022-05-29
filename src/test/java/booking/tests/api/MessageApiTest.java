package booking.tests.api;

import booking.api.client.ApiClient;
import booking.data.TestData;
import booking.model.message.Message;
import booking.model.message.Messages;
import booking.model.room.Room;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MessageApiTest {

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
    void getAll() {
        Message message = apiClient.message().create(TestData.newMessage())
                .then()
                .extract().as(Message.class);

        Messages messages = apiClient.message().getAll()
                .then()
                .statusCode(200)
                .extract().as(Messages.class);
    }

}
