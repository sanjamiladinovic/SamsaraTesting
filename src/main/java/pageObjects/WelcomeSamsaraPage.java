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
	// Lokatori

	By welcomeMessage = By.xpath("//div[@class='panel-heading']/div");

	

	// WebElementi

	public WebElement welcomeMessageBoardPage() {

		return driver.findElement(welcomeMessage);
	}

}
