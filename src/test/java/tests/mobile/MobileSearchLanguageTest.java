package tests.mobile;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.mobile.ArticlePageMobile;
import pages.mobile.OnboardingPage;
import pages.mobile.SearchPageMobile;

public class MobileSearchLanguageTest extends BaseMobileTest {

    @Test
    public void shouldSearchInSpanishAfterLanguageSwitch() {
        new OnboardingPage(driver, wait).skipIfPresent();

        SearchPageMobile search = new SearchPageMobile(driver, wait)
                .dismissOnboardingIfPresent()
                .openSearch()
                .selectLanguage("Español", "ES");

        Assert.assertTrue(search.getLanguageButtonCode().equalsIgnoreCase("ES"),
                "Language button should show ES");

        ArticlePageMobile article = search
                .typeQuery("Barcelona")
                .openFirstResult();

        Assert.assertTrue(article.getTitle().toLowerCase().contains("barcelona"),
                "Article title should contain Barcelona");
    }
}

