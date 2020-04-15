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
import util.SplitLettersFromDigitsUtil;

public class FindLettersDigitsInPlainTextTest extends BaseClassSamsara {
	public static Logger log = LogManager.getLogger(FindLettersDigitsInPlainTextTest.class.getName());

	@BeforeTest
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get(prop.getProperty("url1"));
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());

	}

	@Test(dataProvider = "getData")
	public void testFactorial(String length, String searchKey, String vigenereKey, char letter) {

		AlgorithmsPageObjects algorithmExtractLettersAndDigits = new AlgorithmsPageObjects(driver);
		algorithmExtractLettersAndDigits.enterNumber().sendKeys(length);
		algorithmExtractLettersAndDigits.enterSecondTextField().sendKeys(searchKey);
		algorithmExtractLettersAndDigits.enterFieldVinegereKey().sendKeys(vigenereKey);
		algorithmExtractLettersAndDigits.enterLetterField().sendKeys(Character.toString(letter));
		algorithmExtractLettersAndDigits.pressSubmitButton().click();

		SplitLettersFromDigitsUtil.resetResults();
		String getPlainText = algorithmExtractLettersAndDigits.getPlainTextResult().getText();
		SplitLettersFromDigitsUtil.extractLettersAndDigitsFromPlainText(getPlainText);
		List<Character> myFindedLetters = SplitLettersFromDigitsUtil.getFindedLetters();
		List<Character> myFindedDigits = SplitLettersFromDigitsUtil.getFindedDigits();

		String appLetters = algorithmExtractLettersAndDigits.getLettersFromRandomStringResult().getText();
		String appLettersBezJedneZagrade = appLetters.replace("[", "");
		String appLettersWithoutBrackets = appLettersBezJedneZagrade.replace("]", "");
		String appLettersWithoutBracketsandSpaces = appLettersWithoutBrackets.replace(" ", "");
		String appLettersNew = appLettersWithoutBracketsandSpaces.replace(",", "");

		String appDigits = algorithmExtractLettersAndDigits.getDigitsFromRandomStringResult().getText();
		String appDigitsBezJedneZagrade = appDigits.replace("[", "");
		String appDigitsWithoutBrackets = appDigitsBezJedneZagrade.replace("]", "");
		String appDigitsWithoutBracketsandSpaces = appDigitsWithoutBrackets.replace(" ", "");
		String appDigitsNew = appDigitsWithoutBracketsandSpaces.replace(",", "");

		System.out.println(getPlainText);
		System.out.println(myFindedLetters);
		System.out.println(myFindedDigits);

//		List<String> appLettersList = new ArrayList<String>(
//				Arrays.asList(appLettersWithoutBracketsandSpaces.split(",")));
//		List<String> appDigitsList = new ArrayList<String>(Arrays.asList(appDigitsWithoutBracketsandSpaces.split(",")));

		List<Character> appLettersList = new ArrayList<>();
		for (char chl : appLettersNew.toCharArray()) {

			appLettersList.add(chl);
		}

		List<Character> appDigitsList = new ArrayList<>();
		for (char chn : appDigitsNew.toCharArray()) {

			appDigitsList.add(chn);
		}
		System.out.println(appLettersList);
		System.out.println(appDigitsList);
		Assert.assertEquals(appLettersList, myFindedLetters, "Letters are not equal.");
		Assert.assertEquals(appDigitsList, myFindedDigits, "Digits are not equal.");
	}

	@AfterMethod
	public void testAlgorithmsSamsaraAfter() {
		// driver.close();

	}

	@DataProvider
	public Object[][] getData() {

		// Object[][] dataAlg = new Object[1][4];
		return new Object[][] {
//				dataAlg[0][0] = "6";
//				dataAlg[0][1] = "";
//				dataAlg[0][2] = "samasa";
//				dataAlg[0][3] = "s";
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
