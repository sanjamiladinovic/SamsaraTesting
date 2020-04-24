package samsara;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPageSamsara;
import pageObjects.WelcomeSamsaraPage;
import resources.BaseClassSamsara;
import resources.DataProviders;

public class TestLoginPageSamsara extends BaseClassSamsara {

	public static Logger log = LogManager.getLogger(TestLoginPageSamsara.class.getName());

	@BeforeMethod
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialised.");
		driver.get(prop.getProperty("url"));
		log.info("Logged in: " + driver.getTitle());
	}

	@Test(dataProviderClass = DataProviders.class, dataProvider = "getDataUsernamePassword")
	// Login - positive/negative
	public void testLogin(String username, String password) throws InterruptedException {

		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);

		loginPageSamsara.usernameField().sendKeys(username);
		loginPageSamsara.passwordField().sendKeys(password);
		loginPageSamsara.loginButton().click();

		WelcomeSamsaraPage welcomeSamsaraPage = new WelcomeSamsaraPage(driver);
		Assert.assertTrue(welcomeSamsaraPage.welcomeMessageBoardPage().getText()
				.equalsIgnoreCase("Hello, and welcome to our gamers page!"), "Nismo se uspesno ulogovali u app.");

		log.info("Success login.");
	}

	@Test

	public void imageDisplaying() {

		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);

		if (loginPageSamsara.loginImage().isDisplayed()) {
			System.out.println("Image is displayed in login page.");
			log.debug("Image is displayed in login page.");
		}

	}

	@Test
	public void resetPasswordOption() {

		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);
		loginPageSamsara.resetPasswordLink().click();
		log.info("Success click on resetPassvordLink.");

	}

}
