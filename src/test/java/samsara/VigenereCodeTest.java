package samsara;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.AlgorithmsPageObjects;
import resources.BaseClassSamsara;
import util.VigenereKeyUtil;

public class VigenereCodeTest extends BaseClassSamsara {
	public static Logger log = LogManager.getLogger(VigenereCodeTest.class.getName());

	@BeforeTest
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get(prop.getProperty("url1"));
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());

	}

	@Test(dataProvider = "getData")
	public void testFactorial(String length, String searchKey, String vigenereKey, char letter) {

		AlgorithmsPageObjects algorithmVigenere = new AlgorithmsPageObjects(driver);
		algorithmVigenere.enterNumber().sendKeys(length);
		algorithmVigenere.enterSecondTextField().sendKeys(searchKey);
		algorithmVigenere.enterFieldVinegereKey().sendKeys(vigenereKey);
		algorithmVigenere.enterLetterField().sendKeys(Character.toString(letter));
		algorithmVigenere.pressSubmitButton().click();

		String myVigenere = VigenereKeyUtil.getEncriptedVigenere(vigenereKey,
				algorithmVigenere.getPlainTextResult().getText());
		System.out.println(myVigenere);
		String appVigenere = algorithmVigenere.getVigenereResult().getText();
		System.out.println(appVigenere);
		Assert.assertEquals("Error! Vigenere key is wrong. ", myVigenere, appVigenere);

	}

	@AfterTest
	public void testAlgorithmsSamsaraAfter() {
		driver.close();

	}

	@DataProvider
	public Object[][] getData() {

		return new Object[][] {

				{ "6", "", "samasa", 's' }, 
				{ "7", "", "samasa", 's' }, 
				{ "8", "", "samasa", 's' },

		};

	}

}
