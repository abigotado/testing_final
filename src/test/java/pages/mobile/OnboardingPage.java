package pages.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OnboardingPage extends BaseMobilePage {
    private static final AppiumBy SKIP_BUTTON = (AppiumBy) AppiumBy.id("org.wikipedia:id/fragment_onboarding_skip_button");
    private static final AppiumBy SKIP_BUTTON_ALT = (AppiumBy) AppiumBy.id("org.wikipedia:id/skip_button");

    public OnboardingPage(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public OnboardingPage skipIfPresent() {
        if (!driver.findElements(SKIP_BUTTON).isEmpty()) {
            driver.findElement(SKIP_BUTTON).click();
        } else if (!driver.findElements(SKIP_BUTTON_ALT).isEmpty()) {
            driver.findElement(SKIP_BUTTON_ALT).click();
        }
        return this;
    }
}

