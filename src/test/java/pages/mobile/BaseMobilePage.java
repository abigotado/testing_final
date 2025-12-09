package pages.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;

import java.time.Duration;
import java.util.List;

public abstract class BaseMobilePage {
    protected final AndroidDriver driver;
    protected final WebDriverWait wait;
    protected final String appPackage;
    protected final String fallbackPackage = "org.wikipedia";
    protected static final Duration LONG = Duration.ofSeconds(45);

    protected BaseMobilePage(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        String pkg = System.getProperty("appium.appPackage", Config.get("appium.appPackage"));
        Object fromCaps = driver.getCapabilities().getCapability("appium:appPackage");
        if (fromCaps != null && !fromCaps.toString().isBlank()) {
            pkg = fromCaps.toString();
        }
        this.appPackage = pkg;
    }

    protected WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitVisible(AppiumBy locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement findByText(String text) {
        return waitVisible(AppiumBy.androidUIAutomator(
                "new UiSelector().textContains(\"" + text + "\")"));
    }

    protected By id(String suffix) {
        return By.id(appPackage + suffix);
    }

    protected By idFallback(String suffix) {
        return By.id(fallbackPackage + suffix);
    }

    protected WebElement waitAnyVisible(Duration timeout, By... locators) {
        WebDriverWait customWait = new WebDriverWait(driver, timeout);
        return customWait.until(d -> {
            for (By locator : locators) {
                List<WebElement> els = d.findElements(locator);
                if (!els.isEmpty() && els.get(0).isDisplayed()) {
                    return els.get(0);
                }
            }
            return null;
        });
    }

    protected WebElement waitAnyClickable(Duration timeout, By... locators) {
        WebDriverWait customWait = new WebDriverWait(driver, timeout);
        return customWait.until(d -> {
            for (By locator : locators) {
                List<WebElement> els = d.findElements(locator);
                if (!els.isEmpty() && els.get(0).isDisplayed()) {
                    return els.get(0);
                }
            }
            return null;
        });
    }

    /**
     * Best-effort dismissal of transient popups (e.g., Wikipedia games promo).
     */
    protected void dismissOverlaysIfPresent() {
        try {
            // explicit close button id from popup dump
            List<WebElement> closeIdEls = driver.findElements(AppiumBy.id("org.wikipedia.alpha:id/closeButton"));
            if (!closeIdEls.isEmpty() && closeIdEls.get(0).isDisplayed()) {
                closeIdEls.get(0).click();
                return;
            }

            List<WebElement> closeButtons = driver.findElements(
                    AppiumBy.androidUIAutomator("new UiSelector().descriptionMatches(\"(?i)close|dismiss|got it\")"));
            if (!closeButtons.isEmpty()) {
                closeButtons.get(0).click();
                return;
            }
            List<WebElement> xButtons = driver.findElements(
                    AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageButton\")"));
            for (WebElement btn : xButtons) {
                if (btn.isDisplayed()) {
                    btn.click();
                    return;
                }
            }
            By[] aggressive = new By[]{
                    AppiumBy.id("android:id/button2"),
                    AppiumBy.id("android:id/button1"),
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Close\")"),
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Not now\")"),
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"No thanks\")"),
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Got it\")")
            };
            for (By loc : aggressive) {
                List<WebElement> els = driver.findElements(loc);
                if (!els.isEmpty() && els.get(0).isDisplayed()) {
                    els.get(0).click();
                    return;
                }
            }
            if (!driver.findElements(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Wikipedia games\")")).isEmpty()
                    || !driver.findElements(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Play today's game\")")).isEmpty()) {
                List<WebElement> clickable = driver.findElements(AppiumBy.androidUIAutomator("new UiSelector().clickable(true)"));
                if (!clickable.isEmpty()) {
                    clickable.get(0).click();
                    return;
                }
                driver.navigate().back();
                driver.navigate().back();
            }
        } catch (Exception ignored) {
        }
    }
}
