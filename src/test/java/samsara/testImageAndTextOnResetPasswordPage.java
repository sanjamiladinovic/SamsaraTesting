package samsara;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import junit.framework.Assert;
import pageObjects.LoginPageSamsara;
import pageObjects.ResetPasswordPage;
import resources.BaseClassSamsara;

public class testImageAndTextOnResetPasswordPage extends BaseClassSamsara {

	public static Logger log = LogManager.getLogger(testImageAndTextOnResetPasswordPage.class.getName());
	// SoftAssert softAssert = new SoftAssert();

	@BeforeMethod
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialised.");
		driver.get(prop.getProperty("url"));
		log.info("Logged in: " + driver.getTitle());

	}

	@Test
	public void checkSamsaraTextTitleOnForgotPassPage() throws InterruptedException {

		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);
		loginPageSamsara.resetPasswordLink().click();
		ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
		Assert.assertEquals("Samsara text title is wrong!", "Samsara",
				resetPasswordPage.backToLoginPageViaSamsaraBrand().getText());
		log.info("Samsara text title is good.");

	}

	@Test
	public void resetPassTextTitleOnForgotPassPage() {

		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);
		loginPageSamsara.resetPasswordLink().click();
		ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
		Assert.assertEquals("Reset Password page text title is wrong!", "Reset Password",
				resetPasswordPage.checkPanelTitle().getText());
		log.info("Reset Password page text title is good.");

	}

	@Test
	public void forgotPassPageLogInInRightCornerText() {
		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);
		loginPageSamsara.resetPasswordLink().click();
		ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
		Assert.assertEquals("Text title Login in up right corner is wrong!", "Log In",
				resetPasswordPage.backToLoginPageViaLoginButtonUpRightCorner().getText());
		log.info("Text title Login in up right corner is good.");

	}

	@Test
	public void checkTextEnterUsername() {
		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);
		loginPageSamsara.resetPasswordLink().click();
		ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
		Assert.assertEquals("Question title is wrong!", "Enter username to get your security question.",
				resetPasswordPage.checkQuestionTitle().getText());
		log.info("Question title is good.");

	}

	@Test
	public void checkIsTheImagePresent() {
		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);
		loginPageSamsara.resetPasswordLink().click();
		ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);

		Assert.assertTrue("Immage isn't displayed.", resetPasswordPage.checkCatImage().isDisplayed());
		log.info("Immage is displayed.");
	}

}
