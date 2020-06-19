package org.vtb.web;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/*approach 1
public class EtsyHomePage {
    private WebDriver driver;

    By searchField = By.id("global-enhancements-search-query");
    By searchButton = By.xpath("//button[@value=\"Search\"]");

    //constructor
    public EtsyHomePage(WebDriver driver) {
        this.driver = driver;
    }

    //methods
    public void searchFor(String text){
        driver.findElement(searchField).sendKeys(text);
        driver.findElement(searchButton).click();
    }

    public void clickButton(By locator){
        driver.findElement(locator).click();
    }
}
 */

//approach 2
@Getter
public class EtsyHomePage extends BasePage{

    public EtsyHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "global-enhancements-search-query")
    WebElement searchField;

    //logical or
    @FindAll({
            @FindBy(xpath = "//button[@value=\"Search\"]"),
            @FindBy(tagName = "button1")
    })
    WebElement searchButton;

    //logical and
    @FindBys({
            @FindBy(tagName = "button"),
            @FindBy(xpath = "//button[contains(text(),'Sign in')]")
    })
    private WebElement signInButton;

    @FindBy(xpath = "//input[@name=\"google_code\"]/following-sibling::button")
    private WebElement googleButton;

    @FindBy(xpath = "//input[@name=\"facebook_access_token\"]/following-sibling::button")
    private WebElement facebookButton;

    @FindBy(tagName = "header")
    private WebElement header;

    @FindBy(id = "join_neu_email_field")
    private WebElement email;

    // approach 3
    By getLocator(String name){
        By obj = null;
        switch(name){
            case "Google":
                obj = By.xpath("//input[@name=\"google_code\"]/following-sibling::button"); break;
            case "Sign In":
                obj = By.xpath("//button[contains(text(),'Sign in')]"); break;
            case "Facebook":
                obj = By.xpath("//input[@name=\"facebook_access_token\"]/following-sibling::button"); break;
        }
        return obj;
    }

    //methods
    public SearchResultPage searchFor(String text){
        searchField.sendKeys(text);
        searchButton.click();
        return new SearchResultPage(DriverFactory.driver);
    }

    public EtsyHomePage clickButtonS(String name){
        DriverFactory.driver.findElement(getLocator(name)).click();
        return this;
    }

    public Map<String, By> getElem() {
        return elem;
    }

    Map<String, By> elem = new HashMap() {{
        put("Google", By.xpath("//input[@name=\"google_code\"]/following-sibling::button"));
        put("Sign In", By.xpath("//button[contains(text(),'Sign in')]"));
        put("Facebook", By.xpath("//input[@name=\"facebook_access_token\"]/following-sibling::button"));
    }};

    public void openSubmenu(String submenu){
        Actions builder = new Actions(DriverFactory.driver);
        builder.moveToElement(DriverFactory.driver.findElement(By.id("catnav-primary-link-10855"))).build().perform();
        DriverFactory.waitVar.until(presenceOfElementLocated(By.partialLinkText(submenu)));
        DriverFactory.driver.findElement(By.partialLinkText(submenu)).click();
    }

    public void clickLogo(){
        header.findElement(By.xpath("./div/a/span[contains(@class, \"etsy-icon\")]")).click();
    }

    public void closeSignInWindow(){
        email.sendKeys(Keys.ESCAPE);
    }

    public void executeJS(String script){
        JavascriptExecutor jse = (JavascriptExecutor) DriverFactory.driver;
        jse.executeScript(script);
        DriverFactory.driver.switchTo().alert().accept();
    }

    public void openNewTab(){
        JavascriptExecutor jse = (JavascriptExecutor) DriverFactory.driver;
        jse.executeScript("window.open();");
    }

    public void navigateToNewTab(int i){
        List<String> tabs = new ArrayList<>(DriverFactory.driver.getWindowHandles());
        if(tabs.size()>1){
            DriverFactory.driver.switchTo().window(tabs.get(i));
        }
    }
}
