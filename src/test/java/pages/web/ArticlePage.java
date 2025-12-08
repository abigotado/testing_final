package pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ArticlePage extends BasePage {
    private static final By TITLE = By.tagName("h1");
    private final String expectedPreviewTitle;

    public ArticlePage(WebDriver driver, WebDriverWait wait, String expectedPreviewTitle) {
        super(driver, wait);
        this.expectedPreviewTitle = expectedPreviewTitle == null ? "" : expectedPreviewTitle;
    }

    public String getTitle() {
        return waitVisible(TITLE).getText();
    }

    public String getExpectedPreviewTitle() {
        return expectedPreviewTitle;
    }
}

