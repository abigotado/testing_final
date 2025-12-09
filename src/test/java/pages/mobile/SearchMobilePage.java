package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchMobilePage extends BaseMobilePage {

    public SearchMobilePage(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public SearchMobilePage typeQuery(String query) {
        By primaryInput = By.id("org.wikipedia:id/search_src_text");
        By pkgInput = id(":id/search_src_text");
        By fallbackInput = idFallback(":id/search_src_text");
        WebElement input = waitAnyVisible(LONG, primaryInput, pkgInput, fallbackInput);
        input.clear();
        input.sendKeys(query);
        return this;
    }

    public List<WebElement> getResults() {
        return driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
    }

    public ArticleMobilePage openFirstResult() {
        By primaryTitle = By.id("org.wikipedia:id/page_list_item_title");
        By pkgTitle = id(":id/page_list_item_title");
        By fallbackTitle = idFallback(":id/page_list_item_title");
        WebElement first = waitAnyVisible(LONG, primaryTitle, pkgTitle, fallbackTitle);
        String title = first.getText();
        first.click();
        return new ArticleMobilePage(driver, wait, title);
    }
}
