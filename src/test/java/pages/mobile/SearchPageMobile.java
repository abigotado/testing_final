package pages.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPageMobile extends BaseMobilePage {
    private static final By ONBOARDING_SKIP = By.id("org.wikipedia:id/fragment_onboarding_skip_button");
    private static final By SEARCH_CONTAINER = By.id("org.wikipedia:id/search_container");
    private static final By SEARCH_INPUT = By.id("org.wikipedia:id/search_src_text");
    private static final By LANGUAGE_BUTTON = By.id("org.wikipedia:id/search_lang_button");
    private static final By LANGUAGE_LIST_LABEL = By.id("org.wikipedia:id/option_label");
    private static final By LANGUAGE_LIST_CODE = By.id("org.wikipedia:id/option_lang");
    private static final By RESULT_TITLE = By.id("org.wikipedia:id/page_list_item_title");

    public SearchPageMobile(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public SearchPageMobile dismissOnboardingIfPresent() {
        try {
            WebElement skip = driver.findElement(ONBOARDING_SKIP);
            skip.click();
        } catch (NoSuchElementException ignored) {
        }
        return this;
    }

    public SearchPageMobile openSearch() {
        waitClickable(SEARCH_CONTAINER).click();
        return this;
    }

    public SearchPageMobile typeQuery(String query) {
        waitVisible(SEARCH_INPUT).sendKeys(query);
        return this;
    }

    public ArticlePageMobile openFirstResult() {
        WebElement first = wait.until(d -> {
            List<WebElement> results = d.findElements(RESULT_TITLE);
            return results.isEmpty() ? null : results.get(0);
        });
        String previewTitle = first.getText();
        first.click();
        return new ArticlePageMobile(driver, wait, previewTitle);
    }

    public SearchPageMobile selectLanguage(String languageLabel, String languageCode) {
        waitClickable(LANGUAGE_BUTTON).click();
        wait.until(d -> !d.findElements(LANGUAGE_LIST_LABEL).isEmpty());

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

        // fallback: search by text using UiSelector
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().textContains(\"" + languageLabel + "\")")).click();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Language not found: " + languageLabel + " / " + languageCode);
        }
        return this;
    }

    public String getLanguageButtonCode() {
        return waitVisible(LANGUAGE_BUTTON).getText();
    }
}

