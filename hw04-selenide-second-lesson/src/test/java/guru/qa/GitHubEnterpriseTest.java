package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class GitHubEnterpriseTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void enterprisePageOpenTest() {
        open("/");
        $(byTagAndText("button", "Solutions")).hover();
        $(".HeaderMenu").$(byTagAndText("a", "Enterprises")).click();
        $(byId("hero-section-brand-heading")).shouldHave(text("The AI-powered developer platform."));
    }
}