package samsara;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AlgorithmsPageObjects;
import resources.BaseClassSamsara;
import util.FibonacciUtil;
import util.PrimeUtil;

public class TestAlgorithmsPage extends BaseClassSamsara {
	public static Logger log = LogManager.getLogger(TestAlgorithmsPage.class.getName());

	@BeforeTest
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get(prop.getProperty("url1"));
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());

	}

	@Test(dataProvider = "getData")

	public void compareRandomTextAndPlainTextPositive(String length, String searchKey, String vigenereKey,
			CharSequence[] letter) {
		// da li su isti random strig i plain text
		AlgorithmsPageObjects algorithmRandomStringPlainText = new AlgorithmsPageObjects(driver);
		algorithmRandomStringPlainText.enterNumber().sendKeys(length);
		algorithmRandomStringPlainText.enterSecondTextField().sendKeys(searchKey);
		algorithmRandomStringPlainText.enterFieldVinegereKey().sendKeys(vigenereKey);
		algorithmRandomStringPlainText.enterLetterField().sendKeys(letter);
		algorithmRandomStringPlainText.pressSubmitButton().click();

		String plainText = algorithmRandomStringPlainText.getPlainTextResult().getText();
		String randomString = algorithmRandomStringPlainText.getRandomStringResult().getAttribute("placeholder");

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
				{ "6", "", "samasa", "s" }, { "7", "", "samasa", "s" }, { "8", "", "samasa", "s" },
				/*
				 * { "9", "", "samasa", "s" }, { "10", "", "samasa", "s" } , { "11", "",
				 * "samasa", "s" }, { "12", "", "samasa", "s" }, { "13", "", "samasa", "s" }, {
				 * "14", "", "samasa", "s" }, { "15", "", "samasa", "s" }, { "25", "", "samasa",
				 * "s" }
				 */

		};

		// return dataAlg;
	}

}
