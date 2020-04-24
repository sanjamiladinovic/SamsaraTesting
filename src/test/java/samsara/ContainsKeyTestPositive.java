package samsara;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AlgorithmsPageObjects;
import resources.BaseClassSamsara;
import resources.DataProviders;

public class ContainsKeyTestPositive extends BaseClassSamsara {

	public static Logger log = LogManager.getLogger(ContainsKeyTestPositive.class.getName());

	@Test(dataProviderClass = DataProviders.class, dataProvider = "getData")
	public void isTheKeyContaindeInPlaintext(String length, String searchKey, String vigenereKey, char letter) {
		AlgorithmsPageObjects apo = new AlgorithmsPageObjects(driver);
		apo.fillTheAlgorithmsFieldsAndSubmit(length, searchKey, vigenereKey, letter);

		System.out.println("Entered earch key: " + searchKey);
		System.out.println("Plain text result: " + apo.getPlainTextResult().getText());

		String ifTrueFalse = apo.trueFalsePlainTextContainsSearcheKey().getText();

//		algorithmContainsKey.enterNumber().clear();
//		algorithmContainsKey.enterSecondTextField().clear();
//		algorithmContainsKey.enterFieldVinegereKey().clear();

		SoftAssert softasr = new SoftAssert();
		softasr.assertFalse(apo.trueFalsePlainTextContainsSearcheKey().isDisplayed(),
				" Program is displaying search key result weather ther is less of 3 char.in search key.");

		softasr.assertAll();
	}

}
