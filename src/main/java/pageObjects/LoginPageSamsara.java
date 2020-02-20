package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageSamsara {

	public WebDriver driver;

	// Lokatori

	By username = By.id("username");
	By password = By.id("password");
	By loginButton = By.xpath("//input[@type='submit']");
	By createAccount = By.linkText("Create account");
	//By resetPassword = By.linkText("reset password");
	By loginOption = By.xpath("//a[contains(text(),'Log In')]");
	By navBarBrand = By.cssSelector(".navbar-brand");
	By loginMessage = By.xpath("//div[@class='alert alert-danger']");
	By image = By.xpath("//div[@class='iconmelon']");
	By linkResetPassword = By.cssSelector("form[id='form'] div:nth-child(4) a:nth-child(2)");

	public LoginPageSamsara(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;

	}

	// WebElementi

	public WebElement usernameField() {

		return driver.findElement(username);
	}

	public WebElement passwordField() {

		return driver.findElement(password);
	}

	public WebElement loginButton() {

		return driver.findElement(loginButton);
	}

	public WebElement createAccountLink() {

		return driver.findElement(createAccount);
	}

//	public WebElement resetPasswordLink() {
//
//		return driver.findElement(resetPassword);
//	}

	public WebElement loginOption() {

		return driver.findElement(loginOption);
	}

	public WebElement navBarBrand() {

		return driver.findElement(loginOption);
	}

	public WebElement loginWarnMessage() {

		return driver.findElement(loginMessage);
	}

	public WebElement loginImage() {

		return driver.findElement(image);
	}
	public WebElement resetPasswordLink() {

		return driver.findElement(linkResetPassword);
	}

}
