package org.vtb.web;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstWebTest {

    private static WebDriver driver;
    private final EtsyHomePage etsyHomePage = new EtsyHomePage(driver);
    private final SearchResultPage searchResultPage = new SearchResultPage(driver);

    @BeforeAll
    static void setup(){
        driver = new DriverFactory().driver;
    }

    @Test
    void etsyPage1() {
        driver.findElement(By.id("global-enhancements-search-query")).sendKeys("очки");
        driver.findElement(By.xpath("//button[@value=\"Search\"]")).click();
        DriverFactory.waitVar.until(ExpectedConditions.presenceOfElementLocated(By.id("search-filter-reset-form")));
        Assertions.assertTrue(driver.findElement(By.id("search-filter-reset-form")).isDisplayed());

        List<WebElement> chkboxes = driver.findElements(By.className("wt-checkbox__label"));
        System.out.println(chkboxes.get(0).getText());

        chkboxes.get(0).sendKeys(Keys.LEFT_CONTROL + "v");
    }

    @Test
    void etsyPage2() {
        etsyHomePage.searchFor("очки");
        searchResultPage.waitPageOpened();
        searchResultPage.printChkBoxText(0);
    }

    @Test
    void registrationButtons(){
        etsyHomePage.getSignInButton().click();
        assertAll(
                ()->etsyHomePage.getGoogleButton().isDisplayed(),
                ()->etsyHomePage.getFacebookButton().isDisplayed());
    }

    @Test
    void registrationSwitch(){
        etsyHomePage.clickButton("Sign In");
        assertAll(
                ()->assertTrue(etsyHomePage.isElementDisplayed( "Google")),
                ()->assertTrue(etsyHomePage.isElementDisplayed("Facebook")));
    }

    @Test
    void registrationMap(){
        etsyHomePage.clickButtonMap("Sign In");
        assertAll(
                ()->assertTrue(etsyHomePage.isElementDisplayedMap( "Google")),
                ()->assertTrue(etsyHomePage.isElementDisplayedMap("Facebook")));
    }

    @Test
    void attribute(){
        etsyHomePage.searchFor("очки")
                .waitPageOpened()
                .checkSortAttribute("wt-menu__trigger__label");
        searchResultPage.checkCollectionSize(30);
    }

    @AfterAll
    static void tearDown(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
