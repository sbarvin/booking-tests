package booking.tests.api;

import booking.api.client.ApiClient;
import booking.api.model.booking.Booking;
import booking.api.model.booking.BookingDates;
import booking.api.model.booking.Bookings;
import booking.api.model.booking.NewBooking;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class BookingApiTest {

    private final ApiClient apiClient = ApiClient.api();

    @BeforeEach
    void predictions() {
        apiClient.auth().loginAsDefaultUser();
    }

    @AfterEach
    void afterdictions() {
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
        Bookings bookings = apiClient.booking().getAll()
                .then()
                .statusCode(200)
                .extract().as(Bookings.class);

        Booking bookingResponse = apiClient.booking().getById(
                        bookings.getBookings().get(0).getBookingid().toString()
                )
                .then()
                .statusCode(200)
                .extract().as(Booking.class);
    }

    @Test
    void createBookingTest() {
        Booking bookingRequest = new Booking();
        bookingRequest.setFirstname("Sergey");
        bookingRequest.setLastname("Barvin");
        bookingRequest.setDepositpaid(true);
        bookingRequest.setPhone("12324324234324324");
        bookingRequest.setEmail("933@bsk.ru");
        bookingRequest.setRoomid(1);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2018-01-01");
        bookingDates.setCheckout("2019-01-01");

        bookingRequest.setBookingdates(bookingDates);

        NewBooking bookingResponse = apiClient.booking().create(bookingRequest)
                .then()
                .statusCode(201)
                .extract().as(NewBooking.class);

        apiClient.booking().del(bookingResponse.getBookingid().toString())
                .then()
                .statusCode(200);

    }

}
