package samsara;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AlgorithmsPageObjects;
import resources.BaseClassSamsara;
import resources.DataProviders;

public class TestAlgorithmsPage extends BaseClassSamsara {
	public static Logger log = LogManager.getLogger(TestAlgorithmsPage.class.getName());

	@Test(dataProviderClass = DataProviders.class, dataProvider = "getData")

	public void compareRandomTextAndPlainTextPositive(String length, String searchKey, String vigenereKey,
			char letter) {
		// da li su isti random strig i plain text
		AlgorithmsPageObjects apo = new AlgorithmsPageObjects(driver);
		apo.fillTheAlgorithmsFieldsAndSubmit(length, searchKey, vigenereKey, letter);

		String plainText = apo.getPlainTextResult().getText();
		String randomString = apo.getRandomStringResult().getAttribute("placeholder");

		System.out.println("PlainText je: " + plainText + ", randomstring je: " + randomString);
		if (randomString.equals(plainText)) {

			System.out.println("PlainText je: " + plainText + " i isti je kao i randomstring: " + randomString);
			log.info("PlainText je: " + plainText + " i isti je kao i randomstring: " + randomString);
		} else {
			System.out.println("Error! Random string and plaintext aren't equal. ");
			log.info("Error! Random string and plaintext aren't equal. ");
		}
		SoftAssert softAssertion = new SoftAssert();
		softAssertion.assertEquals(plainText, randomString);
		softAssertion.assertAll();
	}

}
