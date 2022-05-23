package booking.tests.api;

import booking.api.client.ApiClient;
import booking.api.model.room.Room;
import booking.api.model.room.Rooms;
import booking.data.TestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RoomApiTests {

    private final ApiClient apiClient = ApiClient.api();

    @BeforeEach
    void preconditions() {
        apiClient.auth().loginAsDefaultUser();
    }

    @AfterEach
    void postconditions() {
        apiClient.auth().logout();
    }

    @Test
    @DisplayName("Get all rooms")
    void getRoomsTest() {
        apiClient.room().create(TestData.newRoom());

        Rooms rooms = apiClient.room().getAll()
                .then()
                .statusCode(200)
                .extract().as(Rooms.class);
    }

    @Test
    @DisplayName("Get room by id")
    void getRoomTest() {

        Room newRoom = apiClient.room().create(TestData.newRoom())
                .then()
                .extract().as(Room.class);

        Room room = apiClient.room().getById(newRoom.getRoomid().toString())
                .then()
                .statusCode(200)
                .extract().as(Room.class);

        apiClient.room().del(room.getRoomid().toString())
                .then()
                .statusCode(202);
    }

    @Test
    @DisplayName("Create new room")
    void createRoomTest() {
        Room newRoom = apiClient.room().create(TestData.newRoom())
                .then()
                .extract().as(Room.class);

        apiClient.room().del(newRoom.getRoomid().toString())
                .then()
                .statusCode(202);
    }

    @Test
    @DisplayName("Update room")
    void updateRoomTest() {
        Room newRoom = apiClient.room().create(TestData.newRoom())
                .then()
                .extract().as(Room.class);

        newRoom.setRoomName(newRoom.getRoomName() + "UPD");

        Room updatedRoom = apiClient.room().update(newRoom)
                .then()
                .statusCode(202)
                .extract().as(Room.class);

        apiClient.room().del(updatedRoom.getRoomid().toString())
                .then()
                .statusCode(202);
    }

    @Test
    @DisplayName("Delete room")
    void deleteRoomTest() {
        Room newRoom = apiClient.room().create(TestData.newRoom())
                .then()
                .extract().as(Room.class);

        apiClient.room().del(newRoom.getRoomid().toString())
                .then()
                .statusCode(202);
    }
}
