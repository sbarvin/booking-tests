package booking.tests.ui.pages;

import booking.tests.ui.pages.components.LetMeHackComponent;
import booking.tests.ui.pages.components.ReactModalPortalComponent;
import booking.tests.ui.pages.components.front.BookingFormComponent;
import booking.tests.ui.pages.components.front.MessageFormComponent;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FrontPage {

    private final BookingFormComponent bookingFormComponent = new BookingFormComponent();
    private final MessageFormComponent messageFormComponent = new MessageFormComponent();
    private final ReactModalPortalComponent reactModalPortalComponent = new ReactModalPortalComponent();
    private final LetMeHackComponent letMeHackComponent = new LetMeHackComponent();

    @Step("Open home page")
    public FrontPage open() {
        Selenide.open("/");
        return this;
    }

    @Step("Let me hack!")
    public FrontPage letMeHack() {
        letMeHackComponent.letMeHack();
        return this;
    }

    @Step("Go to admin panel login page")
    public FrontPage goToAdminPanelLogin() {
        $("#footer").shouldHave(text("Admin panel")).click();
        return this;
    }

    @Step("Show booking form")
    public FrontPage showBooking(String desc) {
        $("#root").findAll(".openBooking").last().click();
        return this;
    }

    @Step("Fill booking form")
    public FrontPage fillBooking(String firstName, String lastName, String email,
                                 String phone, String from, String to) {

        bookingFormComponent
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillEmail(email)
                .fillPhone(phone)
                .getBookingCalendar().nextMonth().setDates(from, to);


        return this;
    }

    @Step("Send booking form")
    public FrontPage sendBooking() {
        bookingFormComponent.book();
        return this;
    }

    @Step("Cancel booking")
    public FrontPage cancelBooking() {
        bookingFormComponent.cancel();
        return this;
    }

    @Step("Check result after booking")
    public FrontPage checkBookingResult(String val) {
        reactModalPortalComponent.checkInfo(val);
        return this;
    }

    @Step("Fill message form")
    public FrontPage fillMessage(String name, String email,
                                 String phone, String subject, String description) {

        messageFormComponent
                .fillName(name)
                .fillEmail(email)
                .fillPhone(phone)
                .fillSubject(subject)
                .fillDescription(description);

        return this;
    }

    @Step("Send message form")
    public FrontPage sendMessage() {
        messageFormComponent.send();
        return this;
    }

    @Step("Check result after sending message")
    public FrontPage checkMessageResult(String val) {
        messageFormComponent.checkResult(val);
        return this;
    }
}
