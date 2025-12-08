package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ArticlePage extends BaseMobilePage {
    private static final By TITLE = By.id("org.wikipedia:id/view_page_title_text");
    private final String expectedFromList;

    public ArticlePage(AndroidDriver driver, WebDriverWait wait, String expectedFromList) {
        super(driver, wait);
        this.expectedFromList = expectedFromList == null ? "" : expectedFromList;
    }

    public String getTitle() {
        return waitVisible(TITLE).getText();
    }

    public String getExpectedFromList() {
        return expectedFromList;
    }

    public ArticlePage scrollToSectionContaining(String text) {
        scrollToTextContains(text);
        return this;
    }

    public boolean isSectionVisible(String text) {
        try {
            WebElement el = driver.findElement(By.xpath(String.format("//*[contains(@text,'%s')]", text)));
            return el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

