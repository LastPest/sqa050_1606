package org.vtb.web;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
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

    public void screenSh() throws IOException {
        File screenshot1 = new File("/home/galy4/нужное/work/PTC/New_PTC/SQA-050/SQA-050-01/screenshot/screen1.png");
        screenshot1.delete();
        byte[] screenshotBytes = ((TakesScreenshot) DriverFactory.driver).getScreenshotAs(OutputType.BYTES);
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(screenshotBytes));
        ImageIO.write(image, "png", screenshot1);
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
