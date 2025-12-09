package tests.mobile;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.mobile.ArticlePageMobile;
import pages.mobile.OnboardingPage;
import pages.mobile.SearchPageMobile;

public class MobileArticleScrollTest extends BaseMobileTest {

    @Test
    public void shouldScrollToHistorySection() {
        new OnboardingPage(driver, wait).skipIfPresent();

        ArticlePageMobile article = new SearchPageMobile(driver, wait)
                .dismissOnboardingIfPresent()
                .openSearch()
                .selectLanguage("English", "EN")
                .typeQuery("Java (programming language)")
                .openFirstResult();

        boolean foundHistory = article.scrollToHistory();
        Assert.assertTrue(foundHistory, "History section should be reachable via scroll");
    }
}

