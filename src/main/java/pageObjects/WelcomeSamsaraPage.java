package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WelcomeSamsaraPage {

	private WebDriver driver;

	public WelcomeSamsaraPage(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;
	}
	// Locirani WebElementi

	By welcomeMessage = By.xpath("//div[@class='panel-title text-center']");
	By homeButton = By.xpath("//a[contains(text(),'Home')]");
	By startTestingButton = By.xpath("//a[contains(text(),'Start Testing!')]");
	By shareWithFriends = By.xpath("//a[contains(text(),'Share with friends!')]");
	By heroesTab = By.xpath("//a[contains(text(),'Heroes')]");
	By userTab = By.xpath("//a[contains(text(),'Users')]");

	// WebElementi metode

	public WebElement welcomeMessageBoardPage() {

		return driver.findElement(welcomeMessage);
	}

	public WebElement homeButtonM() {

		return driver.findElement(homeButton);

	}

	public WebElement startTestingButtonM() {

		return driver.findElement(startTestingButton);

	}

	public WebElement shareWithFriendsM() {

		return driver.findElement(shareWithFriends);

	}

	public WebElement heroesTab() {

		return driver.findElement(heroesTab);

	}
	public WebElement userTab() {

		return driver.findElement(userTab);

	}
}
