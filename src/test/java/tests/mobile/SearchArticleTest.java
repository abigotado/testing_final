package tests.mobile;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.mobile.ArticleMobilePage;
import pages.mobile.HomeMobilePage;
import pages.mobile.OnboardingPage;

public class SearchArticleTest extends BaseMobileTest {

    @Test
    public void shouldOpenArticleAndMatchTitle() {
        new OnboardingPage(driver, wait).skipIfPresent();

        ArticleMobilePage article = new HomeMobilePage(driver, wait)
                .tapSearch()
                .typeQuery("Selenium")
                .openFirstResult();

        String openedTitle = article.getTitle();
        Assert.assertFalse(openedTitle.isBlank(), "Article title should be present");
        if (!article.getExpectedTitleFromList().isBlank()) {
            Assert.assertTrue(openedTitle.toLowerCase().contains(article.getExpectedTitleFromList().toLowerCase()),
                    "Opened title should contain preview title");
        }
    }
}
