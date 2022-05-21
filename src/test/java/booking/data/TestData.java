package booking.data;

import booking.api.model.auth.User;
import booking.api.model.booking.Booking;
import booking.config.App;

public class TestData {

    private TestData() {
    }

    public static User defaultUser() {
        User authLoginRequest = new User();
        authLoginRequest.setUsername(App.config.defaultLogin());
        authLoginRequest.setPassword(App.config.defaultPassword());

        return authLoginRequest;
    }

    public static Booking newBooking() {
        Booking booking = new Booking();


        return booking;
    }
}
