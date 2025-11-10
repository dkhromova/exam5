package exam.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class BaseUI {

    private static final String baseUrl = "https://www.saucedemo.com/";

    @BeforeEach
    public void setUp() {

        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(true));


        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 10000;
        Configuration.browserSize = "1280x1024";
        Configuration.browserPosition = "100x0";

        // Для запуска тестов в GitHub Actions
        if (System.getenv("CI") != null) {
            Configuration.headless = true;
            Configuration.browserCapabilities.setCapability("goog:chromeOptions",
                    new org.openqa.selenium.chrome.ChromeOptions()
                            .addArguments("--no-sandbox")
                            .addArguments("--disable-dev-shm-usage")
                            .addArguments("--disable-gpu")
                            .addArguments("--window-size=1920,1080")
                            .addArguments("--headless=new")
            );
        }
        open(baseUrl);

    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }


}
