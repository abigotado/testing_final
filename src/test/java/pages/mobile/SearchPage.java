package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage extends BaseMobilePage {
    private static final By SEARCH_INPUT = By.id("org.wikipedia:id/search_src_text");
    private static final By RESULT_TITLES = By.id("org.wikipedia:id/page_list_item_title");

    public SearchPage(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public SearchPage typeQuery(String query) {
        type(SEARCH_INPUT, query);
        return this;
    }

    public boolean hasResults() {
        return wait.until(d -> !d.findElements(RESULT_TITLES).isEmpty());
    }

    public String getFirstResultTitle() {
        WebElement first = wait.until(d -> {
            List<WebElement> results = d.findElements(RESULT_TITLES);
            return results.isEmpty() ? null : results.get(0);
        });
        return first.getText();
    }

    public ArticlePage openFirstResult() {
        WebElement first = wait.until(d -> {
            List<WebElement> results = d.findElements(RESULT_TITLES);
            return results.isEmpty() ? null : results.get(0);
        });
        String title = first.getText();
        first.click();
        return new ArticlePage(driver, wait, title);
    }
}

