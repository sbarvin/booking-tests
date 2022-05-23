package booking.tests.api;

import booking.api.client.ApiClient;
import booking.api.model.booking.Booking;
import booking.api.model.booking.Bookings;
import booking.api.model.booking.NewBooking;
import booking.api.model.room.Room;
import booking.data.TestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class BookingApiTests {

    private final ApiClient apiClient = ApiClient.api();
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

    @Test
    @DisplayName("Get all bookings")
    void getAllBookingTest() {
        NewBooking createdBooking = apiClient.booking().create(TestData.newBooking(roomId))
                .then()
                .extract().as(NewBooking.class);

        Bookings bookings = apiClient.booking().getAll()
                .then()
                .statusCode(200)
                .extract().as(Bookings.class);

        assertTrue(bookings.getBookings().size() > 0);
        assertTrue(bookings.getBookings().contains(createdBooking.getBooking()));

        apiClient.booking().del(createdBooking.getBookingid().toString())
                .then()
                .statusCode(202);
    }

    @Test
    @DisplayName("Get booking")
    void getBookingTest() {
        NewBooking createdBooking = apiClient.booking().create(TestData.newBooking(roomId))
                .then()
                .extract().as(NewBooking.class);

        Booking booking = apiClient.booking().getById(createdBooking.getBookingid().toString())
                .then()
                .statusCode(200)
                .extract().as(Booking.class);

        assertEquals(createdBooking.getBooking(), booking);

        apiClient.booking().del(booking.getBookingid().toString())
                .then()
                .statusCode(202);
    }

    @Test
    @DisplayName("Create booking")
    void createBookingTest() {

        Booking booking = TestData.newBooking(roomId);

        NewBooking createdBooking = apiClient.booking().create(booking)
                .then()
                .statusCode(201)
                .extract().as(NewBooking.class);

        assertEquals(createdBooking.getBooking().getRoomid(), booking.getRoomid());
        assertNotNull(createdBooking.getBooking().getBookingid());

        apiClient.booking().getById(createdBooking.getBookingid().toString())
                .then()
                .statusCode(200);

        apiClient.booking().del(createdBooking.getBookingid().toString())
                .then()
                .statusCode(202);
    }

    @Test
    @DisplayName("Update booking")
    void updateBookingTest() {
        NewBooking createdBooking = apiClient.booking().create(TestData.newBooking(roomId))
                .then()
                .extract().as(NewBooking.class);

        String newLastName = createdBooking.getBooking().getLastname().toUpperCase(Locale.ROOT);
        createdBooking.getBooking().setLastname(newLastName);

        NewBooking updatedBooking = apiClient.booking().update(createdBooking.getBooking())
                .then()
                .statusCode(200)
                .extract().as(NewBooking.class);

        assertEquals(updatedBooking.getBooking(), createdBooking.getBooking());

        apiClient.booking().del(updatedBooking.getBookingid().toString());
    }

    @Test
    @DisplayName("Delete booking")
    void deleteBookingTest() {
        NewBooking createdBooking = apiClient.booking().create(TestData.newBooking(roomId))
                .then()
                .extract().as(NewBooking.class);

        apiClient.booking().del(createdBooking.getBookingid().toString())
                .then()
                .statusCode(202);

        apiClient.booking().getById(createdBooking.getBookingid().toString())
                .then()
                .statusCode(404);
    }

}
