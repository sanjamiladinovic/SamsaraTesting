package samsara;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPageSamsara;
import pageObjects.NewAccountPageObjects;
import resources.BaseClassSamsara;
import resources.DataProviders;

public class TestRegistratingNewAccount extends BaseClassSamsara {

	public static Logger log = LogManager.getLogger(TestRegistratingNewAccount.class.getName());

	@BeforeMethod
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialised.");
		driver.get(prop.getProperty("url"));
		log.info("Logged in: " + driver.getTitle());

	}
//IZBACILI SU POLJE CODE
	@Test(enabled = false, dataProviderClass = DataProviders.class, dataProvider = "getDataTestLoginCodeWarningMessage")

	public void testLoginCodeWarningMessage(String code1) throws InterruptedException {

		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);
		loginPageSamsara.createAccountLink().click();

		NewAccountPageObjects newAccountPageObjects = new NewAccountPageObjects(driver);

		Assert.assertTrue(
				newAccountPageObjects.accountLandingPageMessage().getText().equalsIgnoreCase("Resister new account."),
				"Link 'Create account' works.");

		newAccountPageObjects.registrationFieldCode().sendKeys(code1);

		if (code1.length() > 0) {
			while (code1.length() > 0) {
				newAccountPageObjects.registrationFieldCode().sendKeys(Keys.BACK_SPACE);
				code1 = code1.substring(0, code1.length() - 1);
			}

			System.out.println(newAccountPageObjects.registrationFieldCodeMessage().getText());
			Assert.assertEquals(newAccountPageObjects.registrationFieldCodeMessage().getText(),
					"Bla bla incorect first name.", "Poruka se ili nije pojavila ili nije dobro ispisana.");
			log.info("Warning message - Registration code failed.");
		}

	}

	@Test(enabled = true, priority = 2)
	public void backToLoginPageViaSamsaraLinkOnCreateAccountWhenWeAreNotLogged() throws InterruptedException {

		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);
		loginPageSamsara.createAccountLink().click();

		NewAccountPageObjects newAccountPageObjects = new NewAccountPageObjects(driver);

		newAccountPageObjects.samsaraLinkBack().click();
		Assert.assertTrue(loginPageSamsara.usernameField().isDisplayed(),
				"Link Samsara from page for Account Creation(via link from login page) doesn't works..(if we are not logged before)");

		log.debug(
				"Link Samsara from page for Account Creation(via link from login page) works (if we are not logged before)");

		System.out.println("Vraceno preko Samsara na Login stranu...(if we are not logged before)");

		log.error("Link Samsara on page for Account Creation(via link from loginpage) doesn't works");

	}

	@Test(enabled = true, priority = 3)
	public void registerNewAccountTextTitleCheck() {

		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);
		loginPageSamsara.createAccountLink().click();

		NewAccountPageObjects newAccountPageObjects = new NewAccountPageObjects(driver);

		Assert.assertEquals(newAccountPageObjects.accountLandingPageMessage().getText(), "Register new account.");
		log.info("Title is correct.");

	}

	@Test(enabled = true, priority = 1)
	public void backToLoginPageViaLoginButtonFromAreateAccountIfWeAreNotLogged() throws InterruptedException {

		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);
		loginPageSamsara.createAccountLink().click();

		NewAccountPageObjects newAccountPageObjects = new NewAccountPageObjects(driver);

		Assert.assertEquals(newAccountPageObjects.accountLandingPageMessage().getText(), "Resister new account.");

		newAccountPageObjects.loginButtonBack().click();

		Assert.assertTrue(loginPageSamsara.usernameField().isDisplayed(),
				"Link LoginButton for Account Creation(right in header) doesn't works...(if we are not logged before)");

		log.debug(
				"Link LoginButton on page for Account Creation(right in header) works..(if we are not logged before)");
		System.out.println("Vraceno preko LoginButton-a na Login stranu...(if we are not logged before)");

	}

	@Test(priority = 4, dataProviderClass = DataProviders.class, dataProvider = "getDataTestRegisterNewAccount")
	public void testRegisterNewAccount(String username, String firstName, String lastName, String about,
			String secretQuestion, String secretAnswer, String password, String rePassword)
			throws InterruptedException {

		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);
		loginPageSamsara.createAccountLink().click();

		NewAccountPageObjects newAccountPageObjects = new NewAccountPageObjects(driver);

		Assert.assertEquals(newAccountPageObjects.accountLandingPageMessage().getText(), "Resister new account.");

		newAccountPageObjects.usernameField().sendKeys(username);
		newAccountPageObjects.firstNameFiel().sendKeys(firstName);
		newAccountPageObjects.lastNameFiel().sendKeys(lastName);
		newAccountPageObjects.aboutfield().sendKeys(about);
		newAccountPageObjects.secretQuestionField().sendKeys(secretQuestion);
		newAccountPageObjects.secretAnswerField().sendKeys(secretAnswer);
		newAccountPageObjects.passwordField().sendKeys(password);
		newAccountPageObjects.rePasswordField().sendKeys(rePassword);
		Assert.assertTrue(newAccountPageObjects.signUpButton().isEnabled(), "Button SignUp is disabled.");

		System.out.println("Button SignUp is: " + newAccountPageObjects.signUpButton().isEnabled());

		newAccountPageObjects.signUpButton().click();

		log.fatal("Button SignUp is enabled.");

	}

}
