package pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FootballPage extends BasePage {
    private static final By NEWS_TAB = By.cssSelector("a[href='/football/news/']");
    private static final By MATCHES_TAB = By.cssSelector("a[href='/football/match/']");
    private static final By ARTICLE_LINKS = By.cssSelector("main a[href*='/football/'][href$='.html']");

    public FootballPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public FootballPage openNews() {
        navigateOrClick(NEWS_TAB, "/football/news/");
        return this;
    }

    public FootballPage openMatches() {
        navigateOrClick(MATCHES_TAB, "/football/match/");
        return this;
    }

    public ArticlePage openFirstArticle() {
        try {
            WebElement firstArticle = wait.until(d -> {
                List<WebElement> links = d.findElements(ARTICLE_LINKS);
                return links.stream()
                        .filter(WebElement::isDisplayed)
                        .filter(el -> !el.getText().isBlank())
                        .findFirst()
                        .orElse(null);
            });
            String title = firstArticle.getText();
            scrollIntoView(firstArticle);
            firstArticle.click();
            return new ArticlePage(driver, wait, title);
        } catch (TimeoutException e) {
            String source = driver.getPageSource();
            Pattern pattern = Pattern.compile("href=\\\"(\\/football\\/[^\\\"]+\\.html)\\\"");
            Matcher matcher = pattern.matcher(source);
            if (matcher.find()) {
                String url = Config.get("baseUrl") + matcher.group(1);
                driver.navigate().to(url);
                return new ArticlePage(driver, wait, "");
            }
            throw e;
        }
    }

    public boolean hasAtLeastOneMatchCard() {
        return wait.until(d -> {
            List<WebElement> matchLinks = d.findElements(By.cssSelector("a[href*='/football/match/']"));
            return !matchLinks.isEmpty();
        });
    }

    private void navigateOrClick(By locator, String path) {
        try {
            waitClickable(locator).click();
        } catch (TimeoutException e) {
            driver.navigate().to(Config.get("baseUrl") + path);
        }
    }
}

