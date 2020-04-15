package samsara;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AlgorithmsPageObjects;
import resources.BaseClassSamsara;
import util.ContainsKeyUtil;

public class ContainsKeyTestPositive extends BaseClassSamsara {

	public static Logger log = LogManager.getLogger(ContainsKeyTestPositive.class.getName());

	@BeforeMethod
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

		System.out.println(searchKey);
		System.out.println(algorithmContainsKey.getPlainTextResult().getText());

		String ifTrueFalse = algorithmContainsKey.trueFalsePlainTextContainsSearcheKey().getText();

//		algorithmContainsKey.enterNumber().clear();
//		algorithmContainsKey.enterSecondTextField().clear();
//		algorithmContainsKey.enterFieldVinegereKey().clear();

		SoftAssert softasr = new SoftAssert();
		softasr.assertFalse(algorithmContainsKey.trueFalsePlainTextContainsSearcheKey().isDisplayed(),
				" Program is displaying search key result weather ther is less of 3 char.in search key.");

		softasr.assertAll();
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
				/* { "6", "", "samasa", 's' }, */{ "6", "", "samasa", 's' }, { "7", "s", "samasa",
						's' }/* , { "8", "sas", "samasa", 's' }, */

		};

		// return dataAlg;
	}

}
