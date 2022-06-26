package booking.tests.ui.pages.components;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ReactModalPortalComponent {

    @Step("Check information in modal")
    public ReactModalPortalComponent checkInfo(String val) {
        $(".ReactModalPortal").shouldHave(text(val));
        return this;
    }

    @Step("Close modal")
    public ReactModalPortalComponent close() {
        $(".ReactModalPortal").$(".btn-outline-primary").click();
        return this;
    }

}
