package samsara;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HeroesPageObjects;
import pageObjects.LoginPageSamsara;
import pageObjects.WelcomeSamsaraPage;
import resources.BaseClassSamsara;
import resources.DataProviders;

public class VerifyUserCanCreateEditDeleteHero extends BaseClassSamsara {
	public static Logger log = LogManager.getLogger(VerifyUserCanCreateEditDeleteHero.class.getName());

	@BeforeClass
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();

	}

	@AfterClass(alwaysRun = true)
	public void close() {
		driver.close();
		driver = null;

	}

	@Test(enabled = true, priority = 0, dataProviderClass = DataProviders.class, dataProvider = "verifyUserCanCreateHeroData")
	public void verifyUserCanCreateHero(String heroeName, int level, String heroClass) throws IOException {

		LoginPageSamsara lps = new LoginPageSamsara(driver);
		lps.openAsUserPositive();
		WelcomeSamsaraPage wsp = new WelcomeSamsaraPage(driver);
		wsp.heroesTab().click();
		HeroesPageObjects heroesp = new HeroesPageObjects(driver);
		heroesp.addNewHero().click();
		heroesp.nameFieldNewHero().sendKeys(heroeName);
		heroesp.levelFeldNewHero().sendKeys(String.valueOf(level));
		heroesp.selectClassNewHero().click();
		Select classHero = new Select(heroesp.selectClassNewHero());
		classHero.selectByValue(heroClass);
		heroesp.saveButton().click();

		assert heroesp.searchHeroRow(heroeName) != -1 : "Hero isn't created.";

	}

	@Test(enabled = true, priority = 1, dataProviderClass = DataProviders.class, dataProvider = "verifyUserCanEditHeroData")
	public void verifyUserCanEditHero(String heroeName, String heroeEditedName, int editedLevel, String editedHeroClass)
			throws IOException {

		HeroesPageObjects heroesp = new HeroesPageObjects(driver);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		int heroRow = heroesp.searchHeroRow(heroeName);
		String queryEditButtonClick = String.format("$($('#heroes-table tr')[%d]).find('.glyphicon-pencil').click()",
				heroRow);
		js.executeScript(queryEditButtonClick);

		heroesp.editHeroName().clear();
		heroesp.editHeroName().sendKeys(heroeEditedName);
		heroesp.editHeroLevel().clear();
		heroesp.editHeroLevel().sendKeys(String.valueOf(editedLevel));
		Select classHero = new Select(heroesp.classEditedHero());
		classHero.selectByValue(editedHeroClass);

		String queriClickOnSubmitEditButton = "$('#editHeroModal').find('.btn-primary').click()";
		js.executeScript(queriClickOnSubmitEditButton);
		assert heroesp.searchHeroRow(heroeEditedName) != -1 : "Own hero isn't edited.";
	}

	@Test(enabled = true, priority = 2, dataProviderClass = DataProviders.class, dataProvider = "verifyUserCanDeleteOwnHeroData")
	public void verifyUserCanDeleteOwnHero(String heroeEditedName) throws IOException {

		HeroesPageObjects heroesp = new HeroesPageObjects(driver);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		int userRow = heroesp.searchHeroRow(heroeEditedName);
		String queryTrashElClick = String.format("$($('#heroes-table tr')[%d]).find('.glyphicon-trash').click()",
				userRow);
		js.executeScript(queryTrashElClick);
		String queriClickOnDeleteButton = "$('.modal-footer').find('.btn-danger').click()";
		js.executeScript(queriClickOnDeleteButton);
		assert heroesp.searchHeroRow(heroeEditedName) == -1 : "Own hero isn't deleted.";
	}

	@Test(enabled = true, priority = 3, dataProviderClass = DataProviders.class, dataProvider = "verifyUserCanDeleteDoesntOwnHeroData")
	public void verifyUserCanDeleteDoesntOwnHeroNegative(String doesntOwnedHero) throws IOException {

		HeroesPageObjects heroesp = new HeroesPageObjects(driver);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		int userRow = heroesp.searchHeroRow(doesntOwnedHero);
		String queryTrashElClick = String.format("$($('#heroes-table tr')[%d]).find('.glyphicon-trash').click()",
				userRow);
		js.executeScript(queryTrashElClick);
		String queriClickOnDeleteButton = "$('.modal-footer').find('.btn-danger').click()";
		js.executeScript(queriClickOnDeleteButton);
		assert heroesp.searchHeroRow(doesntOwnedHero) != -1 : "Doesn't Owned Hero is deleted.";
	}

}
