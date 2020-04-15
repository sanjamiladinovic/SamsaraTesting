package samsara;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AlgorithmsPageObjects;
import resources.BaseClassSamsara;
import util.ContainsKeyUtil;

public class ContainsLetterTestPossitive extends BaseClassSamsara {

	public static Logger log = LogManager.getLogger(ContainsLetterTestPossitive.class.getName());

	@BeforeTest
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get(prop.getProperty("url1"));
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());

	}

	@Test(dataProvider = "getData")
	public void isTheKeyContaindeInPlaintext(String length, String searchKey, String vigenereKey, char letter) {
		AlgorithmsPageObjects algorithmContainsLetter = new AlgorithmsPageObjects(driver);
		algorithmContainsLetter.enterNumber().sendKeys(length);
		algorithmContainsLetter.enterSecondTextField().sendKeys(searchKey);
		algorithmContainsLetter.enterFieldVinegereKey().sendKeys(vigenereKey);
		algorithmContainsLetter.enterLetterField().sendKeys(Character.toString(letter));// moralo je da se doda da se
																						// karakter pretvori u string
																						// zato sto sendKeys ocekuje
																						// string
		algorithmContainsLetter.pressSubmitButton().click();

		System.out.println("PlainText: " + algorithmContainsLetter.getPlainTextResult().getText());
		SoftAssert softAssertion = new SoftAssert();

		if (ContainsKeyUtil.containedKeyInPlainText(algorithmContainsLetter.getPlainTextResult().getText(),
				letter) == true) {
			System.out.println(
					"Ako sadrzi slovo, pise: " + algorithmContainsLetter.trueFalsePlainTextcontainsLetter().getText());
			Assert.assertEquals(algorithmContainsLetter.trueFalsePlainTextcontainsLetter().getText(), "true");

		} else {
			System.out.println("Ako ne sadrzi slovo, pise: "
					+ algorithmContainsLetter.trueFalsePlainTextcontainsLetter().getText());
			Assert.assertEquals(algorithmContainsLetter.trueFalsePlainTextcontainsLetter().getText(), "false");
		}

//		softAssertion.assertTrue(
//				ContainsKeyUtil.containedKeyInPlainText(algorithmContainsKey.getPlainTextResult().getText(), letter));
//		softAssertion.assertAll();

	}

	@AfterMethod
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
				{ "6", "", "samasa", 's' }, { "7", "", "samasa", 's' }, { "8", "", "samasa", 's' },
			

		};

		// return dataAlg;
	}

}
