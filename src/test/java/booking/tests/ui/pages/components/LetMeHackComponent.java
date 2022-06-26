package booking.tests.ui.pages.components;

import booking.tests.ui.pages.AdminMessagesPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LetMeHackComponent {

    public LetMeHackComponent letMeHack() {
        var banner = $("#collapseBanner");

        if(banner != null)
            banner.$(".btn-primary").click();

        return this;
    }
}
