package tests.mobile;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.mobile.HomeMobilePage;
import pages.mobile.OnboardingPage;
import pages.mobile.SearchMobilePage;

public class SearchResultsTest extends BaseMobileTest {

    @Test
    public void shouldShowResultsForJavaQuery() {
        new OnboardingPage(driver, wait).skipIfPresent();

        SearchMobilePage search = new HomeMobilePage(driver, wait)
                .tapSearch()
                .typeQuery("Java");

        Assert.assertTrue(search.getResults().size() > 0, "Search results should not be empty");
    }
}
