package pages.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeMobilePage extends BaseMobilePage {
    private static final AppiumBy SEARCH_CONTAINER = (AppiumBy) AppiumBy.id("org.wikipedia:id/search_container");

    public HomeMobilePage(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public SearchMobilePage tapSearch() {
        waitVisible(SEARCH_CONTAINER).click();
        return new SearchMobilePage(driver, wait);
    }
}

