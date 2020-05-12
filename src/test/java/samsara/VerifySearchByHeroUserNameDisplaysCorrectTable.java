package samsara;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HeroesPageObjects;
import pageObjects.LoginPageSamsara;
import pageObjects.UserPageObjects;
import pageObjects.WelcomeSamsaraPage;
import resources.BaseClassSamsara;
import resources.DataProviders;

public class VerifySearchByHeroUserNameDisplaysCorrectTable extends BaseClassSamsara {
	public static Logger log = LogManager.getLogger(VerifySearchByHeroUserNameDisplaysCorrectTable.class.getName());

	@BeforeMethod
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get("chrome://settings/clearBrowserData");
		driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
		driver.get(prop.getProperty("url"));
		// driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

	}

	@Test(enabled = false, priority = 0, dataProviderClass = DataProviders.class, dataProvider = "verifyAdminHeroSearchShowsCorrectTableData")
	public void verifyAdminHeroSearchShowsCorrectTable(String heroeName) throws IOException, InterruptedException {

		LoginPageSamsara lps = new LoginPageSamsara(driver);
		lps.openAsAdminPositive();
		WelcomeSamsaraPage wsp = new WelcomeSamsaraPage(driver);
		wsp.heroesTab().click();
		HeroesPageObjects heropo = new HeroesPageObjects(driver);

		assert heropo.searchHeroRow(heroeName) != -1 : "User isn't in table.";
		heropo.searchHeroField().sendKeys(heroeName);
		heropo.searchHeroButton().click();
		assert heropo.searchHeroRow(heroeName) != -1 : "User isn't in table after search.";

	}

	@Test(enabled = false, priority = 1, dataProviderClass = DataProviders.class, dataProvider = "verifyAdminHeroSearchShowsCorrectTableData")
	public void verifyUserHeroSearchShowsCorrectTable(String heroeName) throws IOException, InterruptedException {

		LoginPageSamsara lps = new LoginPageSamsara(driver);
		lps.openAsUserPositive();
		WelcomeSamsaraPage wsp = new WelcomeSamsaraPage(driver);
		wsp.heroesTab().click();
		HeroesPageObjects heropo = new HeroesPageObjects(driver);

		assert heropo.searchHeroRow(heroeName) != -1 : "User isn't in table.";
		heropo.searchHeroField().sendKeys(heroeName);
		heropo.searchHeroButton().click();
		assert heropo.searchHeroRow(heroeName) != -1 : "User isn't in table after search.";

	}

	@Test(enabled = true, priority = 2, dataProviderClass = DataProviders.class, dataProvider = "verifyAdminSearchUsersInHeroeTableShowsCorrectTableData")
	public void verifyAdminSearchUsersInHeroeTableShowsCorrectTable(String user1, String user2)
			throws IOException, InterruptedException {

		LoginPageSamsara lps = new LoginPageSamsara(driver);
		lps.openAsAdminPositive();
		WelcomeSamsaraPage wsp = new WelcomeSamsaraPage(driver);
		wsp.heroesTab().click();
		HeroesPageObjects heropo = new HeroesPageObjects(driver);
		heropo.searchHeroField().sendKeys(user1);
		heropo.searchHeroButton().click();
		System.out.println(heropo.countUsersColumnByName(user1));
//		assert heropo.searchHeroRow(heroeName) != -1 : "User isn't in table.";
//		heropo.searchHeroField().sendKeys(heroeName);
//		heropo.searchHeroButton().click();
//		assert heropo.searchHeroRow(heroeName) != -1 : "User isn't in table after search.";
	}

	@Test(enabled = false, priority = 2, dataProviderClass = DataProviders.class, dataProvider = "verifyAdminCanDeleteUserData")
	public void verifyAdminCanDeleteUser(String userEditedName) throws IOException {

		UserPageObjects userp = new UserPageObjects(driver);
		userp.adminClickOnDeleteIcon(userEditedName);
//		userp.deleteButtonAdmin().click();
		userp.adminClickDeleteUserButon();
		assert userp.searchUserRow(userEditedName) == -1 : "Admin didn't delete the user.";
	}

}
