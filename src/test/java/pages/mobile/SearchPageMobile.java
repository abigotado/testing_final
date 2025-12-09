package pages.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPageMobile extends BaseMobilePage {
    private static final By ONBOARDING_SKIP_ALPHA = By.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button");
    private static final By ONBOARDING_SKIP = By.id("org.wikipedia:id/fragment_onboarding_skip_button");
    private static final By SEARCH_CONTAINER_PRIMARY = By.id("org.wikipedia:id/search_container");
    private static final By SEARCH_CONTAINER_ACCESS = AppiumBy.accessibilityId("Search Wikipedia");
    private static final By SEARCH_CONTAINER_PKG = By.id("org.wikipedia.alpha:id/search_container");
    private static final By SEARCH_CONTAINER_MATCH = AppiumBy.androidUIAutomator("new UiSelector().resourceIdMatches(\".*search_container\")");
    private static final By TOOLBAR_SEARCH = By.id("org.wikipedia:id/page_toolbar_button_search");
    private static final By TOOLBAR_SEARCH_PKG = By.id("org.wikipedia.alpha:id/page_toolbar_button_search");
    private static final By SEARCH_INPUT_PRIMARY = By.id("org.wikipedia:id/search_src_text");
    private static final By SEARCH_INPUT_PKG = By.id("org.wikipedia.alpha:id/search_src_text");
    private static final By LANGUAGE_BUTTON = By.id("org.wikipedia:id/search_lang_button");
    private static final By LANGUAGE_LIST_LABEL = By.id("org.wikipedia:id/option_label");
    private static final By LANGUAGE_LIST_CODE = By.id("org.wikipedia:id/option_lang");
    private static final By RESULT_TITLE_PRIMARY = By.id("org.wikipedia:id/page_list_item_title");
    private static final By RESULT_TITLE_PKG = By.id("org.wikipedia.alpha:id/page_list_item_title");

    public SearchPageMobile(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public SearchPageMobile dismissOnboardingIfPresent() {
        try {
            if (!driver.findElements(ONBOARDING_SKIP).isEmpty()) {
                driver.findElement(ONBOARDING_SKIP).click();
            } else if (!driver.findElements(ONBOARDING_SKIP_ALPHA).isEmpty()) {
                driver.findElement(ONBOARDING_SKIP_ALPHA).click();
            } else if (!driver.findElements(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"SKIP\")")).isEmpty()) {
                driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"SKIP\")")).click();
            }
        } catch (NoSuchElementException ignored) {
        }
        return this;
    }

    private boolean tryOpenSearchOnce() {
        dismissOverlaysIfPresent();
        try {
            waitAnyClickable(LONG,
                    SEARCH_CONTAINER_PRIMARY,
                    SEARCH_CONTAINER_PKG,
                    SEARCH_CONTAINER_ACCESS,
                    SEARCH_CONTAINER_MATCH,
                    TOOLBAR_SEARCH,
                    TOOLBAR_SEARCH_PKG
            ).click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public SearchPageMobile openSearch() {
        if (!tryOpenSearchOnce()) {
            driver.navigate().back();
            tryOpenSearchOnce();
        }
        return this;
    }

    public SearchPageMobile typeQuery(String query) {
        waitAnyVisible(LONG, SEARCH_INPUT_PRIMARY, SEARCH_INPUT_PKG).sendKeys(query);
        return this;
    }

    public ArticlePageMobile openFirstResult() {
        WebElement first = waitAnyVisible(LONG, RESULT_TITLE_PRIMARY, RESULT_TITLE_PKG);
        String previewTitle = first.getText();
        first.click();
        return new ArticlePageMobile(driver, wait, previewTitle);
    }

    public SearchPageMobile selectLanguage(String languageLabel, String languageCode) {
        dismissOverlaysIfPresent();
        waitAnyClickable(LONG, LANGUAGE_BUTTON).click();
        wait.until(d -> !d.findElements(LANGUAGE_LIST_LABEL).isEmpty() || !d.findElements(LANGUAGE_LIST_CODE).isEmpty());

        List<WebElement> labels = driver.findElements(LANGUAGE_LIST_LABEL);
        for (WebElement label : labels) {
            if (label.getText().equalsIgnoreCase(languageLabel)) {
                label.click();
                return this;
            }
        }

        List<WebElement> codes = driver.findElements(LANGUAGE_LIST_CODE);
        for (WebElement code : codes) {
            if (code.getText().equalsIgnoreCase(languageCode)) {
                code.click();
                return this;
            }
        }

        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().textContains(\"" + languageLabel + "\")")).click();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Language not found: " + languageLabel + " / " + languageCode);
        }
        return this;
    }

    public String getLanguageButtonCode() {
        return waitAnyVisible(LONG, LANGUAGE_BUTTON).getText();
    }
}
