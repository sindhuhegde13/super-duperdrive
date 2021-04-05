package com.nanodegree.superduperdrive;

import com.nanodegree.superduperdrive.model.Credentials;
import com.nanodegree.superduperdrive.model.Notes;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Superduperdrive application tests.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SuperduperdriveApplicationTests {

	/**
	 * The Port.
	 */
	@LocalServerPort
	public int port;

	/**
	 * The constant driver.
	 */
	public static WebDriver driver;

	private WebDriverWait webDriverWait;

	/**
	 * The Base url.
	 */
	public String baseURL;

	/**
	 * Before all.
	 */
	@BeforeAll
	public static void beforeAll() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

	}

	/**
	 * After all.
	 */
	@AfterAll
	public static void afterAll() {
		driver.quit();
		driver = null;
	}

	/**
	 * Before each.
	 */
	@BeforeEach
	public void beforeEach() {
		baseURL = baseURL = "http://localhost:" + port;
		this.webDriverWait = new WebDriverWait (driver, 1000);
	}

	/**
	 *  test that verifies that an unauthorized user can only access the login and signup pages.
	 */
	@Test
	@Order(1)
	public void testAccessUser() {
		driver.get("http://localhost:" + this.port + "/login");
		assertEquals("Login", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/signup");
		assertEquals("Sign Up", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/home");
		assertEquals("Login", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/files");
		assertEquals("Login", driver.getTitle());
	}

	/**
	 * Test test that signs up a new user, logs in, verifies that the home page is accessible, logs out, and verifies that the home page is no longer accessible.
	 */
	@Test
	@Order(2)
	public void testSignUpLogOut() {
		driver.get("http://localhost:" + this.port + "/signup");
		assertEquals("Sign Up", driver.getTitle());
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("Test","User","test","password123");
		driver.get("http://localhost:" + this.port + "/login");
		assertEquals("Login", driver.getTitle());
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("test","password123");
		NotePage page = new NotePage(driver);
		page.logout();
		driver.get("http://localhost:" + this.port + "/home");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assertEquals("Login", driver.getTitle());
	}

	/**
	 * Test user signup login submit.
	 */
	@Test
	@Order(3)
	public void testUserSignupLoginSubmit() {
		String username = "testUser";
		String password = "password123";
		driver.get(baseURL + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("Test", "User", username, password);
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
	}

	/**
	 * Test that creates a note, and verifies it is displayed.
	 */
	@Test
	@Order(4)
	public void testCreateAndDisplayNote() {
		String noteTitle = "Test Note";
		String noteDescription = "This is test note";
		testUserSignupLoginSubmit();
		NotePage notePage = new NotePage(driver);
		notePage.createNote(noteTitle,noteDescription,notePage,driver);
		notePage.navToNotesTab();
		Notes note = notePage.viewFirstNote();
		Assertions.assertEquals(noteTitle, note.getNoteTitle());
		Assertions.assertEquals(noteDescription, note.getNoteDescription());
		notePage.deleteNote(notePage,driver);
		notePage.logout();
	}

	/**
	 * Test that edits an existing note and verifies that the changes are displayed.
	 */
	@Test
	@Order(5)
	public void testNoteModify() {
		String noteTitle = "Test Note";
		String noteDescription = "This is test note";
		testUserSignupLoginSubmit();
		NotePage notePage = new NotePage(driver);
		notePage.createNote(noteTitle, noteDescription, notePage, driver);
		notePage.navToNotesTab();
		notePage.editNote();
		String modTitle = "Test Note 2";
		notePage.modifyNoteTitle(modTitle);
		String modDescription = "This is test note 2.";
		notePage.modifyNoteDescription(modDescription);
		notePage.saveNoteChanges();
		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickOk();
		notePage.navToNotesTab();
		Notes firstNote = notePage.viewFirstNote();
		assertEquals(modTitle, firstNote.getNoteTitle());
		assertEquals(modDescription, firstNote.getNoteDescription());
		notePage.deleteNote();
	}

	/**
	 * Test that edits an existing note and verifies that the changes are displayed.
	 */
	@Test
	@Order(6)
	public void testNoteDelete() {
		String noteTitle = "Test Note 3";
		String noteDescription = "This is test note 3";
		testUserSignupLoginSubmit();
		NotePage notePage = new NotePage(driver);
		notePage.createNote(noteTitle, noteDescription, notePage, driver);
		notePage.navToNotesTab();
		Assertions.assertFalse(notePage.noNotes(driver));
		notePage.deleteNote();
		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickOk();
		assertTrue(notePage.noNotes(driver));
		notePage.logout();
	}

	/**
	 * Test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed
	 * password is encrypted.
	 */
	@Test
	@Order(7)
	public void testCreateCredential() {
		testUserSignupLoginSubmit();
		CredentialPage credentialPage  =  new CredentialPage(driver);
		credentialPage.createAndVerifyCredential("http://testwebsite.com","test","password1",credentialPage,driver);
		credentialPage.deleteCredential();
		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickOk();
		credentialPage.logout();
	}


	/**
	 * Test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the
	 * credentials, and verifies that the changes are displayed.
	 */
	@Test
	@Order(8)
	public void testModifyCredential() {
		testUserSignupLoginSubmit();
		CredentialPage credentialPage = new CredentialPage(driver);
		credentialPage.createAndVerifyCredential("http://testwebsite.com","test","password1",credentialPage,driver);
		Credentials firstCredential = credentialPage.viewCredential();
		String credentialEncPassword = firstCredential.getPassword();
		credentialPage.editCredential();
		credentialPage.modifyCredUrl("http://testwebsitenew.com");
		credentialPage.modifyCredUsername("test1");
		credentialPage.modifyCredPassword("password2");
		credentialPage.saveCredentialChanges();
		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickOk();
		credentialPage.navToCredentialsTab();
		Credentials modCred = credentialPage.viewCredential();
		assertEquals("http://testwebsitenew.com", modCred.getUrl());
		assertEquals("test1", modCred.getUserName());
		String modCredPassword = modCred.getPassword();
		assertNotEquals("password2", modCredPassword);
		assertNotEquals(credentialEncPassword, modCredPassword);
		credentialPage.deleteCredential();
		resultPage.clickOk();
		credentialPage.logout();
	}

	/**
	 * Test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.
	 */
	@Test
	@Order(9)
	public void testDeleteCredential() {
		testUserSignupLoginSubmit();
		CredentialPage credentialPage = new CredentialPage(driver);
		credentialPage.createAndVerifyCredential("http://testwebsite.com","test","password1",credentialPage,driver);
		assertFalse(credentialPage.noCredentials(driver));
		credentialPage.deleteCredential();
		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickOk();
		credentialPage.navToCredentialsTab();
		assertTrue(credentialPage.noCredentials(driver));
		credentialPage.logout();
	}
}
