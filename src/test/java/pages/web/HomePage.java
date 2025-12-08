package pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;

public class HomePage extends BasePage {
    private static final By FOOTBALL_LINK = By.cssSelector("a[href='/football/']");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public HomePage open() {
        driver.get(Config.get("baseUrl"));
        return this;
    }

    public FootballPage goToFootball() {
        try {
            waitClickable(FOOTBALL_LINK).click();
        } catch (TimeoutException e) {
            driver.navigate().to(Config.get("baseUrl") + "/football/");
        }
        return new FootballPage(driver, wait);
    }
}

