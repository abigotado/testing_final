package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OnboardingPage extends BaseMobilePage {

    public OnboardingPage(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public OnboardingPage skipIfPresent() {
        if (!driver.findElements(id(":id/fragment_onboarding_skip_button")).isEmpty()) {
            driver.findElement(id(":id/fragment_onboarding_skip_button")).click();
        } else if (!driver.findElements(id(":id/skip_button")).isEmpty()) {
            driver.findElement(id(":id/skip_button")).click();
        } else if (!driver.findElements(findByText("SKIP")).isEmpty()) {
            driver.findElement(findByText("SKIP")).click();
        }
        return this;
    }
}

