package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

public class AppiumDriverFactory {

    private AppiumDriverFactory() {
    }

    public static AndroidDriver createAndroidDriver() {
        DesiredCapabilities caps = new DesiredCapabilities();
        setIfPresent(caps, "platformName", "appium.platformName");
        setIfPresent(caps, "deviceName", "appium.deviceName");
        setIfPresent(caps, "platformVersion", "appium.platformVersion");
        setIfPresent(caps, "appPackage", "appium.appPackage");
        setIfPresent(caps, "appActivity", "appium.appActivity");
        setIfPresent(caps, "appWaitActivity", "appium.appWaitActivity");
        setIfPresent(caps, "automationName", "appium.automationName");
        setIfPresent(caps, "language", "appium.language");
        setIfPresent(caps, "locale", "appium.locale");

        caps.setCapability("noReset", Config.getBoolean("appium.noReset", true));
        caps.setCapability("autoGrantPermissions", Config.getBoolean("appium.autoGrantPermissions", true));
        caps.setCapability("appWaitForLaunch", Config.getBoolean("appium.appWaitForLaunch", true));

        Optional.ofNullable(Config.get("appium.app"))
                .filter(s -> !s.isBlank())
                .ifPresent(path -> caps.setCapability("app", path));

        String timeout = Config.get("appium.newCommandTimeout");
        if (timeout != null && !timeout.isBlank()) {
            try {
                caps.setCapability("newCommandTimeout", Integer.parseInt(timeout));
            } catch (NumberFormatException ignored) {
                caps.setCapability("newCommandTimeout", timeout);
            }
        }

        try {
            return new AndroidDriver(new URL(Config.get("appium.server.url")), caps);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid Appium server URL", e);
        }
    }

    private static void setIfPresent(DesiredCapabilities caps, String capabilityKey, String configKey) {
        String value = Config.get(configKey);
        if (value != null && !value.isBlank()) {
            caps.setCapability(capabilityKey, value);
        }
    }
}

