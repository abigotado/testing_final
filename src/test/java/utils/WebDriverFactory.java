package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    private WebDriverFactory() {
    }

    public static WebDriver create() {
        String browser = Config.get("browser");
        if (browser == null || browser.equalsIgnoreCase("chrome")) {
            return createChrome();
        }
        throw new IllegalArgumentException("Unsupported browser: " + browser);
    }

    private static WebDriver createChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        boolean headless = Config.getBoolean("headless", false);
        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }
}

