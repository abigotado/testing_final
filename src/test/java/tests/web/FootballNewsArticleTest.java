package tests.web;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.web.ArticlePage;
import pages.web.HomePage;

public class FootballNewsArticleTest extends BaseWebTest {

    @Test
    public void shouldOpenFirstFootballNewsAndMatchTitle() {
        ArticlePage articlePage = new HomePage(driver, wait)
                .open()
                .goToFootball()
                .openNews()
                .openFirstArticle();

        String openedTitle = articlePage.getTitle();
        String previewTitle = articlePage.getExpectedPreviewTitle();

        Assert.assertFalse(openedTitle.isBlank(), "Opened article title should not be blank");
        if (!previewTitle.isBlank()) {
            String snippet = previewTitle.substring(0, Math.min(previewTitle.length(), 15)).trim().toLowerCase();
            Assert.assertTrue(openedTitle.toLowerCase().contains(snippet),
                    "Opened article title should contain preview snippet");
        }
    }
}

