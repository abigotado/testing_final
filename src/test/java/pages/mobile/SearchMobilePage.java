package pages.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchMobilePage extends BaseMobilePage {
    private static final AppiumBy SEARCH_INPUT = (AppiumBy) AppiumBy.id("org.wikipedia:id/search_src_text");
    private static final AppiumBy RESULT_TITLE = (AppiumBy) AppiumBy.id("org.wikipedia:id/page_list_item_title");

    public SearchMobilePage(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public SearchMobilePage typeQuery(String query) {
        WebElement input = waitVisible(SEARCH_INPUT);
        input.clear();
        input.sendKeys(query);
        return this;
    }

    public List<WebElement> getResults() {
        return driver.findElements(RESULT_TITLE);
    }

    public ArticleMobilePage openFirstResult() {
        WebElement first = waitVisible(RESULT_TITLE);
        String title = first.getText();
        first.click();
        return new ArticleMobilePage(driver, wait, title);
    }
}

