package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeMobilePage extends BaseMobilePage {
    public HomeMobilePage(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public SearchMobilePage tapSearch() {
        waitVisible(id(":id/search_container")).click();
        return new SearchMobilePage(driver, wait);
    }
}

