package booking.api.controller;

import booking.api.client.ApiConfig;
import booking.model.booking.Booking;
import booking.data.TestData;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.post;

public class BookingController extends ApiController {

    public BookingController(ApiConfig config) {
        super(config);
    }

    @Step("Get all bookings")
    public Response getAll() {
        reqSpec.addCookie(TOKEN.get());

        return get("/booking");
    }

    @Step("Get booking by id")
    public Response getById(String id) {
        reqSpec.addCookie(TOKEN.get());

        return get("/booking/" + id);
    }

    @Step("Create booking")
    public Response create(Booking booking) {
        reqSpec
                .addCookie(TOKEN.get())
                .setBody(booking);

        return post("/booking/");
    }

    @Step("Create booking")
    public Response create(int roomId) {
        reqSpec
                .addCookie(TOKEN.get())
                .setBody(TestData.newBooking(roomId));

        return post("/booking/");
    }

    @Step("Update booking")
    public Response update(Booking booking) {
        reqSpec
                .addCookie(TOKEN.get())
                .setBody(booking);

        return put("/booking/" + booking.getBookingid().toString());
    }

    @Step("Delete booking")
    public Response del(String id) {
        reqSpec.addCookie(TOKEN.get());

        return delete("/booking/" + id);
    }

}
