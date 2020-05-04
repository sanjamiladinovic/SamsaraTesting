package samsara;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HeroesPageObjects;
import pageObjects.LoginPageSamsara;
import pageObjects.UserPageObjects;
import pageObjects.WelcomeSamsaraPage;
import resources.BaseClassSamsara;
import resources.DataProviders;

public class VerifyAdminCanCreateEditDeleteUser extends BaseClassSamsara {
	public static Logger log = LogManager.getLogger(VerifyAdminCanCreateEditDeleteUser.class.getName());

	@BeforeClass
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get("chrome://settings/clearBrowserData");
		driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
		driver.get(prop.getProperty("url"));
		// driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

	}

	@AfterClass(alwaysRun = true)
	public void close() {
		driver.close();
		driver = null;

	}

	@Test(enabled = true, priority = 0, dataProviderClass = DataProviders.class, dataProvider = "verifyAdminCanCreateUserData")
	public void verifyAdminCanCreateUser(String userName, String firstName, String lastName, String about,
			String secretQuestion, String secretAnswer, String password) throws IOException, InterruptedException {

		LoginPageSamsara lps = new LoginPageSamsara(driver);
		lps.openAsAdminPositive();
		WelcomeSamsaraPage wsp = new WelcomeSamsaraPage(driver);
		wsp.userTab().click();
		UserPageObjects userp = new UserPageObjects(driver);
		userp.addNewUserButtonByAdmin().click();
		Thread.sleep(1000);
		userp.userNameField().sendKeys(userName);
		userp.firstNameField().sendKeys(firstName);
		userp.lastNameField().sendKeys(lastName);
		userp.aboutField().sendKeys(about);
		userp.secretQuestionField().sendKeys(secretQuestion);
		userp.secretAnswerField().sendKeys(secretAnswer);
		userp.passwordField().sendKeys(password);
		userp.repasswordField().sendKeys(password);
		userp.submitButton().click();

		assert userp.searchUserRow(userName) != -1 : "User isn't created.";

	}

	@Test(enabled = true, priority = 1, dataProviderClass = DataProviders.class, dataProvider = "verifyAdminCanEditUserData")
	public void verifyAdminCanEditUser(String userName, String userEditedName, String editFirstName,
			String editedLastName, String editedAbout) throws IOException {

		UserPageObjects userp = new UserPageObjects(driver);

		userp.adminClickOnEditIcon(userName);

//		userp.editUserNameJS(userEditedName);
//		userp.editFirstNameFieldJS(editFirstName);
//		userp.editLastNameFieldJS(editedLastName);
//		userp.editAboutFieldJS(editedAbout);
		userp.editUserNameField().clear();
		userp.editUserNameField().sendKeys(userEditedName);
		userp.editFirstNameField().clear();
		userp.editFirstNameField().sendKeys(editFirstName);
		userp.editLastNameField().clear();
		userp.editLastNameField().sendKeys(editedLastName);
		userp.editAboutField().clear();
		userp.editAboutField().sendKeys(editedAbout);
		userp.cancelEditButtonClick();
		assert userp.searchUserRow(userEditedName) != -1 : "Admin didn't edit the user.";
	}

	@Test(enabled = true, priority = 2, dataProviderClass = DataProviders.class, dataProvider = "verifyAdminCanDeleteUserData")
	public void verifyAdminCanDeleteUser(String userEditedName) throws IOException {

		UserPageObjects userp = new UserPageObjects(driver);
		userp.adminClickOnDeleteIcon(userEditedName);
//		userp.deleteButtonAdmin().click();
		userp.adminClickDeleteUserButon(); 
		assert userp.searchUserRow(userEditedName) == -1 : "Admin didn't delete the user.";
	}

	@Test(enabled = false, priority = 3, dataProviderClass = DataProviders.class, dataProvider = "verifyUserCanDeleteDoesntOwnHeroData")
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
