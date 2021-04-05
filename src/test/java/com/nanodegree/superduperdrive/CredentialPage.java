package com.nanodegree.superduperdrive;

import com.nanodegree.superduperdrive.model.Credentials;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * The type Credential page.
 */
public class CredentialPage {

    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    @FindBy(id = "add-new-credential")
    private WebElement addNewCred;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;

    @FindBy(id = "saveChanges")
    private WebElement saveChanges;

    @FindBy(id = "edit-credential")
    private WebElement editCredential;

    @FindBy(id = "delete-credential")
    private WebElement deleteCredential;

    @FindBy(id = "credential-url")
    private WebElement credUrl;

    @FindBy(id = "credential-username")
    private WebElement credUsername;

    @FindBy(id = "credential-password")
    private WebElement credPassword;

    @FindBy(id = "credSaveChanges")
    private WebElement credSaveChanges;

    @FindBy(id = "tableCredentialUrl")
    private WebElement tableCredUrl;

    @FindBy(id = "tableCredentialUsername")
    private WebElement tableCredUsername;

    @FindBy(id = "tableCredentialPassword")
    private WebElement tableCredPassword;

    private final JavascriptExecutor js;

    private final WebDriverWait wait;

    /**
     * Instantiates a new Credential page.
     *
     * @param driver the driver
     */
    public CredentialPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 500);
    }

    /**
     * Logout.
     */
    public void logout() {
        js.executeScript("arguments[0].click();", logoutButton);
    }

    /**
     * Edit credential.
     */
    public void editCredential() {
        js.executeScript("arguments[0].click();", editCredential);
    }

    /**
     * Delete credential.
     */
    public void deleteCredential() {
        js.executeScript("arguments[0].click();", deleteCredential);
    }

    /**
     * Add new credential.
     */
    public void addNewCredential() {
        js.executeScript("arguments[0].click();", addNewCred);
    }

    /**
     * Sets credential url.
     *
     * @param url the url
     */
    public void setCredentialUrl(String url) {
        js.executeScript("arguments[0].value='" + url + "';", credUrl);
    }

    /**
     * Sets credential username.
     *
     * @param username the username
     */
    public void setCredentialUsername(String username) {
        js.executeScript("arguments[0].value='" + username + "';", credUsername);
    }

    /**
     * Sets credential password.
     *
     * @param password the password
     */
    public void setCredentialPassword(String password) {
        js.executeScript("arguments[0].value='" + password + "';", credPassword);
    }

    /**
     * Nav to credentials tab.
     */
    public void navToCredentialsTab() {
        js.executeScript("arguments[0].click();", navCredentialsTab);
    }

    /**
     * Save credential changes.
     */
    public void saveCredentialChanges() {
        js.executeScript("arguments[0].click();", credSaveChanges);
    }

    /**
     * Modify cred url.
     *
     * @param newCredUrl the new cred url
     */
    public void modifyCredUrl(String newCredUrl) {
        wait.until(ExpectedConditions.elementToBeClickable(credUrl)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(credUrl)).sendKeys(newCredUrl);
    }

    /**
     * Modify cred username.
     *
     * @param newCredUsername the new cred username
     */
    public void modifyCredUsername(String newCredUsername) {
        wait.until(ExpectedConditions.elementToBeClickable(credUsername)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(credUsername)).sendKeys(newCredUsername);
    }

    /**
     * Modify cred password.
     *
     * @param newCredPassword the new cred password
     */
    public void modifyCredPassword(String newCredPassword) {
        wait.until(ExpectedConditions.elementToBeClickable(credPassword)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(credPassword)).sendKeys(newCredPassword);
    }


    /**
     * No credentials boolean.
     *
     * @param driver the driver
     * @return the boolean
     */
    public boolean noCredentials(WebDriver driver) {
        return !findElementById(By.id("tableCredentialUrl"), driver) &&
                !findElementById(By.id("tableCredentialUsername"), driver) &&
                !findElementById(By.id("tableCredentialPassword"), driver);
    }

    /**
     * Find element by id boolean.
     *
     * @param locatorKey the locator key
     * @param driver     the driver
     * @return the boolean
     */
    public boolean findElementById(By locatorKey, WebDriver driver) {
        try {
            driver.findElement(locatorKey);

            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * View credential credentials.
     *
     * @return the credentials
     */
    public Credentials viewCredential() {
        String url = wait.until(ExpectedConditions.elementToBeClickable(tableCredUrl)).getText();
        String username = tableCredUsername.getText();
        String password = tableCredPassword.getText();

        return new Credentials(url, username, password);
    }

    /**
     * Create and verify credential.
     *
     * @param url            the url
     * @param username       the username
     * @param password       the password
     * @param credentialPage the credential page
     * @param driver         the driver
     */
    public void createAndVerifyCredential(String url, String username, String password, CredentialPage credentialPage, WebDriver driver) {
        credentialPage.navToCredentialsTab();
        credentialPage.addNewCredential();
        credentialPage.setCredentialUrl(url);
        credentialPage.setCredentialUsername(username);
        credentialPage.setCredentialPassword(password);
        credentialPage.saveCredentialChanges();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
        credentialPage.navToCredentialsTab();
        Credentials credential = credentialPage.viewCredential();
        assertEquals(url, credential.getUrl());
        assertEquals(username, credential.getUserName());
        assertNotEquals(password, credential.getPassword());
    }

}
