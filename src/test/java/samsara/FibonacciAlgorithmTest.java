package samsara;

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
import resources.DataProviders;
import util.FibonacciUtil;

public class FibonacciAlgorithmTest extends BaseClassSamsara {
	public static Logger log = LogManager.getLogger(TestAlgorithmsPage.class.getName());

	@Test(dataProviderClass = DataProviders.class, dataProvider = "getData")
	// FIBONACCI SEQUENCE
	public void fibonacciSequencePositive(String length, String searchKey, String vigenereKey, char letter)
			throws InterruptedException {

		AlgorithmsPageObjects apo = new AlgorithmsPageObjects(driver);
		apo.fillTheAlgorithmsFieldsAndSubmit(length, searchKey, vigenereKey, letter);

		int fibonacciLengthNum = Integer.parseInt(length);
		List<Integer> myFibonacciList = FibonacciUtil.getFibonacciNum(fibonacciLengthNum);
		System.out.println("My f. list: " + myFibonacciList);

		String fibonacciResult = apo.getFibonacciResult().getAttribute("placeholder");
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

}
