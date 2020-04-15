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
import util.PrimeUtil;

public class PrimeNumbers {

	public static class TestAlgorithmsPage extends BaseClassSamsara {
		public static Logger log = LogManager.getLogger(PrimeNumbers.class.getName());

		@BeforeTest
		public void initialize() throws IOException, InterruptedException {

			driver = initializeDriver();
			driver.get(prop.getProperty("url1"));
			driver.manage().window().maximize();
			System.out.println(driver.getTitle());

		}

		@Test(dataProvider = "getData")
		public void primeNumbersTestPositive(String length, String searchKey, String vigenereKey, CharSequence[] letter)
				throws InterruptedException {

			AlgorithmsPageObjects algorithmPrimes = new AlgorithmsPageObjects(driver);

			algorithmPrimes.enterNumber().sendKeys(length);
			algorithmPrimes.enterSecondTextField().sendKeys(searchKey);
			algorithmPrimes.enterFieldVinegereKey().sendKeys(vigenereKey);
			algorithmPrimes.enterLetterField().sendKeys(letter);
			algorithmPrimes.pressSubmitButton().click();

			// ITERIRAM KOJI SU ISPRAVNI PROSTI BROJEVI ZA ZADATI BROJ CLANOVA NIZA

			int wantedNumberOfPrimes = Integer.parseInt(length);
			List<Integer> myPrimes = PrimeUtil.getFirstN(wantedNumberOfPrimes);// Lista prostih brojeva kako treba da
																				// budu
																				// poredjani

			if (myPrimes.isEmpty())
				log.info("Error, list is empty. ");
			else
				System.out.println("Correct primes are: " + myPrimes);
			log.info("Correct primes are: " + myPrimes);

			// POREDIM DA LI MOJE PROSTE BROJEVE SA DOBIJENIM U PROGRAMU
			String primesApp = algorithmPrimes.getPrimeNumberResult().getAttribute("placeholder");
			String trimovanPrimesApp = primesApp.replace(" ", "");
			String trimovanPrimesAppBezZagrade = trimovanPrimesApp.replace("[", "");
			String trimovanPrimesAppBezZagrade1 = trimovanPrimesAppBezZagrade.replace("]", "");
			System.out.println("Sredjen app string:" + trimovanPrimesAppBezZagrade1);
			List<String> integerStringPrimesApp = new ArrayList<String>(
					Arrays.asList(trimovanPrimesAppBezZagrade1.split(",")));// napravim
			// listu
			// stingova,
			// da
			// bih
			// posle
			// kroz
			// petlju
			// iterirala
			// i
			// dobila
			// Listu
			// int

			List<Integer> appPrimeListNumber = new ArrayList<Integer>();// puni se sa int kroz petlju

			for (int i = 0; i < integerStringPrimesApp.size(); i++) {// petlja za int

				String primeInt = integerStringPrimesApp.get(i);
				int primeResultNum = Integer.parseInt(primeInt);
				appPrimeListNumber.add(primeResultNum);

			}
			System.out.println("App list int:" + appPrimeListNumber);

			Assert.assertEquals(appPrimeListNumber, myPrimes);

			// System.out.println(trimovanPrimesApp);

//			System.out.println("Prime numbers from app: " + primesApp + " za niz od " + length + " clanova");
//			if (primes.equals(primesApp)) {
			//
//				System.out.println("Prime array is correct. ");
//				log.info("Prime array is correct. ");
			//
//			} else
//				System.out.println("Error! Prime array is not correct. App primes: " + primesApp);
//			log.info("App prime array is not correct. " + primesApp);
//			SoftAssert softAssertion = new SoftAssert();
//			softAssertion.assertEquals(primesApp, primes);
//			softAssertion.assertAll();
		}

		@AfterMethod
		public void testAlgorithmsSamsaraAfter() {
			driver.close();

		}

		@DataProvider
		public Object[][] getData() {

			// Object[][] dataAlg = new Object[1][4];
			return new Object[][] {
//				dataAlg[0][0] = "6";
//				dataAlg[0][1] = "";
//				dataAlg[0][2] = "samasa";
//				dataAlg[0][3] = "s";
					{ "6", "", "samasa", "s" }, { "7", "", "samasa", "s" }, { "8", "", "samasa", "s" },
					{ "9", "", "samasa", "s" },
					{ "10", "", "samasa", "s" }/*
												 * , { "11", "", "samasa", "s" }, { "12", "", "samasa", "s" }, { "13",
												 * "", "samasa", "s" }, { "14", "", "samasa", "s" }, { "15", "",
												 * "samasa", "s" }, { "25", "", "samasa", "s" }
												 */

			};

			// return dataAlg;
		}

	}

}
