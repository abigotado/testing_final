package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverFactory {

    private AppiumDriverFactory() {
    }

    public static AndroidDriver createAndroidDriver() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", Config.get("appium.platformName"));
        caps.setCapability("deviceName", Config.get("appium.deviceName"));
        caps.setCapability("platformVersion", Config.get("appium.platformVersion"));
        caps.setCapability("automationName", Config.get("appium.automationName"));
        caps.setCapability("appPackage", Config.get("appium.appPackage"));
        caps.setCapability("appActivity", Config.get("appium.appActivity"));
        caps.setCapability("noReset", Config.getBoolean("appium.noReset", true));
        caps.setCapability("autoGrantPermissions", true);
        String appPath = Config.get("appium.app");
        if (appPath != null && !appPath.isBlank()) {
            caps.setCapability("app", appPath);
        }
        caps.setCapability("newCommandTimeout", 120);

        try {
            return new AndroidDriver(new URL(Config.get("appium.server.url")), caps);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid Appium server URL", e);
        }
    }
}
