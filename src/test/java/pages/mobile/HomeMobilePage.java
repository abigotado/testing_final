package pages.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeMobilePage extends BaseMobilePage {
    public HomeMobilePage(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public SearchMobilePage tapSearch() {
        By primary = By.id("org.wikipedia:id/search_container");
        By pkgPrimary = id(":id/search_container");
        By fallbackAlpha = idFallback(":id/search_container");
        By accessibility = AppiumBy.accessibilityId("Search Wikipedia");

        // дождёмся хедера фида, если он есть
        By feedHeader = By.id("org.wikipedia:id/view_feed_header");
        driver.findElements(feedHeader); // не блокирует, лишь проверка

        waitAnyClickable(LONG, primary, pkgPrimary, fallbackAlpha, accessibility).click();
        return new SearchMobilePage(driver, wait);
    }
}
