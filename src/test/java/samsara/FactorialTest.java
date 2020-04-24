package samsara;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AlgorithmsPageObjects;
import resources.BaseClassSamsara;
import resources.DataProviders;
import util.FactorialUtil;

public class FactorialTest extends BaseClassSamsara {
	public static Logger log = LogManager.getLogger(FactorialTest.class.getName());

	@Test(dataProviderClass = DataProviders.class, dataProvider = "getData")
	public void testFactorial(String length, String searchKey, String vigenereKey, char letter) {

		AlgorithmsPageObjects apo = new AlgorithmsPageObjects(driver);
		apo.fillTheAlgorithmsFieldsAndSubmit(length, searchKey, vigenereKey, letter);

		int factorialFromNumber = Integer.parseInt(length);
		System.out.println(factorialFromNumber);
		int factorialFromApp = Integer.parseInt(apo.getFactorialResult().getAttribute("placeholder"));
		int myRealFactorial = (int) FactorialUtil.factorialWithRecursion(factorialFromNumber);

		Assert.assertEquals(factorialFromApp, myRealFactorial,
				"Factorial from app isn't same like factorial calculated by myself.");

	}

}
