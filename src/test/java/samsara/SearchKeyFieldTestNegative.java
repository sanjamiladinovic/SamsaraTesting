package samsara;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AlgorithmsPageObjects;
import resources.BaseClassSamsara;
import resources.DataProviders;

public class SearchKeyFieldTestNegative extends BaseClassSamsara {

	public static Logger log = LogManager.getLogger(SearchKeyFieldTestNegative.class.getName());

	@Test(dataProviderClass = DataProviders.class, dataProvider = "getDataSearchKey")
	public void isTheKeyContaindeInPlaintext(String length, String searchKey, String vigenereKey, char letter) {

		AlgorithmsPageObjects apo = new AlgorithmsPageObjects(driver);
		apo.fillTheAlgorithmsFieldsAndSubmit(length, searchKey, vigenereKey, letter);

		boolean ifPlaintextContainsKey = apo.getPlainTextResult().getText().contains(searchKey);

		System.out.println("Search key is: " + searchKey);
		System.out.println("Plaintext is: " + apo.getPlainTextResult().getText());
		System.out.println("Da li plaintext contains search key: " + ifPlaintextContainsKey);

		String ifTrueFalse = apo.trueFalsePlainTextContainsSearcheKey().getText();

//		algorithmContainsKey.enterNumber().clear();
//		algorithmContainsKey.enterSecondTextField().clear();
//		algorithmContainsKey.enterFieldVinegereKey().clear();
		if (ifPlaintextContainsKey == true && searchKey.length() >= 3) {

			Assert.assertTrue(ifTrueFalse.equals("true"),
					"Key is contained in plaintext, instead false should be written true in Contains key field. Number of search key characters is correct. ");
		} else
			Assert.assertTrue(ifTrueFalse.equals("false"),
					"Key isn't contained in plaintext, instead true should be written false in Contains key field or number of search key characters is incorrect.");

		System.out.println("Successfully passed!");

	}

}
