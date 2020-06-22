package org.vtb.web;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(TestWatcher.class)
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
        etsyHomePage.executeJS("alert('dkjshkjsdhfksdjh')");
        etsyHomePage.searchFor("очки");
        searchResultPage.waitPageOpened();
        searchResultPage.printChkBoxText(0);
    }
    
   @Test
    void etsyPage3() {
        etsyHomePage.executeJS("alert('ничего нет')");
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
        etsyHomePage.closeSignInWindow();
    }

    @Test
    void registrationSwitch() throws IOException {
        etsyHomePage.clickButton("Sign In");
        assertAll(
                ()->assertTrue(etsyHomePage.isElementDisplayed( "Google")),
                ()->assertTrue(etsyHomePage.isElementDisplayed("Facebook")));
        etsyHomePage.closeSignInWindow();
        searchResultPage.screenSh();
    }

    @Test
    void registrationMap(){
        etsyHomePage.clickButtonMap("Sign In");
        assertAll(
                ()->assertTrue(etsyHomePage.isElementDisplayedMap( "Google")),
                ()->assertTrue(etsyHomePage.isElementDisplayedMap("Facebook")));
        etsyHomePage.closeSignInWindow();
    }

    @Test
    void attribute(){
        etsyHomePage.searchFor("очки")
                .waitPageOpened()
                .checkSortAttribute("wt-menu__trigger__label");
        searchResultPage.checkCollectionSize(30);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ободк", "Очки"})
    void submenuTest(String sm){
        etsyHomePage.openSubmenu(sm);
        searchResultPage.waitPageOpened();
    }

    @Test
    @Disabled
    void openGoogle(){
        etsyHomePage.openNewTab();
        etsyHomePage.navigateToNewTab(1);
        driver.navigate().to("http://google.com");
    }

    @AfterEach
    void clickLogo(){
        etsyHomePage.clickLogo();
    }

    @AfterAll
    static void tearDown(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
