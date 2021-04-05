package com.nanodegree.superduperdrive;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * The type Login page.
 */
public class LoginPage {

    @FindBy(id="inputUsername")
    private WebElement usernameField;

    @FindBy(id="inputPassword")
    private WebElement passwordField;

    @FindBy(id="submit-button")
    private WebElement submitButton;

    /**
     * Instantiates a new Login page.
     *
     * @param webDriver the web driver
     */
    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Login.
     *
     * @param username the username
     * @param password the password
     */
    public void login(String username, String password) {
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.submitButton.click();
    }

}
