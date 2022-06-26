package booking.tests.ui.pages;

import booking.tests.ui.pages.components.LetMeHackComponent;
import booking.tests.ui.pages.components.ReactModalPortalComponent;
import booking.tests.ui.pages.components.admin.NavBarComponent;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class AdminMessagesPage {
    public NavBarComponent navBarComponent = new NavBarComponent();
    private ReactModalPortalComponent reactModalPortalComponent = new ReactModalPortalComponent();
    protected static LetMeHackComponent letMeHackComponent = new LetMeHackComponent();

    @Step("Open page")
    public AdminMessagesPage open() {
        Selenide.open("/#/admin");
        navBarComponent.goToMessages();
        return this;
    }

    @Step("Let me hack!")
    public AdminMessagesPage letMeHack() {
        letMeHackComponent.letMeHack();
        return this;
    }

    @Step("Show message")
    public AdminMessagesPage showMessage(String subject) {
        $(".messages").$$(".detail").findBy(text(subject)).click();
        return this;
    }

    @Step("Check message info")
    public AdminMessagesPage checkMessageInfo(String val) {
        reactModalPortalComponent.checkInfo(val);
        return this;
    }

    @Step("Close message")
    public AdminMessagesPage closeMessage() {
        reactModalPortalComponent.close();
        return this;
    }

    @Step("Check message status")
    public AdminMessagesPage checkMessageStatus(String subject, boolean read) {
        $(".messages").$$(".read-" + read).find(text(subject));
        return this;
    }

    @Step("Delete message")
    public AdminMessagesPage deleteMessage(String subject) {
        $(".messages").$$(".row").find(text(subject)).$(".roomDelete").click();

        return this;
    }

}
