package pages.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OnboardingPage extends BaseMobilePage {

    public OnboardingPage(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public OnboardingPage skipIfPresent() {
        By skip1 = id(":id/fragment_onboarding_skip_button");
        By skip2 = id(":id/skip_button");
        By skipAlpha1 = idFallback(":id/fragment_onboarding_skip_button");
        By skipAlpha2 = idFallback(":id/skip_button");
        By skipText = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"SKIP\")");
        By cont = id(":id/fragment_onboarding_forward_button");
        By contAlpha = idFallback(":id/fragment_onboarding_forward_button");

        // loop a few times to clear all onboarding slides
        for (int i = 0; i < 5; i++) {
            try {
                var clickable = waitAnyClickable(Duration.ofSeconds(10),
                        skip1, skip2, skipAlpha1, skipAlpha2, skipText, cont, contAlpha);
                if (clickable != null) {
                    clickable.click();
                    continue;
                }
            } catch (Exception ignored) {
                // if nothing clickable, break
            }
            break;
        }
        return this;
    }
}

