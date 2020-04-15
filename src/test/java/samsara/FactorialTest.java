package samsara;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AlgorithmsPageObjects;
import resources.BaseClassSamsara;
import util.FactorialUtil;

public class FactorialTest extends BaseClassSamsara {
	public static Logger log = LogManager.getLogger(FactorialTest.class.getName());

	@BeforeTest
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get(prop.getProperty("url1"));
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());

	}

	@Test(dataProvider = "getData")
	public void testFactorial(String length, String searchKey, String vigenereKey, char letter) {

		AlgorithmsPageObjects algorithmFactorial = new AlgorithmsPageObjects(driver);
		algorithmFactorial.enterNumber().sendKeys(length);
		algorithmFactorial.enterSecondTextField().sendKeys(searchKey);
		algorithmFactorial.enterFieldVinegereKey().sendKeys(vigenereKey);
		algorithmFactorial.enterLetterField().sendKeys(Character.toString(letter));
		algorithmFactorial.pressSubmitButton().click();

		int factorialFromNumber = Integer.parseInt(length);
		System.out.println(factorialFromNumber);
		int factorialFromApp = Integer.parseInt(algorithmFactorial.getFactorialResult().getAttribute("placeholder"));
		int myRealFactorial = (int) FactorialUtil.factorialWithRecursion(factorialFromNumber);

		Assert.assertEquals(factorialFromApp, myRealFactorial,
				"Factorial from app isn't same like factorial calculated by myself.");

	}

	@AfterMethod
	public void testAlgorithmsSamsaraAfter() {
		driver.close();

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
