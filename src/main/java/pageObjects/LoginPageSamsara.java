package pageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.BaseClassSamsara;


public class LoginPageSamsara extends BaseClassSamsara{
	public static Logger log = LogManager.getLogger(LoginPageSamsara.class.getName());
	private WebDriver driver;
	public Properties prop;

	// Locirani WebElementi

	By username = By.id("username");
	By password = By.id("password");
	By loginButton = By.xpath("//input[@type='submit']");
	By createAccount = By.linkText("Create account");
	// By resetPassword = By.linkText("reset password");
	By loginOption = By.xpath("//a[contains(text(),'Log In')]");
	By navBarBrand = By.cssSelector(".navbar-brand");
	By loginMessage = By.xpath("//div[@class='alert alert-danger']");
	By image = By.xpath("//div[@class='iconmelon']");
	By linkResetPassword = By.cssSelector("form[id='form'] div:nth-child(4) a:nth-child(2)");

	public LoginPageSamsara(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;

	}

	// WebElementi metode

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

	public void openAsUserPositive() throws IOException {
		
		prop = new Properties();

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\sanja.miladinovic\\PracticeSamsara\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		
		String rightUser=prop.getProperty("rightUser");
		String rightPassword=prop.getProperty("rightPassword");
		usernameField().sendKeys(rightUser);
		passwordField().sendKeys(rightPassword);
		loginButton().click();
		log.info("Open Endava training web site");
		log.debug("Open Endava training web site");

	}
	public void openAsAdminPositive() throws IOException {
		
		prop = new Properties();

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\sanja.miladinovic\\PracticeSamsara\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		
		String rightUser=prop.getProperty("rightAdmin");
		String rightPassword=prop.getProperty("rightAdminPassword");
		usernameField().sendKeys(rightUser);
		passwordField().sendKeys(rightPassword);
		loginButton().click();
		log.info("Open Endava training web site");
		log.debug("Open Endava training web site");

	}

}
