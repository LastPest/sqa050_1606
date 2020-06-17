package org.vtb.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    static WebDriver driver;
    static WebDriverWait waitVar;


    public DriverFactory() {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
        ChromeOptions options = new ChromeOptions();
//        options.setHeadless(true);
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.navigate().to("https://www.etsy.com/");
//        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7000, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
        waitVar = new WebDriverWait(driver, 5);
    }
}
