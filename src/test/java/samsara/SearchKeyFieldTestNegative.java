package samsara;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.AlgorithmsPageObjects;
import resources.BaseClassSamsara;

public class SearchKeyFieldTestNegative extends BaseClassSamsara {

	public static Logger log = LogManager.getLogger(SearchKeyFieldTestNegative.class.getName());

	@BeforeTest
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get(prop.getProperty("url1"));
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());

	}

	@Test(dataProvider = "getData")
	public void isTheKeyContaindeInPlaintext(String length, String searchKey, String vigenereKey, char letter) {
		AlgorithmsPageObjects algorithmContainsKey = new AlgorithmsPageObjects(driver);
		algorithmContainsKey.enterNumber().sendKeys(length);
		algorithmContainsKey.enterSecondTextField().sendKeys(searchKey);
		algorithmContainsKey.enterFieldVinegereKey().sendKeys(vigenereKey);
		algorithmContainsKey.enterLetterField().sendKeys(Character.toString(letter));// moralo je da se doda da se
																						// karakter pretvori u string
																						// zato sto sendKeys ocekuje
																						// string
		algorithmContainsKey.pressSubmitButton().click();

		boolean ifPlaintextContainsKey = algorithmContainsKey.getPlainTextResult().getText().contains(searchKey);

		System.out.println(searchKey);
		System.out.println(algorithmContainsKey.getPlainTextResult().getText());
		System.out.println(ifPlaintextContainsKey);

		String ifTrueFalse = algorithmContainsKey.trueFalsePlainTextContainsSearcheKey().getText();

//		algorithmContainsKey.enterNumber().clear();
//		algorithmContainsKey.enterSecondTextField().clear();
//		algorithmContainsKey.enterFieldVinegereKey().clear();
		if (ifPlaintextContainsKey == true && searchKey.length() >= 3) {

			Assert.assertTrue(ifTrueFalse.equals("true"),
					"Key is contained in plaintext, instead false should be written true in Contains key field. Number of search key characters is correct. ");
		} else
			Assert.assertTrue(ifTrueFalse.equals("false"),
					"Key isn't contained in plaintext, instead true should be written false in Contains key field. Number of search key characters is correct.");

		System.out.println("Successfully passed!");

	}

	@AfterTest
	public void testAlgorithmsSamsaraAfter() {
		// driver.close();

	}

	@DataProvider
	public Object[][] getData() {

		// Object[][] dataAlg = new Object[1][4];
		return new Object[][] {
//			dataAlg[0][0] = "6";
//			dataAlg[0][1] = "";
//			dataAlg[0][2] = "samasa";
//			dataAlg[0][3] = "s";
				/* { "6", "", "samasa", 's' }, */ { "6", "sas", "samasa", 's' },{ "7", "sasa", "samasa", 's' }/* , { "8", "sas", "samasa", 's' }, */

		};

		// return dataAlg;
	}

}
