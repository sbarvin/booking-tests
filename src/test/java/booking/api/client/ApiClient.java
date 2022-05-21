package booking.api.client;

import booking.api.controller.AuthController;
import booking.api.controller.BookingController;

public class ApiClient {

    private ApiClient() {
    }

    public static ApiClient api() {
        return new ApiClient();
    }

    public AuthController auth() {
        return new AuthController(ApiConfig.config().build());
    }

    public BookingController booking() {
        return new BookingController(ApiConfig.config().build());
    }

}
