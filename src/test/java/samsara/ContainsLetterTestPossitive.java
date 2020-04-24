package samsara;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AlgorithmsPageObjects;
import resources.BaseClassSamsara;
import resources.DataProviders;
import util.ContainsKeyUtil;

public class ContainsLetterTestPossitive extends BaseClassSamsara {

	public static Logger log = LogManager.getLogger(ContainsLetterTestPossitive.class.getName());

	@Test(dataProviderClass = DataProviders.class, dataProvider = "getData")
	public void isTheKeyContaindeInPlaintext(String length, String searchKey, String vigenereKey, char letter) {
		AlgorithmsPageObjects apo = new AlgorithmsPageObjects(driver);
		apo.fillTheAlgorithmsFieldsAndSubmit(length, searchKey, vigenereKey, letter);

		System.out.println("PlainText: " + apo.getPlainTextResult().getText());
		SoftAssert softAssertion = new SoftAssert();

		if (ContainsKeyUtil.containedKeyInPlainText(apo.getPlainTextResult().getText(), letter) == true) {
			System.out.println("Posto sadrzi slovo, pise: " + apo.trueFalsePlainTextcontainsLetter().getText());
			Assert.assertEquals(apo.trueFalsePlainTextcontainsLetter().getText(), "true");

		} else {
			System.out.println("Posto ne sadrzi slovo, pise: " + apo.trueFalsePlainTextcontainsLetter().getText());
			Assert.assertEquals(apo.trueFalsePlainTextcontainsLetter().getText(), "false");
		}

//		softAssertion.assertTrue(
//				ContainsKeyUtil.containedKeyInPlainText(algorithmContainsKey.getPlainTextResult().getText(), letter));
//		softAssertion.assertAll();

	}
}
