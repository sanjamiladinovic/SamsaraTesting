package samsara;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.AlgorithmsPageObjects;
import resources.BaseClassSamsara;
import resources.DataProviders;
import util.ReverseStringAndPalindromUtil;

public class ReverseStringAndPalindromTest extends BaseClassSamsara {

	public static Logger log = LogManager.getLogger(ReverseStringAndPalindromTest.class.getName());

	@Test(dataProviderClass = DataProviders.class, dataProvider = "getData")
	public void checkReversedRandomString(String length, String searchKey, String vigenereKey, char letter)
			throws InterruptedException {

		AlgorithmsPageObjects apo = new AlgorithmsPageObjects(driver);
		apo.fillTheAlgorithmsFieldsAndSubmit(length, searchKey, vigenereKey, letter);
		// moj napravljen reverse string

		String myReverseString = ReverseStringAndPalindromUtil
				.reverseText(apo.getRandomStringResult().getAttribute("placeholder"));

		System.out.println("My reversed string: " + myReverseString);
		String appReverseString = apo.getReverseStringResult().getText();
		System.out.println("App reversed string: " + appReverseString);
		log.info("Test checkReversedRandomString - myReverseString je: " + myReverseString + " appReverseString: "
				+ appReverseString);

		Assert.assertEquals(myReverseString, appReverseString);// da li je assert case sensitive

	}

	@Test(dataProviderClass = DataProviders.class, dataProvider = "getData")
	public void checkIfIsPalindrom(String length, String searchKey, String vigenereKey, char letter) {
		AlgorithmsPageObjects apo = new AlgorithmsPageObjects(driver);
		apo.fillTheAlgorithmsFieldsAndSubmit(length, searchKey, vigenereKey, letter);

		// proverava da li je plaintext palindrom

		if (ReverseStringAndPalindromUtil.isPalindrome(apo.getPlainTextResult().getText().toLowerCase()) == true) {

			System.out.println("Plain text: " + apo.getPlainTextResult().getText() + " is palindrom.");
			log.info("Plain text: " + apo.getPlainTextResult().getText() + " is palindrom.");
			Assert.assertEquals("Word Is palindrom and field is palindrom is correctly filled with true.", "true",
					apo.trueFalseIsItPalindrom().getText().toLowerCase());
//			if (palindromPageObj.trueFalseIsItPalindrom().getText().equalsIgnoreCase("true")) {
//				// a ako jeste proverava da li je ispisano u polju isPalindrom true i obrnuto
//
//				Assert.assertTrue("Word Is palindrom and field is palindrom is correctly filled.",
//						palindromPageObj.trueFalseIsItPalindrom().getText().equalsIgnoreCase("true"));

			// log.info("Word Is palindrom and field is palindrom is correctly filled.");
		}

		else {
//			System.out.println("Plain text: " + palindromPageObj.getPlainTextResult().getText() + " isn't palindrom.");
			log.info("Plain text: " + apo.getPlainTextResult().getText() + " isn't palindrom.");
			System.out.println("Word isn't palindrom and field is palindrom is correctly filled with: "
					+ apo.trueFalseIsItPalindrom().getText());
			// Assert.assertEquals(true,
			// palindromPageObj.trueFalseIsItPalindrom().getText());
			// ("Word Isn't palindrom and field is incorrectly filled.", "true");
			Assert.assertEquals("false", apo.trueFalseIsItPalindrom().getText());
		}

	}
	// provera preko metode da li je palindrom ili ne...true/false

}
