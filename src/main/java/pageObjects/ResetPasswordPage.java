package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResetPasswordPage {
	public WebDriver driver;

	By panelTitle = By.cssSelector("div[id='loginbox'] div:nth-child(2) div:nth-child(1) div");
	By userNameField = By.id("username");
	By questionText = By.id("question");
	By answer = By.id("answer");
	By newPasswordField = By.id("password");
	By confirmPasswordField = By.id("repassword");
	By resetPasswordButton = By.id("submitButton");
	By backToLoginPageLink = By.linkText("Login");
	By catImage = By.cssSelector(".iconmelon");
	By loginButtonUpRightCorner = By.cssSelector("div[class='container-fluid'] ul:nth-child(3) li a");
	By samsaraBackToLoginPage = By.cssSelector(".navbar-brand");
	By password = By.cssSelector("#passwordMessage");
	By repasswordMarchig = By.cssSelector("#repasswordMessage");

	public ResetPasswordPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement checkPanelTitle() {
		return driver.findElement(panelTitle);
	}

	public WebElement enterUsernameField() {
		return driver.findElement(userNameField);
	}

	public WebElement checkQuestionTitle() {
		return driver.findElement(questionText);
	}

	public WebElement enterAnswer() {
		return driver.findElement(answer);
	}

	public WebElement enterNewPasswordField() {
		return driver.findElement(newPasswordField);
	}

	public WebElement confirmPasswordField() {
		return driver.findElement(confirmPasswordField);
	}

	public WebElement resetPasswordButton() {
		return driver.findElement(resetPasswordButton);
	}

	public WebElement backToLoginPageViaLink() {
		return driver.findElement(backToLoginPageLink);
	}

	public WebElement checkCatImage() {
		return driver.findElement(catImage);
	}

	public WebElement backToLoginPageViaLoginButtonUpRightCorner() {
		return driver.findElement(loginButtonUpRightCorner);
	}

	public WebElement backToLoginPageViaSamsaraBrand() {
		return driver.findElement(samsaraBackToLoginPage);
	}

	public WebElement enterPassword() {
		return driver.findElement(password);
	}
	public WebElement rePasswordMatchMessage() {
		return driver.findElement(repasswordMarchig);
	}

}
