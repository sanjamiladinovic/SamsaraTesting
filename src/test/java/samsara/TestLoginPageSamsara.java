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

public class TestLoginPageSamsara extends BaseClassSamsara {

	public static Logger log = LogManager.getLogger(TestLoginPageSamsara.class.getName());

	@BeforeMethod
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialised.");

	}

	@Test(dataProvider = "getData")
	// Login - positive/negative
	public void testLogin(String username, String password) throws InterruptedException {
		driver.get(prop.getProperty("url"));
		log.info("Logged in: " + driver.getTitle());

		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);

		loginPageSamsara.usernameField().sendKeys(username);
		loginPageSamsara.passwordField().sendKeys(password);
		loginPageSamsara.loginButton().click();

		WelcomeSamsaraPage welcomeSamsaraPage = new WelcomeSamsaraPage(driver);

		if (loginPageSamsara.loginWarnMessage().isDisplayed()) {

			log.debug(loginPageSamsara.loginWarnMessage().getText());

		}

		else {
			// Nesto nece da pronadje elemenat.
			System.out.println(welcomeSamsaraPage.welcomeMessageBoardPage().getText());
			// Thread.sleep(3000);
		}

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
	public void resetPassword() {
		driver.get(prop.getProperty("url"));
		log.info("Logged in: " + driver.getTitle());
		LoginPageSamsara loginPageSamsara = new LoginPageSamsara(driver);
		loginPageSamsara.resetPasswordLink().click();

	}

	@AfterMethod
	public void closeTheBrowser() {
		log.info(
				"-------------------------------------------------------------------------------------------------------");
		driver.close();
		driver = null;
	}

	@DataProvider()

	public Object[][] getData() {

		Object[][] data = new Object[10][2];
		data[0][0] = "user";
		data[0][1] = "password";

		data[1][0] = "user";
		data[1][1] = "password1";

		data[2][0] = "user1";
		data[2][1] = "password";

		data[3][0] = "";
		data[3][1] = "";

		data[4][0] = "";
		data[4][1] = "password";

		data[5][0] = "user";
		data[5][1] = "";

		data[6][0] = "00000000";
		data[6][1] = "password";

		data[7][0] = "user";
		data[7][1] = "passwxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxord";

		data[8][0] = "trshryhjuryhrgrejdhajfdgnjdkgdlkgd/klgjdkhj/lsrkjglesgjkldgjm/klsjgklesrgj";
		data[8][1] = "password";

		data[9][0] = ".#@#";
		data[9][1] = "password";

		return data;
	}

}
