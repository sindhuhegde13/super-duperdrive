package com.nanodegree.superduperdrive;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * The type Signup page.
 */
public class SignupPage {
    @FindBy(id = "inputFirstName")
    private WebElement firstNameField;

    @FindBy(id = "inputLastName")
    private WebElement lastNameField;

    @FindBy(id = "inputUsername")
    private WebElement usernameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "submit-Btn")
    private WebElement submitButton;

    /**
     * Instantiates a new Signup page.
     *
     * @param webDriver the web driver
     */
    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Signup.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param username  the username
     * @param password  the password
     */
    public void signup(String firstName, String lastName, String username, String password) {
        this.firstNameField.sendKeys(firstName);
        this.lastNameField.sendKeys(lastName);
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.submitButton.click();
    }

}
