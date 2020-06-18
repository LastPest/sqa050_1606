package org.vtb.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Map;

public abstract class BasePage {

    //switch
    abstract By getLocator(String name);
    //Map
    abstract Map<String, By> getElem();

    public void clickButton(String name){
        DriverFactory.driver.findElement(getLocator(name)).click();
    }

    public boolean isElementDisplayed(String name){
       return DriverFactory.driver.findElement(getLocator(name)).isDisplayed();
    }

    public void clickButtonMap(String name){
        DriverFactory.driver.findElement(getElem().get(name)).click();
    }

    public boolean isElementDisplayedMap(String name){
        return DriverFactory.driver.findElement(getElem().get(name)).isDisplayed();
    }
}
