package tests.web;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.web.HomePage;

public class FootballMatchesTest extends BaseWebTest {

    @Test
    public void shouldSeeMatchesListOnFootballMatchesTab() {
        boolean hasMatches = new HomePage(driver, wait)
                .open()
                .goToFootball()
                .openMatches()
                .hasAtLeastOneMatchCard();

        Assert.assertTrue(hasMatches, "Matches tab should display at least one match card/link");
    }
}

