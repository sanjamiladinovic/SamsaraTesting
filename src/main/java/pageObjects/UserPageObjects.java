package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserPageObjects {

	private WebDriver driver;

	public UserPageObjects(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;
	}

	// Locirani WebElementi
	By addNewUserButtonByAdmin = By.xpath("//div[@class='panel-body']/div/div/a");
	By userNameField = By.id("username");
	By firstNameField = By.id("firstName");
	By lastNameField = By.id("lastName");
	By aboutField = By.id("about");
	By secretQuestionField = By.id("secretQuestion");
	By secretAnswerField = By.id("secretAnswer");
	By passwordField = By.id("password");
	By repasswordField = By.id("repassword");
	By submitButton = By.id("submitButton");
	By cancelBytton = By.xpath("// div[@class=\"modal-content\"]/form/div[2]/button[1]");
	By wholeUsersTable = By.cssSelector("table[id='users-table']");
//	String clearEditUserNameField = "$('#edit-user-form').find('#username').val('')";
//	String clearEditFirstNameField = "$('#edit-user-form').find('#firstName').val('')";
//	String clearEditLastNameField = "$('#edit-user-form').find('#lastName').val('')";
//	String clearEditAboutField = "$('#edit-user-form').find('#about').val('')";
	By editUserNameField = By.id("username");
	By editFirstNameField = By.id("firstName");
	By editLastNameField = By.id("lastName");
	By editAboutField = By.id("about");
	By saveEditButton = By.cssSelector(".btn-primary");
	By cancelEditButton = By.cssSelector(".btn-default");
	// By deleteButton =
	// By.xpath("//div[@id='deleteUserModal']/div/div/div[3]/form/button[2]");
	String deleteButton = "$('#deleteUserModalHolder').find('.btn-danger').click()";
	By searchField = By.id("search");
	By searchButton = By.xpath("//div[@id='custom-search-input']/div/span/button");

	// WebElementi metode
	public WebElement searchButton() {

		return driver.findElement(searchButton);
	}

	public WebElement searchField() {

		return driver.findElement(searchField);
	}

	public void adminClickDeleteUserButon() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(deleteButton);
	}
//	public void editUserNameJS(String newUserName1) {
//
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript(clearEditUserNameField);
////		String newUserName = clearEditUserNameField.substring(clearEditUserNameField.lastIndexOf('.')) + ".val('"
////				+ newUserName1 + "')";
//		String newUserName = String.format("$('#edit-user-form').find('#username').val('[%d]')", newUserName1);
//		js.executeScript(newUserName);
//
//	}

//	public void editFirstNameFieldJS(String newFirstName1) {
//
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript(clearEditFirstNameField);
//		String newFirstName = String.format("$('#edit-user-form').find('#username').val('[%d]')", newFirstName1);
//		js.executeScript(newFirstName);
//
//	}
//
//	public void editLastNameFieldJS(String newLastName1) {
//
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript(clearEditLastNameField);
//		String newLastName = String.format("$('#edit-user-form').find('#username').val('[%d]')", newLastName1);
//		js.executeScript(newLastName);
//	}
//
//	public void editAboutFieldJS(String newAbout1) {
//
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript(clearEditAboutField);
//		String newAbout = String.format("$('#edit-user-form').find('#username').val('[%d]')", newAbout1);
//		js.executeScript(newAbout);

//	}
//	public WebElement deleteButtonAdmin() {
//
//		return driver.findElement(deleteButton);
//	}

	public WebElement addNewUserButtonByAdmin() {

		return driver.findElement(addNewUserButtonByAdmin);
	}

	public WebElement userNameField() {

		return driver.findElement(userNameField);
	}

	public WebElement firstNameField() {

		return driver.findElement(firstNameField);
	}

	public WebElement lastNameField() {

		return driver.findElement(lastNameField);
	}

	public WebElement aboutField() {

		return driver.findElement(aboutField);
	}

	public WebElement secretQuestionField() {

		return driver.findElement(secretQuestionField);
	}

	public WebElement secretAnswerField() {

		return driver.findElement(secretAnswerField);
	}

	public WebElement passwordField() {

		return driver.findElement(passwordField);
	}

	public WebElement repasswordField() {

		return driver.findElement(repasswordField);
	}

	public WebElement submitButton() {

		return driver.findElement(submitButton);
	}

	public WebElement cancelBytton() {

		return driver.findElement(cancelBytton);
	}

	public WebElement editUserNameField() {

		return driver.findElement(editUserNameField);
	}

	public WebElement editFirstNameField() {

		return driver.findElement(editFirstNameField);
	}

	public WebElement editLastNameField() {

		return driver.findElement(editLastNameField);
	}

	public WebElement editAboutField() {

		return driver.findElement(editAboutField);
	}

	public WebElement saveEditButton() {

		return driver.findElement(saveEditButton);
	}

	public void cancelEditButtonClick() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String queryClickSubmit = "$('#edit-user-form').find('.btn-primary').click()";
		js.executeScript(queryClickSubmit);
	}

	public void adminClickOnEditIcon(String userName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int userRow = searchUserRow(userName);
		String queryClickOnEditIcon = String.format("$($('#users-table tr')[%d]).find('.glyphicon-pencil').click()",
				userRow);

		js.executeScript(queryClickOnEditIcon);

	}

	public void adminClickOnDeleteIcon(String userName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int userRow = searchUserRow(userName);
		String queryClickOnEditIcon = String.format("$($('#users-table tr')[%d]).find('.glyphicon-trash').click()",
				userRow);

		js.executeScript(queryClickOnEditIcon);

	}

	public int searchUserRow(String userName) {

		List<WebElement> rows = driver.findElement(wholeUsersTable).findElements(By.tagName("tr"));
		int result = -1;
		for (int ir = 0; ir < rows.size(); ir++) {
			List<WebElement> columns = rows.get(ir).findElements(By.tagName("td"));
			for (int ic = 0; ic < columns.size(); ic++)
				if (columns.get(0).getText().equals(userName)) {
					result = ir;
					break;
				}

		}
		return result;
	}

}
