package pages.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ArticlePageMobile extends BaseMobilePage {
    private static final By TITLE_PRIMARY = By.id("org.wikipedia:id/view_page_title_text");
    private static final By TITLE_PKG = By.id("org.wikipedia.alpha:id/view_page_title_text");
    private final String previewTitle;

    public ArticlePageMobile(AndroidDriver driver, WebDriverWait wait, String previewTitle) {
        super(driver, wait);
        this.previewTitle = previewTitle == null ? "" : previewTitle;
    }

    public String getTitle() {
        return waitAnyVisible(LONG, TITLE_PRIMARY, TITLE_PKG).getText();
    }

    public String getPreviewTitle() {
        return previewTitle;
    }

    public boolean scrollToText(String text) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(\"" + text + "\")"));
        return !driver.findElements(AppiumBy.androidUIAutomator(
                "new UiSelector().textContains(\"" + text + "\")")).isEmpty();
    }

    public boolean scrollToHistory() {
        return scrollToText("History") || scrollToText("История");
    }
}
