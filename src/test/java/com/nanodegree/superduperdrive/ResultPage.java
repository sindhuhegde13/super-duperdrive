package com.nanodegree.superduperdrive;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * The type Result page.
 */
public class ResultPage {

    private final JavascriptExecutor js;

    /**
     * Instantiates a new Result page.
     *
     * @param driver the driver
     */
    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    @FindBy(id = "result-success")
    private WebElement result;

    /**
     * Click ok.
     */
    public void clickOk() {
        js.executeScript("arguments[0].click();", result);
    }
}
