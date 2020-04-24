package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AlgorithmsPageObjects {

	private WebDriver driver;

	private By panelTitleAlgorithms = By.cssSelector(".panel-heading div");
	private By panelBodyText = By.cssSelector(".lead");
	private By number = By.cssSelector("#length");
	private By secondTextField = By.id("plainText");
	private By fieldVinegereKey = By.cssSelector("#vgnKey");
	private By letterField = By.cssSelector("#letter");
	private By submitButton = By.cssSelector(".col-auto button");
	private By primeNumberResult = By.cssSelector("#primes");
	private By fibonacciResult = By.cssSelector("#fibonacci");
	private By factorialResult = By.cssSelector("#factorial");
	private By randomStringResult = By.id("rand");
	private By plainTextResult = By.xpath("//div[@class='panel panel-default']//div[1]//p[1]");
	private By xorVigenereKeyResult = By.xpath("//div[@class='panel panel-default']//div[1]//p[2]");
	private By rot13CesarEncriptionResult = By.xpath("//div[@class='panel panel-default']//div[1]//p[4]");
	private By vigenereResult = By.xpath("//div[@class='panel panel-default']//div[1]//p[7]");
	private By reverseStringResult = By.xpath("//div[@class='panel panel-default']//div[1]//p[9]");
	private By lettersFromRandomStringResult = By
			.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/p[1]");
	private By digitsFromRandomStringResult = By
			.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/p[2]");
	private By isItPalindrom = By.xpath("//div[@class='panel-body']//div[2]//p[3]");
	private By containsSearchKey = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/p[4]");
	private By containsLetter = By.xpath("//div[@class='panel-body']//div[2]//p[5]");
	private By possiblePermutations = By.xpath("//body//textarea[1]");

	public AlgorithmsPageObjects(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;

	}

	public WebElement getPanelTitleAlgorithms() {

		return driver.findElement(panelTitleAlgorithms);

	}

	public WebElement getPanelBodyText() {

		return driver.findElement(panelBodyText);

	}

	public WebElement enterNumber() {

		return driver.findElement(number);

	}

	public WebElement enterSecondTextField() {

		return driver.findElement(secondTextField);

	}

	public WebElement enterFieldVinegereKey() {

		return driver.findElement(fieldVinegereKey);

	}

	public WebElement enterLetterField() {

		return driver.findElement(letterField);

	}

	public WebElement pressSubmitButton() {

		return driver.findElement(submitButton);

	}

	public WebElement getPrimeNumberResult() {

		return driver.findElement(primeNumberResult);

	}

	public WebElement getFibonacciResult() {

		return driver.findElement(fibonacciResult);

	}

	public WebElement getFactorialResult() {

		return driver.findElement(factorialResult);

	}

	public WebElement getRandomStringResult() {

		return driver.findElement(randomStringResult);

	}

	public WebElement getPlainTextResult() {

		return driver.findElement(plainTextResult);

	}

	public WebElement getXorVigenereKeyResult() {

		return driver.findElement(xorVigenereKeyResult);

	}

	public WebElement getRot13CesarEncriptionResult() {

		return driver.findElement(rot13CesarEncriptionResult);

	}

	public WebElement getVigenereResult() {

		return driver.findElement(vigenereResult);

	}

	public WebElement getReverseStringResult() {

		return driver.findElement(reverseStringResult);

	}

	public WebElement getLettersFromRandomStringResult() {

		return driver.findElement(lettersFromRandomStringResult);

	}

	public WebElement getDigitsFromRandomStringResult() {

		return driver.findElement(digitsFromRandomStringResult);

	}

	public WebElement trueFalseIsItPalindrom() {

		return driver.findElement(isItPalindrom);

	}

	public WebElement trueFalsePlainTextContainsSearcheKey() {

		return driver.findElement(containsSearchKey);

	}

	public WebElement trueFalsePlainTextcontainsLetter() {

		return driver.findElement(containsLetter);

	}

	public WebElement countPossiblePermutations() {

		return driver.findElement(possiblePermutations);

	}

	public void fillTheAlgorithmsFieldsAndSubmit(String length, String searchKey, String vigenereKey, char letter) {
		enterNumber().sendKeys(length);
		enterSecondTextField().sendKeys(searchKey);
		enterFieldVinegereKey().sendKeys(vigenereKey);
		enterLetterField().sendKeys(Character.toString(letter));// moralo je da se doda da se
		// karakter pretvori u string
		// zato sto sendKeys ocekuje
		// string
		pressSubmitButton().click();

	}

}
