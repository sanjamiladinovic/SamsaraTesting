package samsara;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.LoginPageSamsara;
import pageObjects.WelcomeSamsaraPage;
import resources.BaseClassSamsara;

public class VerifyHomePageTest extends BaseClassSamsara {
	public static Logger log = LogManager.getLogger(VerifyHomePageTest.class.getName());

	@BeforeMethod
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();

	}

	@Test
	public void verifyHomePage() throws IOException {

		LoginPageSamsara lps = new LoginPageSamsara(driver);
		lps.openAsUserPositive();
		WelcomeSamsaraPage wsp = new WelcomeSamsaraPage(driver);
		assert wsp.welcomeMessageBoardPage().isDisplayed() : "Welcome screen isn't displayed​.";
		assert wsp.startTestingButtonM().isDisplayed() : "'Start Testing!' button isn't displayed.";
		assert wsp.shareWithFriendsM().isDisplayed() : "'Share with friends' button is displayed​";

	}

}
