package samsara;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HeroesPageObjects;
import pageObjects.LoginPageSamsara;
import pageObjects.WelcomeSamsaraPage;
import resources.BaseClassSamsara;
import resources.DataProviders;

public class VerifyUserCanEditHeroNegative extends BaseClassSamsara {
	public static Logger log = LogManager.getLogger(VerifyUserCanEditHeroNegative.class.getName());

	@BeforeTest
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();

	}

	@Test(enabled = true, dataProviderClass = DataProviders.class, dataProvider = "verifyUserCanEditHeroNegativeData")
	public void verifyUserCanEditHeroNegative(String heroeName, String heroeEditedName, int editedLevelNegative, String editedHeroClass)
			throws IOException {
		
		LoginPageSamsara lps = new LoginPageSamsara(driver);
		lps.openAsUserPositive();
		WelcomeSamsaraPage wsp = new WelcomeSamsaraPage(driver);
		wsp.heroesTab().click();
		HeroesPageObjects heroesp = new HeroesPageObjects(driver);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		int userRow = heroesp.searchHeroRow(heroeName);
		String queryEditButtonClick = String.format("$($('#heroes-table tr')[%d]).find('.glyphicon-pencil').click()",
				userRow);
		js.executeScript(queryEditButtonClick);

		heroesp.editHeroName().clear();
		heroesp.editHeroName().sendKeys(heroeEditedName);
		heroesp.editHeroLevel().sendKeys(String.valueOf(editedLevelNegative));
		Select classHero = new Select(heroesp.classEditedHero());
		classHero.selectByValue(editedHeroClass);

		String queriClickOnSubmitEditButton = "$('#editHeroModal').find('.btn-primary').click()";
		js.executeScript(queriClickOnSubmitEditButton);
		assert heroesp.searchHeroRow(heroeName) != -1 : "Own hero is edited with ineligible data(level with number above 80).";
	}

	

}
