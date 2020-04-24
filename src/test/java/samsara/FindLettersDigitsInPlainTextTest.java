package samsara;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AlgorithmsPageObjects;
import resources.BaseClassSamsara;
import resources.DataProviders;
import util.SplitLettersFromDigitsUtil;

public class FindLettersDigitsInPlainTextTest extends BaseClassSamsara {
	public static Logger log = LogManager.getLogger(FindLettersDigitsInPlainTextTest.class.getName());

	@Test(dataProviderClass = DataProviders.class, dataProvider = "getData")
	public void testFactorial(String length, String searchKey, String vigenereKey, char letter) {

		AlgorithmsPageObjects apo = new AlgorithmsPageObjects(driver);
		apo.fillTheAlgorithmsFieldsAndSubmit(length, searchKey, vigenereKey, letter);

		SplitLettersFromDigitsUtil.resetResults();
		String getPlainText = apo.getPlainTextResult().getText();
		SplitLettersFromDigitsUtil.extractLettersAndDigitsFromPlainText(getPlainText);
		List<Character> myFindedLetters = SplitLettersFromDigitsUtil.getFindedLetters();
		List<Character> myFindedDigits = SplitLettersFromDigitsUtil.getFindedDigits();

		String appLetters = apo.getLettersFromRandomStringResult().getText();
		String appLettersBezJedneZagrade = appLetters.replace("[", "");
		String appLettersWithoutBrackets = appLettersBezJedneZagrade.replace("]", "");
		String appLettersWithoutBracketsandSpaces = appLettersWithoutBrackets.replace(" ", "");
		String appLettersNew = appLettersWithoutBracketsandSpaces.replace(",", "");

		String appDigits = apo.getDigitsFromRandomStringResult().getText();
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

}
