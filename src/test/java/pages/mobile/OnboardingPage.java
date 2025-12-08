package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OnboardingPage extends BaseMobilePage {
    private static final By SKIP_BUTTON = By.id("org.wikipedia:id/fragment_onboarding_skip_button");
    private static final By SKIP_BUTTON_ALT = By.id("org.wikipedia:id/skip_button");

    public OnboardingPage(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void skipIfPresent() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            shortWait.until(ExpectedConditions.presenceOfElementLocated(SKIP_BUTTON));
            click(SKIP_BUTTON);
            return;
        } catch (Exception ignored) {
        }
        try {
            shortWait.until(ExpectedConditions.presenceOfElementLocated(SKIP_BUTTON_ALT));
            click(SKIP_BUTTON_ALT);
        } catch (Exception ignored) {
            // no onboarding to skip
        }
    }
}

