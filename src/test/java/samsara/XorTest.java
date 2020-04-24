package samsara;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AlgorithmsPageObjects;
import resources.BaseClassSamsara;
import resources.DataProviders;
import util.XorUtil;

public class XorTest extends BaseClassSamsara {
	public static Logger log = LogManager.getLogger(XorTest.class.getName());

	@Test(dataProviderClass = DataProviders.class, dataProvider = "getData")
	public void testFactorial(String length, String searchKey, String vigenereKey, char letter) {

		AlgorithmsPageObjects apo = new AlgorithmsPageObjects(driver);
		apo.fillTheAlgorithmsFieldsAndSubmit(length, searchKey, vigenereKey, letter);

		String plainTextXorVigenereKeyMy = XorUtil.plaintextXorVigenereKey(vigenereKey,
				apo.getPlainTextResult().getText());
		String plainTextXorVigenereKeyApp = apo.getXorVigenereKeyResult().getText();
		System.out.println("Ocekivani XOR: " + plainTextXorVigenereKeyMy);
		System.out.println("App XOR: " + plainTextXorVigenereKeyApp);
		log.info("PlainText je: " + apo.getPlainTextResult().getText() + " Vigenere key je: " + vigenereKey
				+ " Moj ptXORvk: " + plainTextXorVigenereKeyMy + "App ptXORvk: " + plainTextXorVigenereKeyApp);
		Assert.assertEquals(plainTextXorVigenereKeyApp, plainTextXorVigenereKeyMy, "XOR isn't as expected.");

	}

}
