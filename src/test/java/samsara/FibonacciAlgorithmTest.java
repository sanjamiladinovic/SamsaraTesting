package samsara;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AlgorithmsPageObjects;
import resources.BaseClassSamsara;
import util.FibonacciUtil;

public class FibonacciAlgorithmTest extends BaseClassSamsara {
	public static Logger log = LogManager.getLogger(TestAlgorithmsPage.class.getName());

	@BeforeTest
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get(prop.getProperty("url1"));
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());

	}

	@Test(dataProvider = "getData")
	// FIBONACCI SEQUENCE
	public void fibonacciSequencePositive(String length, String searchKey, String vigenereKey, CharSequence[] letter)
			throws InterruptedException {

		AlgorithmsPageObjects algorithmFibonacci = new AlgorithmsPageObjects(driver);

		algorithmFibonacci.enterNumber().sendKeys(length);
		algorithmFibonacci.enterSecondTextField().sendKeys(searchKey);
		algorithmFibonacci.enterFieldVinegereKey().sendKeys(vigenereKey);
		algorithmFibonacci.enterLetterField().sendKeys(letter);
		algorithmFibonacci.pressSubmitButton().click();

		int fibonacciLengthNum = Integer.parseInt(length);
		List<Integer> myFibonacciList = FibonacciUtil.getFibonacciNum(fibonacciLengthNum);
		System.out.println("My f. list: " + myFibonacciList);

		String fibonacciResult = algorithmFibonacci.getFibonacciResult().getAttribute("placeholder");
		String newFibonacciResult = fibonacciResult.replace("[", "");
		String newNewFibonacciResult = newFibonacciResult.replace("]", "");
		String pojedinacniZnakBezRazmaka = newNewFibonacciResult.replace(" ", "");

		System.out.println("App sredjen string: " + pojedinacniZnakBezRazmaka);

		List<String> integerStringFibonaciApp = new ArrayList<String>(
				Arrays.asList(pojedinacniZnakBezRazmaka.split(",")));// napravim listu stingova, da bih posle kroz
																		// petlju iterirala i dobila niz int

		System.out.println(integerStringFibonaciApp);

		List<Integer> appFibonaciListNumber = new ArrayList<Integer>();// puni se sa int kroz petlju

		for (int i = 0; i < integerStringFibonaciApp.size(); i++) {// petlja za int

			String fibInt = integerStringFibonaciApp.get(i);
			int fibonacciResultNum = Integer.parseInt(fibInt);
			appFibonaciListNumber.add(fibonacciResultNum);

		}
		System.out.println("App list int:" + appFibonaciListNumber);

		Assert.assertEquals(appFibonaciListNumber, myFibonacciList);

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
