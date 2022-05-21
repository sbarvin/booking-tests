package booking.api.model.booking;

import lombok.Data;

import java.util.List;

@Data
public class Bookings {
    private List<Booking> bookings;
}