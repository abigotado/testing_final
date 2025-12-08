package tests.mobile;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.mobile.ArticlePage;
import pages.mobile.MainPage;
import pages.mobile.OnboardingPage;
import pages.mobile.SearchPage;

public class WikipediaOpenArticleTest extends BaseMobileTest {

    @Test
    public void shouldOpenArticleAndMatchTitle() {
        new OnboardingPage(driver, wait).skipIfPresent();

        SearchPage searchPage = new MainPage(driver, wait)
                .openSearch()
                .typeQuery("Selenium");

        String previewTitle = searchPage.getFirstResultTitle();
        ArticlePage articlePage = searchPage.openFirstResult();

        String openedTitle = articlePage.getTitle();
        Assert.assertFalse(openedTitle.isBlank(), "Opened article title should not be blank");

        String snippet = previewTitle.substring(0, Math.min(previewTitle.length(), 12)).toLowerCase();
        Assert.assertTrue(openedTitle.toLowerCase().contains(snippet),
                "Opened article title should contain preview snippet");
    }
}

