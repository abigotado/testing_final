package tests.mobile;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.mobile.MainPage;
import pages.mobile.OnboardingPage;
import pages.mobile.SearchPage;

public class WikipediaSearchResultsTest extends BaseMobileTest {

    @Test
    public void shouldShowResultsForSeleniumQuery() {
        new OnboardingPage(driver, wait).skipIfPresent();

        SearchPage searchPage = new MainPage(driver, wait)
                .openSearch()
                .typeQuery("Selenium");

        Assert.assertTrue(searchPage.hasResults(), "Search results should not be empty");
        String firstTitle = searchPage.getFirstResultTitle();
        Assert.assertTrue(firstTitle.toLowerCase().contains("selenium"), "First result should mention Selenium");
    }
}

