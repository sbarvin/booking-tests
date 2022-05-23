package booking.tests.api;

import booking.api.client.ApiClient;
import booking.api.model.booking.Booking;
import booking.api.model.booking.Bookings;
import booking.api.model.booking.NewBooking;
import booking.data.TestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookingApiTest {

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
    void getBookingsTest() {
        Bookings bookingsResponse = apiClient.booking().getAll()
                .then()
                .statusCode(200)
                .extract().as(Bookings.class);
    }

    @Test
    void getBookingTest() {
        Booking bookingRequest = TestData.newBooking(1);

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
    void createBookingTest() {
        Booking bookingRequest = TestData.newBooking(1);

        NewBooking bookingResponse = apiClient.booking().create(bookingRequest)
                .then()
                .statusCode(201)
                .extract().as(NewBooking.class);

        apiClient.booking().del(bookingResponse.getBookingid().toString())
                .then()
                .statusCode(202);
    }

    @Test
    void updateBookingTest() {
        Booking bookingRequest = TestData.newBooking(1);

        NewBooking bookingResponse = apiClient.booking().create(bookingRequest)
                .then()
                .extract().as(NewBooking.class);

        apiClient.booking().del(bookingResponse.getBookingid().toString());
    }

    @Test
    void deleteBookingTest() {
        Booking bookingRequest = TestData.newBooking(1);

        NewBooking newBooking = apiClient.booking().create(bookingRequest)
                .then()
                .extract().as(NewBooking.class);

        apiClient.booking().del(newBooking.getBookingid().toString())
                .then()
                .statusCode(202);

        apiClient.booking().getById(newBooking.getBookingid().toString())
                .then()
                .statusCode(400);
    }

}
