package booking.tests.ui.pages.components.front;

import booking.tests.ui.pages.components.BookingCalendarComponent;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BookingFormComponent {

    private final BookingCalendarComponent bookingCalendarComponent = new BookingCalendarComponent();

    @Step("Fill first name")
    public BookingFormComponent fillFirstName(String val) {
        $(".room-firstname").setValue(val);
        return this;
    }

    @Step("Fill last name")
    public BookingFormComponent fillLastName(String val) {
        $(".room-lastname").setValue(val);
        return this;
    }

    @Step("Fill email")
    public BookingFormComponent fillEmail(String val) {
        $(".room-email").setValue(val);
        return this;
    }

    @Step("Fill phone")
    public BookingFormComponent fillPhone(String val) {
        $(".room-phone").setValue(val);
        return this;
    }

    @Step("Get booking calendar")
    public BookingCalendarComponent getBookingCalendar() {
        return bookingCalendarComponent;
    }

    @Step("Book")
    public BookingFormComponent book() {
        $(".room-firstname").parent().parent().$(".btn-outline-primary").click();
        return this;
    }

    @Step("Cancel")
    public BookingFormComponent cancel() {
        $(".room-firstname").parent().parent().$(".btn-outline-danger").click();
        return this;
    }

}
