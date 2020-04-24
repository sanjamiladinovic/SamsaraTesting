package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WelcomeSamsaraPage {

	public WebDriver driver;

	
	public WelcomeSamsaraPage(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;
	}
	// Locirani WebElementi

	By welcomeMessage = By.xpath("//div[@class='panel-title text-center']");
	By homeButton = By.xpath("//a[contains(text(),'Home')]");

	

	// WebElementi metode

	public WebElement welcomeMessageBoardPage() {

		return driver.findElement(welcomeMessage);
	}

}
