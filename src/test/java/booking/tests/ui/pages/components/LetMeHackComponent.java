package booking.tests.ui.pages.components;

import booking.tests.ui.pages.AdminMessagesPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LetMeHackComponent {

    public LetMeHackComponent letMeHack() {
        if($("#collapseBanner") != null)
            $("#collapseBanner").$(".btn-primary").click();
        return this;
    }
}
