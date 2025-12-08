package pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public SearchPage openWithQuery(String query) {
        String encoded;
        try {
            encoded = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            encoded = query;
        }
        driver.get(Config.get("baseUrl") + "/search/?q=" + encoded);
        return this;
    }

    public boolean hasResultContaining(String text) {
        String lower = text.toLowerCase();
        return wait.until(d -> {
            List<WebElement> links = d.findElements(By.cssSelector("main a[href]"));
            return links.stream().anyMatch(el -> el.getText() != null && el.getText().toLowerCase().contains(lower));
        });
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }
}

