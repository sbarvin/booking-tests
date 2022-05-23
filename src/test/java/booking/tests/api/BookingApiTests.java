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

public class BookingApiTests {

    private final ApiClient apiClient = ApiClient.api();
    private Integer roomId = 1;


    @BeforeEach
    void preconditions() {
        apiClient.auth().loginAsDefaultUser();
        roomId = apiClient.room().create(TestData.newRoom())
                .then()
                .extract().as(Room.class).getRoomId();
    }

    @AfterEach
    void postconditions() {
        apiClient.room().del(roomId.toString());
        apiClient.auth().logout();
    }

    @Test
    @DisplayName("Get all bookings")
    void getAllBookingTest() {
        Bookings bookingsResponse = apiClient.booking().getAll()
                .then()
                .statusCode(200)
                .extract().as(Bookings.class);
    }

    @Test
    @DisplayName("Get booking by id")
    void getBookingTest() {
        Booking bookingRequest = TestData.newBooking(roomId);

        NewBooking newBooking = apiClient.booking().create(bookingRequest)
                .then()
                .extract().as(NewBooking.class);

        Booking bookingResponse = apiClient.booking().getById(
                        newBooking.getBookingid().toString()
                )
                .then()
                .statusCode(200)
                .extract().as(Booking.class);
    }

    @Test
    @DisplayName("Create new booking")
    void createBookingTest() {
        NewBooking newBooking = apiClient.booking().create(TestData.newBooking(roomId))
                .then()
                .statusCode(201)
                .extract().as(NewBooking.class);

        apiClient.booking().del(newBooking.getBookingid().toString())
                .then()
                .statusCode(202);
    }

    @Test
    @DisplayName("Update booking")
    void updateBookingTest() {
        Booking newBooking = TestData.newBooking(roomId);

        NewBooking bookingResponse = apiClient.booking().create(newBooking)
                .then()
                .extract().as(NewBooking.class);

        apiClient.booking().del(bookingResponse.getBookingid().toString());
    }

    @Test
    @DisplayName("Delete booking")
    void deleteBookingTest() {
        Booking bookingRequest = TestData.newBooking(roomId);

        NewBooking newBooking = apiClient.booking().create(bookingRequest)
                .then()
                .extract().as(NewBooking.class);

        apiClient.booking().del(newBooking.getBookingid().toString())
                .then()
                .statusCode(202);

        apiClient.booking().getById(newBooking.getBookingid().toString())
                .then()
                .statusCode(404);
    }

}
