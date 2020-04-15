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
	SoftAssert softAssert = new SoftAssert();

	@BeforeMethod
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialised.");

	}

	@Test
	public void checkText() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		log.info("Logged in: " + driver.getTitle());
		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);
		loginPageSamsara.resetPasswordLink().click();
		ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);

		if (resetPasswordPage.backToLoginPageViaSamsaraBrand().getText().equalsIgnoreCase("Samsara"))
			log.info("Samsara text title is good.");
		else
			log.info("Samsara text title is wrong!");

		if (resetPasswordPage.checkPanelTitle().getText().equalsIgnoreCase("Reset Password"))
			log.info("Reset Password page text title is good.");
		else
			log.error("Reset Password page text title is wrong!");
//Kako istestirati tekst u dugmetu???
		if (resetPasswordPage.backToLoginPageViaLoginButtonUpRightCorner().getText().contains("Log In"))
			log.info("Text title Login in up right corner is good.");
		else
			log.info("Text title Login in up right corner is wrong!");

//		softAssert.assertEquals(resetPasswordPage.checkPanelTitle().getText(), "Reset Password");
//
//		log.info("Reset Password page text title is good!");

		if (resetPasswordPage.checkQuestionTitle().getText()
				.equalsIgnoreCase("Enter username to get your security question.")) {
			log.info("Question title is good.");

		}

		else {

			log.error("Question title is wrong!");
			System.out.println("Question title is wrong!");
		}

		if (resetPasswordPage.checkCatImage().isDisplayed())
			log.info("Immage is displayed.");
		else {
			log.error("Immage is displayed.");
			System.out.println("Slika nije prikazana.");
		}
//		softAssert.assertEquals(resetPasswordPage.checkQuestionTitle().getText(),
//				"Enter username to get your security question.");

		// Kako da napravim text za popup/pitanje koji ne iskace nakon unosa usera a
		// potrebno je za sledece polje-za odgovor
	}

	@AfterMethod
	public void closeTheBrowser() {

		driver.close();
		driver = null;
		// softAssert.assertAll();//da mi gore proveri sve Assert-ove ali da mi ne
		// prekine izvsavanje testa u slucaju greske, vec na kraju da proveri
	}

}
