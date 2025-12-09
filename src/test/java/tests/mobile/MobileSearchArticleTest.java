package tests.mobile;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.mobile.ArticlePageMobile;
import pages.mobile.OnboardingPage;
import pages.mobile.SearchPageMobile;

public class MobileSearchArticleTest extends BaseMobileTest {

    @Test
    public void shouldOpenArticleFromSearch() {
        new OnboardingPage(driver, wait).skipIfPresent();

        ArticlePageMobile article = new SearchPageMobile(driver, wait)
                .dismissOnboardingIfPresent()
                .openSearch()
                .selectLanguage("English", "EN")
                .typeQuery("Selenium")
                .openFirstResult();

        String title = article.getTitle();
        Assert.assertTrue(title.toLowerCase().contains("selenium"), "Article title should contain Selenium");
    }
}

