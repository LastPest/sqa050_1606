package org.vtb.web;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchResultPage {

    private WebDriver driver;
    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "search-filter-reset-form")
    WebElement filter;

    @FindBy(className = "wt-checkbox__label")
    List<WebElement> chkBoxes;

    @FindBy(xpath = "//span[text()='Сортировать по: Релевантность']")
    WebElement sort;

    @FindBy(className = "placeholder-content")
    List<WebElement> results;

    public SearchResultPage waitPageOpened(){
        DriverFactory.waitVar.until(ExpectedConditions.presenceOfElementLocated(By.id("search-filter-reset-form")));
        return this;
    }

    public void printChkBoxText(int num){
        System.out.println(chkBoxes.get(num).getText());
    }

    public SearchResultPage checkSortAttribute(String cl){
        Assertions.assertTrue(sort.getAttribute("class").equalsIgnoreCase(cl));
        return this;
    }

    public void checkCollectionSize(int size){
        Assertions.assertTrue(results.size()>size);
    }
}
