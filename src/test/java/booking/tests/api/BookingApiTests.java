package booking.tests.api;

import booking.allure.JiraIssue;
import booking.api.client.ApiClient;
import booking.model.booking.Booking;
import booking.model.booking.Bookings;
import booking.model.booking.CreatedBooking;
import booking.model.room.Room;
import booking.data.TestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@Owner("barvinsk")
@Feature("Booking")
public class BookingApiTests {

    public final ApiClient apiClient = ApiClient.api();
    private Integer roomId;

    @BeforeEach
    void preconditions() {
        apiClient.auth().loginAsDefaultUser();
        roomId = apiClient.room().create(TestData.newRoom())
                .then()
                .extract().as(Room.class).getRoomid();
    }

    @AfterEach
    void postconditions() {
        apiClient.room().del(roomId.toString());
        apiClient.auth().logout();
    }

    @Tag("API")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Get all booking")
    void successGetAllBookingTest() {
        CreatedBooking createdBooking = apiClient.booking().create(TestData.newBooking(roomId))
                .then()
                .extract().as(CreatedBooking.class);

        Bookings bookings = apiClient.booking().getAll()
                .then()
                .statusCode(200)
                .extract().as(Bookings.class);

        assertTrue(bookings.getBookings().size() > 0);
        assertTrue(bookings.getBookings().contains(createdBooking.getBooking()));

        apiClient.booking().del(createdBooking.getBookingid().toString());
    }

    @Tag("API")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Get booking")
    void successGetBookingTest() {
        CreatedBooking createdBooking = apiClient.booking().create(TestData.newBooking(roomId))
                .then()
                .extract().as(CreatedBooking.class);

        Booking booking = apiClient.booking().getById(createdBooking.getBookingid().toString())
                .then()
                .statusCode(200)
                .extract().as(Booking.class);

        assertEquals(createdBooking.getBooking(), booking);

        apiClient.booking().del(booking.getBookingid().toString());
    }

    @Tag("API")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Create booking")
    void successCreateBookingTest() {

        Booking booking = TestData.newBooking(roomId);

        CreatedBooking createdBooking = apiClient.booking().create(booking)
                .then()
                .statusCode(201)
                .extract().as(CreatedBooking.class);

        assertEquals(createdBooking.getBooking().getRoomid(), booking.getRoomid());
        assertNotNull(createdBooking.getBooking().getBookingid());

        apiClient.booking().getById(createdBooking.getBookingid().toString())
                .then()
                .statusCode(200);

        apiClient.booking().del(createdBooking.getBookingid().toString());
    }

    @Tag("API")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Update booking")
    void successUpdateBookingTest() {
        CreatedBooking createdBooking = apiClient.booking().create(TestData.newBooking(roomId))
                .then()
                .extract().as(CreatedBooking.class);

        String newLastName = createdBooking.getBooking().getLastname().toUpperCase(Locale.ROOT);
        createdBooking.getBooking().setLastname(newLastName);

        CreatedBooking updatedBooking = apiClient.booking().update(createdBooking.getBooking())
                .then()
                .statusCode(200)
                .extract().as(CreatedBooking.class);

        assertEquals(updatedBooking.getBooking(), createdBooking.getBooking());

        apiClient.booking().del(updatedBooking.getBookingid().toString());
    }

    @Tag("API")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Delete booking")
    void successDeleteBookingTest() {
        CreatedBooking createdBooking = apiClient.booking().create(TestData.newBooking(roomId))
                .then()
                .extract().as(CreatedBooking.class);

        apiClient.booking().del(createdBooking.getBookingid().toString())
                .then()
                .statusCode(202);

        apiClient.booking().getById(createdBooking.getBookingid().toString())
                .then()
                .statusCode(404);
    }

}
