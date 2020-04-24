package samsara;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AlgorithmsPageObjects;
import resources.BaseClassSamsara;
import resources.DataProviders;
import util.PrimeUtil;

public class PrimeNumbers {

	public static class TestAlgorithmsPage extends BaseClassSamsara {
		public static Logger log = LogManager.getLogger(PrimeNumbers.class.getName());

		@Test(dataProviderClass = DataProviders.class, dataProvider = "getData")
		public void primeNumbersTestPositive(String length, String searchKey, String vigenereKey, char letter)
				throws InterruptedException {

			AlgorithmsPageObjects apo = new AlgorithmsPageObjects(driver);
			apo.fillTheAlgorithmsFieldsAndSubmit(length, searchKey, vigenereKey, letter);

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
			String primesApp = apo.getPrimeNumberResult().getAttribute("placeholder");
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

	}

}
