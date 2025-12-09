package tests.mobile;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.mobile.ArticleMobilePage;
import pages.mobile.HomeMobilePage;
import pages.mobile.OnboardingPage;

public class ArticleScrollTest extends BaseMobileTest {

    @Test
    public void shouldScrollToHistorySection() {
        new OnboardingPage(driver, wait).skipIfPresent();

        ArticleMobilePage article = new HomeMobilePage(driver, wait)
                .tapSearch()
                .typeQuery("Appium")
                .openFirstResult();

        boolean found = article.scrollToText("History");
        Assert.assertTrue(found, "Expected to find 'History' section in article after scrolling");
    }
}
