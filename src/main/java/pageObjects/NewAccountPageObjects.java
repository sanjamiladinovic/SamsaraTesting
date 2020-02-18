package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewAccountPageObjects {

	public WebDriver driver;

	public NewAccountPageObjects(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	By accounPageMessage = By.xpath("//div[@class=\"container\"]/div/div/div[1]/div");
	By registrationFieldCode = By.id("registrationCode");
	By regCodeMessage = By.id("regiCodeMessage");
	By usernameField = By.id("username");
	By firstNameFiel = By.id("firstName");
	By lastNameFiel = By.id("lastName");
	By lastNameFieldMessage = By.id("lastNameMessage");
	By aboutfield = By.id("about");
	By secretQuestionField = By.id("secretQuestion");
	By secretAnswerField = By.id("secretAnswer");
	By passwordField = By.id("password");
	By passwordMessage = By.id("passwordMessage");
	By rePasswordField = By.id("repassword");
	By passwordMatchingMessage = By.id("repasswordMessage");
	By signUpButton = By.id("submitButton");

	public WebElement accountLandingPageMessage() {
		return driver.findElement(accounPageMessage);
	}

	public WebElement registrationFieldCode() {
		return driver.findElement(registrationFieldCode);
	}

	public WebElement registrationFieldCodeMessage() {
		return driver.findElement(regCodeMessage);
	}

	public WebElement usernameField() {
		return driver.findElement(usernameField);
	}

	public WebElement firstNameFiel() {
		return driver.findElement(firstNameFiel);
	}

	public WebElement lastNameFiel() {
		return driver.findElement(lastNameFiel);
	}

	public WebElement aboutfield() {
		return driver.findElement(aboutfield);
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

	public WebElement passwordMessage() {
		return driver.findElement(passwordMessage);
	}

	public WebElement rePasswordField() {
		return driver.findElement(rePasswordField);
	}

	public WebElement passwordMatchingMessage() {
		return driver.findElement(passwordMatchingMessage);
	}

	public WebElement signUpButton() {
		return driver.findElement(signUpButton);
	}

}
