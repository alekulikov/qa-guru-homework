package guru.qa;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GitHubEnterpriseTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        WebDriverManager.chromedriver().setup();
    }

    @Test
    void enterprisePageOpenTest() throws InterruptedException {
        open("/");
        $(byTagAndText("button", "Solutions")).hover();
        $(".HeaderMenu").$(byTagAndText("a", "Enterprise")).click();
        $(byId("hero-section-brand-heading")).shouldHave(text("The AI-powered developer platform."));
    }
}
