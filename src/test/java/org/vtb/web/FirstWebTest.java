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

public class FirstWebTest {

    private static WebDriver driver;

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

    @AfterAll
    static void tearDown(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
