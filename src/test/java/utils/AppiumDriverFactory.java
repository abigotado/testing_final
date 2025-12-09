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
        caps.setCapability("appium:deviceName", Config.get("appium.deviceName"));

        String platformVersion = Config.get("appium.platformVersion");
        if (platformVersion != null && !platformVersion.isBlank()) {
            caps.setCapability("appium:platformVersion", platformVersion);
        }

        caps.setCapability("appium:automationName", Config.get("appium.automationName"));
        caps.setCapability("appium:appPackage", Config.get("appium.appPackage"));
        caps.setCapability("appium:appActivity", Config.get("appium.appActivity"));
        caps.setCapability("appium:noReset", Config.getBoolean("appium.noReset", true));
        caps.setCapability("appium:autoGrantPermissions", true);

        String appPath = Config.get("appium.app");
        if (appPath != null && !appPath.isBlank()) {
            caps.setCapability("appium:app", appPath);
        }

        String udid = Config.get("appium.udid");
        if (udid != null && !udid.isBlank()) {
            caps.setCapability("appium:udid", udid);
        }

        caps.setCapability("appium:newCommandTimeout", 120);

        try {
            return new AndroidDriver(new URL(Config.get("appium.server.url")), caps);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid Appium server URL", e);
        }
    }
}
