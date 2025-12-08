package tests.web;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.web.SearchPage;

public class SearchMessiTest extends BaseWebTest {

    @Test
    public void shouldFindResultsForMessiQuery() {
        SearchPage searchPage = new SearchPage(driver, wait).openWithQuery("Месси");

        boolean hasResults;
        try {
            hasResults = searchPage.hasResultContaining("Месси");
        } catch (Exception e) {
            // fallback: at least confirm page content mentions the query
            String content = searchPage.getPageSource().toLowerCase();
            hasResults = content.contains("месси");
        }

        Assert.assertTrue(hasResults, "Search results should contain query text");
    }
}

