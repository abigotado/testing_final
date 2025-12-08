package tests.web;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.web.HomePage;

public class NavigationFootballTest extends BaseWebTest {

    @Test
    public void shouldOpenFootballSectionFromHome() {
        new HomePage(driver, wait)
                .open()
                .goToFootball();

        String title = driver.getTitle();

        Assert.assertTrue(driver.getCurrentUrl().contains("/football/"), "URL should contain /football/");
        Assert.assertTrue(title.toLowerCase().contains("футбол"), "Page title should mention Футбол");
    }
}

