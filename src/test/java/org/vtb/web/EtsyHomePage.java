package org.vtb.web;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

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

}
