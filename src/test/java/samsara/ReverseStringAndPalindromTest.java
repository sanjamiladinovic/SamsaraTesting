package samsara;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.AlgorithmsPageObjects;
import resources.BaseClassSamsara;
import util.ReverseStringAndPalindromUtil;

public class ReverseStringAndPalindromTest extends BaseClassSamsara {

	public static Logger log = LogManager.getLogger(ReverseStringAndPalindromTest.class.getName());

	@BeforeTest
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get(prop.getProperty("url1"));
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());

	}

	@Test(dataProvider = "getData")
	public void checkReversedRandomString(String length, String searchKey, String vigenereKey, CharSequence[] letter)
			throws InterruptedException {

		AlgorithmsPageObjects reversePageObj = new AlgorithmsPageObjects(driver);

		reversePageObj.enterNumber().sendKeys(length);
		reversePageObj.enterSecondTextField().sendKeys(searchKey);
		reversePageObj.enterFieldVinegereKey().sendKeys(vigenereKey);
		reversePageObj.enterLetterField().sendKeys(letter);
		reversePageObj.pressSubmitButton().click();

		// moj napravljen reverse string

		String myReverseString = ReverseStringAndPalindromUtil
				.reverseText(reversePageObj.getRandomStringResult().getAttribute("placeholder"));

		System.out.println("My reversed string: " + myReverseString);
		String appReverseString = reversePageObj.getReverseStringResult().getText();
		System.out.println("App reversed string: " + appReverseString);
		log.info("Test checkReversedRandomString - myReverseString je: " + myReverseString + "appReverseString: "
				+ appReverseString);

		Assert.assertEquals(myReverseString, appReverseString);// da li je assert case sensitive

	}

	@Test(dataProvider = "getData")
	public void checkIfIsPalindron(String length, String searchKey, String vigenereKey, CharSequence[] letter) {

		AlgorithmsPageObjects palindromPageObj = new AlgorithmsPageObjects(driver);
		palindromPageObj.enterNumber().sendKeys(length);
		palindromPageObj.enterSecondTextField().sendKeys(searchKey);
		palindromPageObj.enterFieldVinegereKey().sendKeys(vigenereKey);
		palindromPageObj.enterLetterField().sendKeys(letter);
		palindromPageObj.pressSubmitButton().click();

		// proverava da li je plaintext palindrom

		if (ReverseStringAndPalindromUtil
				.isPalindrome(palindromPageObj.getPlainTextResult().getText().toLowerCase()) == true) {

			System.out.println("Plain text: " + palindromPageObj.getPlainTextResult().getText() + " is palindrom.");
			log.info("Plain text: " + palindromPageObj.getPlainTextResult().getText() + " is palindrom.");
			Assert.assertEquals("Word Is palindrom and field is palindrom is correctly filled with true.", "true",
					palindromPageObj.trueFalseIsItPalindrom().getText().toLowerCase());
//			if (palindromPageObj.trueFalseIsItPalindrom().getText().equalsIgnoreCase("true")) {
//				// a ako jeste proverava da li je ispisano u polju isPalindrom true i obrnuto
//
//				Assert.assertTrue("Word Is palindrom and field is palindrom is correctly filled.",
//						palindromPageObj.trueFalseIsItPalindrom().getText().equalsIgnoreCase("true"));

			// log.info("Word Is palindrom and field is palindrom is correctly filled.");
		}

		else {
//			System.out.println("Plain text: " + palindromPageObj.getPlainTextResult().getText() + " isn't palindrom.");
			log.info("Plain text: " + palindromPageObj.getPlainTextResult().getText() + " isn't palindrom.");
			System.out.println("Word isn't palindrom and field is palindrom is correctly filled with: "
					+ palindromPageObj.trueFalseIsItPalindrom().getText());
			// Assert.assertEquals(true,
			// palindromPageObj.trueFalseIsItPalindrom().getText());
			// ("Word Isn't palindrom and field is incorrectly filled.", "true");
			Assert.assertEquals("false", palindromPageObj.trueFalseIsItPalindrom().getText());
		}

	}
	// provera preko metode da li je palindrom ili ne...true/false

	@AfterMethod
	public void testAlgorithmsSamsaraAfter() {
		driver.close();

	}

	@DataProvider
	public Object[][] getData() {

		// Object[][] dataAlg = new Object[1][4];
		return new Object[][] {
//				dataAlg[0][0] = "6";
//				dataAlg[0][1] = "";
//				dataAlg[0][2] = "samasa";
//				dataAlg[0][3] = "s";
				{ "6", "", "samasa", "s" }/*
											 * , { "7", "", "samasa", "s" }, { "8", "", "samasa", "s" }, { "9", "",
											 * "samasa", "s" }, { "10", "", "samasa", "s" } };
											 */
		};
	}
}
