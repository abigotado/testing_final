package pages.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;

public abstract class BaseMobilePage {
    protected final AndroidDriver driver;
    protected final WebDriverWait wait;
    protected final String appPackage;

    protected BaseMobilePage(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.appPackage = Config.get("appium.appPackage");
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

    protected AppiumBy findByText(String text) {
        return (AppiumBy) AppiumBy.androidUIAutomator(
                "new UiSelector().textContains(\"" + text + "\")");
    }

    protected By id(String suffix) {
        return By.id(appPackage + suffix);
    }
}

