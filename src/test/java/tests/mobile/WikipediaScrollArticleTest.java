package tests.mobile;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.mobile.ArticlePage;
import pages.mobile.MainPage;
import pages.mobile.OnboardingPage;
import pages.mobile.SearchPage;

public class WikipediaScrollArticleTest extends BaseMobileTest {

    @Test
    public void shouldScrollArticleAndFindSection() {
        new OnboardingPage(driver, wait).skipIfPresent();

        ArticlePage articlePage = new MainPage(driver, wait)
                .openSearch()
                .typeQuery("Java")
                .openFirstResult();

        articlePage.scrollToSectionContaining("History");
        Assert.assertTrue(articlePage.isSectionVisible("History"),
                "Expected to find 'History' section after scrolling");
    }
}

