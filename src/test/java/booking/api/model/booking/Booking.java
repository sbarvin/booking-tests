package booking.api.model.booking;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Booking {
    private Integer bookingid;
    private Integer roomid;
    private String firstname;
    private String lastname;
    private Boolean depositpaid;
    private BookingDates bookingdates;
    private String email;
    private String phone;
}
