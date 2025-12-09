package pages.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ArticleMobilePage extends BaseMobilePage {
    private static final AppiumBy TITLE = (AppiumBy) AppiumBy.id("org.wikipedia:id/view_page_title_text");
    private final String expectedTitleFromList;

    public ArticleMobilePage(AndroidDriver driver, WebDriverWait wait, String expectedTitleFromList) {
        super(driver, wait);
        this.expectedTitleFromList = expectedTitleFromList == null ? "" : expectedTitleFromList;
    }

    public String getTitle() {
        return waitVisible(TITLE).getText();
    }

    public String getExpectedTitleFromList() {
        return expectedTitleFromList;
    }

    public boolean scrollToText(String text) {
        try {
            String uiScroll = String.format(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(\"%s\")", text);
            WebElement element = driver.findElement(AppiumBy.androidUIAutomator(uiScroll));
            return element != null;
        } catch (Exception e) {
            return false;
        }
    }
}




