package samsara;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.LoginPageSamsara;
import pageObjects.ResetPasswordPage;
import resources.BaseClassSamsara;
import resources.DataProviders;

public class TestResetPassword extends BaseClassSamsara {

	public static Logger log = LogManager.getLogger(TestResetPassword.class.getName());

	@BeforeMethod
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialised.");
		driver.get(prop.getProperty("url"));
		log.info("Logged in: " + driver.getTitle());

	}

	@Test(dataProviderClass = DataProviders.class, dataProvider = "getDataTestResetPassword")
	public void checkResetPassOption(String username, String answer, String newPassword, String confirmPassword) {
		LoginPageSamsara lps = new LoginPageSamsara(driver);
		lps.resetPasswordLink().click();
		ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
		resetPasswordPage.enterUsernameField().sendKeys(username);
		resetPasswordPage.enterAnswer().sendKeys(answer);
		resetPasswordPage.enterAnswer().sendKeys(newPassword);
		resetPasswordPage.enterAnswer().sendKeys(confirmPassword);
		resetPasswordPage.resetPasswordButton().click();
		Assert.assertTrue("ResetPasswordButton is disabled.", resetPasswordPage.resetPasswordButton().isEnabled());
		log.info("ResetPasswordButton is enabled.");

	}
	
	@Test
	public void  returnToLoginPageLink() {
		LoginPageSamsara lps = new LoginPageSamsara(driver);
		lps.resetPasswordLink().click();
		ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
		resetPasswordPage.backToLoginPageViaLink().click();
		Assert.assertTrue("We are not returned to loginPage via Login Link.", lps.usernameField().isEnabled());
		log.info("We are returned to loginPage via Login Link.");
	}

}
