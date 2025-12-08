package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BaseMobilePage {
    private static final By SEARCH_CONTAINER = By.id("org.wikipedia:id/search_container");

    public MainPage(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public SearchPage openSearch() {
        click(SEARCH_CONTAINER);
        return new SearchPage(driver, wait);
    }

    public boolean isLoaded() {
        return isDisplayed(SEARCH_CONTAINER);
    }
}

