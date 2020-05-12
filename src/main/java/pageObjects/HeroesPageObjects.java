package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeroesPageObjects {

	private WebDriver driver;

	public HeroesPageObjects(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;
	}
	// Locirani WebElementi

	By addNewHero = By.xpath("//span[contains(text(),'Add New')]");
	By nameFieldNewHero = By.id("name");
	By levelFeldNewHero = By.id("level");
	By editHeroName = By.xpath("//div[@id=\"editHeroModal\"]/div/div/form/div[1]/div[1]/input");
	By editHeroLevel = By.xpath("//div[@id=\"editHeroModal\"]/div/div/form/div[1]/div[2]/input");

	By classNewHero = By.id("type");
	By classEditedHero = By.xpath("//div[@id=\"editHeroModal\"]/div/div/form/div[1]/div[3]/select");
	By saveButton = By.id("submitButton");
	By cancelButton = By.cssSelector("button[class='btn btn-default']");
	By wholeTable = By.cssSelector("table[id='heroes-table']");
	By deleteHeroButton = By.cssSelector(".modal-footer form button:nth-child(2)");
	By searchHeroButton = By.xpath("//div[@id='custom-search-input']/div/span/button");
	By searchHeroField = By.id("search");

	// WebElementi metode
	public WebElement searchHeroField() {

		return driver.findElement(searchHeroField);
	}

	public WebElement searchHeroButton() {

		return driver.findElement(searchHeroButton);
	}

	public WebElement addNewHero() {

		return driver.findElement(addNewHero);
	}

	public WebElement nameFieldNewHero() {

		return driver.findElement(nameFieldNewHero);
	}

	public WebElement levelFeldNewHero() {

		return driver.findElement(levelFeldNewHero);
	}

	public WebElement selectClassNewHero() {

		return driver.findElement(classNewHero);
	}

	public WebElement saveButton() {

		return driver.findElement(saveButton);
	}

	public WebElement cancelButton() {

		return driver.findElement(cancelButton);
	}

	public WebElement deleteHeroButton() {

		return driver.findElement(deleteHeroButton);
	}

	public WebElement editHeroName() {

		return driver.findElement(editHeroName);
	}

	public WebElement editHeroLevel() {

		return driver.findElement(editHeroLevel);
	}

	public WebElement classEditedHero() {

		return driver.findElement(classEditedHero);
	}
//	public WebElement wholeTable() {
//
//		return driver.findElement(wholeTable);
//	}

	public int searchHeroRow(String heroeName) {

		List<WebElement> rows = driver.findElement(wholeTable).findElements(By.tagName("tr"));
		int result = -1;
		for (int ir = 0; ir < rows.size(); ir++) {
			List<WebElement> columns = rows.get(ir).findElements(By.tagName("td"));
			for (int ic = 0; ic < columns.size(); ic++)
				if (columns.get(0).getText().equals(heroeName)) {
					result = ir;
					break;
				}

		}
		return result;
	}

	public int countUsersColumnByName(String userName) {
		int brojac = 0;
		List<WebElement> rows = driver.findElement(wholeTable).findElements(By.tagName("tr"));
		
		for (int ir = 0; ir < rows.size(); ir++) {
			List<WebElement> columns = rows.get(ir).findElements(By.tagName("td"));
			for (int ic = 0; ic < columns.size(); ic++)
				if (columns.get(3).getText().equals(userName)) {
					brojac++;

				}

		}
		return brojac;
	}

}
