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

public class TestRegistratingNewAccount extends BaseClassSamsara {

	public static Logger log = LogManager.getLogger(TestRegistratingNewAccount.class.getName());

	@BeforeMethod
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialised.");

	}

	@Test(dataProvider = "getData1") // Mucila sam se da spojim ovaj deo sa unosom ostalih polja, koja bi se unosila
										// ako se ne obrise polje kod, pa sam napravila izdvojeni test za taj slucaj

	public void testLoginCodeWarningMessage(String code1) throws InterruptedException {
		driver.get(prop.getProperty("url"));
		log.info("Logged in: " + driver.getTitle());
		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);
		loginPageSamsara.createAccountLink().click();

		NewAccountPageObjects newAccountPageObjects = new NewAccountPageObjects(driver);

		Assert.assertEquals(newAccountPageObjects.accountLandingPageMessage().getText(), "Resister new account.");

		newAccountPageObjects.registrationFieldCode().sendKeys(code1);

		if (code1.length() > 0) {
			while (code1.length() > 0) {
				newAccountPageObjects.registrationFieldCode().sendKeys(Keys.BACK_SPACE);
				code1 = code1.substring(0, code1.length() - 1);
			}
			System.out.println(newAccountPageObjects.registrationFieldCodeMessage().getText());
			log.info("Warning message - Registration code failed.");
		}

	}
	@Test
	public void backToLoginPageViaSamsaraLinkOnCreateAccount() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		log.info("Logged in: " + driver.getTitle());
		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);
		loginPageSamsara.createAccountLink().click();

		NewAccountPageObjects newAccountPageObjects = new NewAccountPageObjects(driver);

		Assert.assertEquals(newAccountPageObjects.accountLandingPageMessage().getText(), "Register new account.");

		newAccountPageObjects.samsaraLinkBack().click();
		if(loginPageSamsara.usernameField().isDisplayed()) {
			log.debug("Link Samsara on page for Account Creation(via link from loginpage) works");
			System.out.println("Vraceno preko Samsara na Login stranu");
		}
		else
			log.error("Link Samsara on page for Account Creation(via link from loginpage) doesn't works");
		
		
		
	}
	@Test
	public void backOnLoginPageViaLoginButtonFromAreateAccount() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		log.info("Logged in: " + driver.getTitle());
		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);
		loginPageSamsara.createAccountLink().click();

		NewAccountPageObjects newAccountPageObjects = new NewAccountPageObjects(driver);

		Assert.assertEquals(newAccountPageObjects.accountLandingPageMessage().getText(), "Resister new account.");

		newAccountPageObjects.loginButtonBack().click();
		if(loginPageSamsara.usernameField().isDisplayed()) {
			log.debug("Link LoginButton on page for Account Creation(right in header) works");
			System.out.println("Vraceno preko LoginButton-a na Login stranu");
		}
		else
			log.error("Link LoginButton on page for Account Creation(right in header) doesn't works");
		
		
		
	}

	@DataProvider
	public Object[] getData1() {

		Object[] data1 = new Object[3];
		data1[0] = "";
		data1[1] = "55555";
		data1[2] = "jdgjgdj";
		return data1;
	}

	@Test(dataProvider = "getData")
	public void testLoginPositiveCases(String code, String username, String firstName, String lastName, String about,
			String secretQuestion, String secretAnswer, String password, String rePassword)
			throws InterruptedException {

		driver.get(prop.getProperty("url"));
		log.info("Logged in: " + driver.getTitle());
		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);
		loginPageSamsara.createAccountLink().click();

		NewAccountPageObjects newAccountPageObjects = new NewAccountPageObjects(driver);

		Assert.assertEquals(newAccountPageObjects.accountLandingPageMessage().getText(), "Resister new account.");

		newAccountPageObjects.registrationFieldCode().sendKeys(code);
		newAccountPageObjects.usernameField().sendKeys(username);
		newAccountPageObjects.firstNameFiel().sendKeys(firstName);
		newAccountPageObjects.lastNameFiel().sendKeys(lastName);
		newAccountPageObjects.aboutfield().sendKeys(about);
		newAccountPageObjects.secretQuestionField().sendKeys(secretQuestion);
		newAccountPageObjects.secretAnswerField().sendKeys(secretAnswer);
		newAccountPageObjects.passwordField().sendKeys(password);
		newAccountPageObjects.rePasswordField().sendKeys(rePassword);

		if (newAccountPageObjects.signUpButton().isEnabled()) {
			System.out.println("Button SignUp is: " + newAccountPageObjects.signUpButton().isEnabled());
			newAccountPageObjects.signUpButton().click();
		} else {
			log.fatal("SubmitButton is disabled.");
		}
	}

	@DataProvider()
	public Object[][] getData() {

		Object[][] data = new Object[2][9];

		data[0][0] = "sfsfs";
		data[0][1] = "user87";
		data[0][2] = "Test Name";
		data[0][3] = "Test Last Name";
		data[0][4] = "Nothing";
		data[0][5] = "First teacher?";
		data[0][6] = "Secret1";
		data[0][7] = "User87";
		data[0][8] = "user87";

		data[1][0] = "54125";
		data[1][1] = "";
		data[1][2] = "name88";
		data[1][3] = "lastname88";
		data[1][4] = "everything";
		data[1][5] = "Age?";
		data[1][6] = "99";
		data[1][7] = "Ggg88";
		data[1][8] = "Ggg88";

		return data;
	}

	@AfterMethod
	public void closeTheBrowser() {
		log.info(
				"-------------------------------------------------------------------------------------------------------");
		driver.close();
		driver = null;
	}

}
