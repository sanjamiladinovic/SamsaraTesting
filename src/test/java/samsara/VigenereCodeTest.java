package samsara;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.AlgorithmsPageObjects;
import resources.BaseClassSamsara;
import resources.DataProviders;
import util.VigenereKeyUtil;

public class VigenereCodeTest extends BaseClassSamsara {
	public static Logger log = LogManager.getLogger(VigenereCodeTest.class.getName());

	@Test(dataProviderClass = DataProviders.class, dataProvider = "getDataVigenereCodeTest")
	public void testFactorial(String length, String searchKey, String vigenereKey, char letter) {

		AlgorithmsPageObjects apo = new AlgorithmsPageObjects(driver);
		apo.fillTheAlgorithmsFieldsAndSubmit(length, searchKey, vigenereKey, letter);

		String myVigenere = VigenereKeyUtil.getEncriptedVigenere(vigenereKey, apo.getPlainTextResult().getText());
		System.out.println(myVigenere);
		String appVigenere = apo.getVigenereResult().getText();
		System.out.println(appVigenere);
		Assert.assertEquals("Error! Vigenere key is wrong. ", myVigenere, appVigenere);
		log.info("Vigenere key is correctly created.");

	}

}
