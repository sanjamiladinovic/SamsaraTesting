package samsara;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ResetPasswordPage;
import resources.BaseClassSamsara;

public class TestResetPassword extends BaseClassSamsara {

	public static Logger log = LogManager.getLogger(TestResetPassword.class.getName());

	@BeforeMethod
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialised.");

	}

	@Test(dataProvider = "getData")
	public void checkTehtOnPage(String username, String answer, String newPassword, String confirmPassword) {
		driver.get(prop.getProperty("url"));
		log.info("Logged in: " + driver.getTitle());

		ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
		resetPasswordPage.enterUsernameField().sendKeys("username");
		resetPasswordPage.enterAnswer().sendKeys("answer");
		resetPasswordPage.enterAnswer().sendKeys("newPassword");
		resetPasswordPage.enterAnswer().sendKeys("confirmPassword");

	}

	@DataProvider()

	public Object[][] getData() {

		Object[][] data = new Object[5][4];
		data[0][0] = "user";
		data[0][1] = "bla bla";
		data[0][2] = "password";
		data[0][3] = "password";

		data[1][0] = "user";
		data[1][1] = "bla bla";
		data[1][2] = "Password1";
		data[1][3] = "Password1";
		
		data[2][0] = "sanja";
		data[2][1] = "444";
		data[2][2] = "Password2";
		data[2][3] = "Password1";
		
		data[3][0] = "igor";
		data[3][1] = "null";
		data[3][2] = "Password3";
		data[3][3] = "Password3";
		
		data[4][0] = "marko";
		data[4][1] = "da da da";
		data[4][2] = "";
		data[4][3] = "";
		return data;
		
	}
}
