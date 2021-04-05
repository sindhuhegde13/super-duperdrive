package com.nanodegree.superduperdrive;

import com.nanodegree.superduperdrive.model.Notes;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The type Note page.
 */
public class NotePage {

    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    @FindBy(id = "add-new-note")
    private WebElement addNewNote;

    @FindBy(id = "note-title")
    private WebElement snoteTitle;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "note-description")
    private WebElement snoteDescription;

    @FindBy(id = "save-note")
    private WebElement saveChanges;

    @FindBy(id = "table-notetitle")
    private WebElement tableNoteTitle;

    @FindBy(id = "table-notedescription")
    private WebElement tableNoteDescription;

    @FindBy(id = "edit-note")
    private WebElement btnEditNote;

    @FindBy(id = "note-description")
    private WebElement smodifyNoteDescription;

    @FindBy(id = "delete-note")
    private WebElement btnDeleteNote;

    private final JavascriptExecutor js;

    private final WebDriverWait wait;

    /**
     * Instantiates a new Note page.
     *
     * @param webDriver the web driver
     */
    public NotePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        js = (JavascriptExecutor) webDriver;
        wait = new WebDriverWait(webDriver, 500);
    }

    /**
     * Logout.
     */
    public void logout() {
        js.executeScript("arguments[0].click();", logoutButton);
    }

    /**
     * Edit note.
     */
    public void editNote() {
        js.executeScript("arguments[0].click();", btnEditNote);
    }

    /**
     * Delete note.
     */
    public void deleteNote() {
        js.executeScript("arguments[0].click();", btnDeleteNote);
    }

    /**
     * Add new note.
     */
    public void addNewNote() {
        js.executeScript("arguments[0].click();", addNewNote);
    }

    /**
     * Sets note title.
     *
     * @param noteTitle the note title
     */
    public void setNoteTitle(String noteTitle) {
        js.executeScript("arguments[0].value='" + noteTitle + "';", snoteTitle);
    }

    /**
     * Modify note title.
     *
     * @param newNoteTitle the new note title
     */
    public void modifyNoteTitle(String newNoteTitle) {
        wait.until(ExpectedConditions.elementToBeClickable(snoteTitle)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(snoteTitle)).sendKeys(newNoteTitle);
    }

    /**
     * Modify note description.
     *
     * @param newNoteDescription the new note description
     */
    public void modifyNoteDescription(String newNoteDescription) {
        wait.until(ExpectedConditions.elementToBeClickable(smodifyNoteDescription)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(smodifyNoteDescription)).sendKeys(newNoteDescription);
    }

    /**
     * Nav to notes tab.
     */
    public void navToNotesTab() {
        js.executeScript("arguments[0].click();", navNotesTab);
    }

    /**
     * Sets note description.
     *
     * @param noteDescription the note description
     */
    public void setNoteDescription(String noteDescription) {
        js.executeScript("arguments[0].value='"+ noteDescription +"';", snoteDescription);
    }

    /**
     * Save note changes.
     */
    public void saveNoteChanges() {
        js.executeScript("arguments[0].click();", saveChanges);
    }

    /**
     * No notes boolean.
     *
     * @param driver the driver
     * @return the boolean
     */
    public boolean noNotes(WebDriver driver) {
        return !findElementByKey(By.id("table-notetitle"), driver) && !findElementByKey(By.id("table-notedescription"), driver);
    }

    /**
     * Find element by key boolean.
     *
     * @param element the locator key
     * @param driver     the driver
     * @return the boolean
     */
    public boolean findElementByKey(By element, WebDriver driver) {
        try {
            driver.findElement(element);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * View first note notes.
     *
     * @return the notes
     */
    public Notes viewFirstNote() {
        String title = wait.until(ExpectedConditions.elementToBeClickable(tableNoteTitle)).getText();
        String description = tableNoteDescription.getText();

        return new Notes(title, description);
    }

    /**
     * Create note.
     *
     * @param noteTitle       the note title
     * @param noteDescription the note description
     * @param notePage        the note page
     * @param driver          the driver
     */
    public void createNote(String noteTitle, String noteDescription, NotePage notePage, WebDriver driver) {
        notePage.navToNotesTab();
        notePage.addNewNote();
        notePage.setNoteTitle(noteTitle);
        notePage.setNoteDescription(noteDescription);
        notePage.saveNoteChanges();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
        notePage.navToNotesTab();
    }

    /**
     * Delete note.
     *
     * @param notePage  the note page
     * @param webDriver the web driver
     */
    public void deleteNote(NotePage notePage, WebDriver webDriver) {
        notePage.deleteNote();
        ResultPage resultPage = new ResultPage(webDriver);
        resultPage.clickOk();
    }
}
