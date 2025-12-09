package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchMobilePage extends BaseMobilePage {

    public SearchMobilePage(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public SearchMobilePage typeQuery(String query) {
        WebElement input = waitVisible(id(":id/search_src_text"));
        input.clear();
        input.sendKeys(query);
        return this;
    }

    public List<WebElement> getResults() {
        return driver.findElements(id(":id/page_list_item_title"));
    }

    public ArticleMobilePage openFirstResult() {
        WebElement first = waitVisible(id(":id/page_list_item_title"));
        String title = first.getText();
        first.click();
        return new ArticleMobilePage(driver, wait, title);
    }
}

