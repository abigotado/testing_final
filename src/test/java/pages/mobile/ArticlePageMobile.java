package pages.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ArticlePageMobile extends BaseMobilePage {
    private static final By TITLE = By.id("org.wikipedia:id/view_page_title_text");
    private final String previewTitle;

    public ArticlePageMobile(AndroidDriver driver, WebDriverWait wait, String previewTitle) {
        super(driver, wait);
        this.previewTitle = previewTitle == null ? "" : previewTitle;
    }

    public String getTitle() {
        return waitVisible(TITLE).getText();
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
}

