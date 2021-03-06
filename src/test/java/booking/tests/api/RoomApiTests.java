package booking.tests.api;

import booking.annotations.JiraIssue;
import booking.annotations.Layer;
import booking.api.client.ApiClient;
import booking.model.room.Room;
import booking.model.room.Rooms;
import booking.data.TestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Layer("rest")
@Owner("barvinsk")
@Feature("Room")
class RoomApiTests {

    private final ApiClient apiClient = ApiClient.api();

    @BeforeEach
    void preconditions() {
        apiClient.auth().loginAsDefaultUser();
    }

    @AfterEach
    void postconditions() {
        apiClient.auth().logout();
    }

    @Tag("API")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Get all room")
    void successGetAllRoomTest() {

        Room createdRoom = apiClient.room().create(TestData.newRoom())
                .then()
                .extract().as(Room.class);

        Rooms rooms = apiClient.room().getAll()
                .then()
                .statusCode(200)
                .extract().as(Rooms.class);

        assertTrue(rooms.getRooms().size() > 0);

        apiClient.room().del(createdRoom.getRoomid().toString());

    }

    @Tag("API")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Get room")
    void successGetRoomTest() {

        Room createdRoom = apiClient.room().create(TestData.newRoom())
                .then()
                .extract().as(Room.class);

        Room room = apiClient.room().getById(createdRoom.getRoomid().toString())
                .then()
                .statusCode(200)
                .extract().as(Room.class);

        assertEquals(createdRoom, room);

        apiClient.room().del(createdRoom.getRoomid().toString());

    }

    @Tag("API")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Create room")
    void successCreateRoomTest() {

        Room room = TestData.newRoom();

        Room createdRoom = apiClient.room().create(room)
                .then()
                .statusCode(201)
                .extract().as(Room.class);

        assertEquals(createdRoom.getRoomName(), room.getRoomName());
        assertNotNull(createdRoom.getRoomid());

        apiClient.room().getById(createdRoom.getRoomid().toString())
                .then()
                .statusCode(200);

        apiClient.room().del(createdRoom.getRoomid().toString());

    }


    @Tag("API")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Update room")
    void successUpdateRoomTest() {

        Room createdRoom = apiClient.room().create(TestData.newRoom())
                .then()
                .extract().as(Room.class);

        String newRoomName = createdRoom.getRoomName() + "UPD";
        createdRoom.setRoomName(newRoomName);

        Room updatedRoom = apiClient.room().update(createdRoom)
                .then()
                .statusCode(202)
                .extract().as(Room.class);

        assertEquals(updatedRoom.getRoomName(), newRoomName);
        assertEquals(updatedRoom, createdRoom);

        apiClient.room().del(updatedRoom.getRoomid().toString());

    }

    @Tag("API")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Delete room")
    void successDeleteRoomTest() {

        Room createdRoom = apiClient.room().create(TestData.newRoom())
                .then()
                .extract().as(Room.class);

        apiClient.room().del(createdRoom.getRoomid().toString())
                .then()
                .statusCode(202);

        apiClient.room().getById(createdRoom.getRoomid().toString())
                .then()
                .statusCode(500);

    }
}
