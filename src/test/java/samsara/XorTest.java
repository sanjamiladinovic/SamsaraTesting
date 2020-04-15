package samsara;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AlgorithmsPageObjects;
import resources.BaseClassSamsara;
import util.XorUtil;

public class XorTest extends BaseClassSamsara {
	public static Logger log = LogManager.getLogger(XorTest.class.getName());

	@BeforeTest
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get(prop.getProperty("url1"));
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());

	}

	@Test(dataProvider = "getData")
	public void testFactorial(String length, String searchKey, String vigenereKey, char letter) {

		AlgorithmsPageObjects algorithmXor = new AlgorithmsPageObjects(driver);
		algorithmXor.enterNumber().sendKeys(length);
		algorithmXor.enterSecondTextField().sendKeys(searchKey);
		algorithmXor.enterFieldVinegereKey().sendKeys(vigenereKey);
		algorithmXor.enterLetterField().sendKeys(Character.toString(letter));
		algorithmXor.pressSubmitButton().click();

		String plainTextXorVigenereKeyMy = XorUtil.plaintextXorVigenereKey(vigenereKey,
				algorithmXor.getPlainTextResult().getText());
		String plainTextXorVigenereKeyApp = algorithmXor.getXorVigenereKeyResult().getText();
		System.out.println("Ocekivani XOR: " + plainTextXorVigenereKeyMy);
		System.out.println("App XOR: " + plainTextXorVigenereKeyApp);
		Assert.assertEquals(plainTextXorVigenereKeyApp, plainTextXorVigenereKeyMy, "XOR isn't as expected.");
	}

	@AfterTest
	public void testAlgorithmsSamsaraAfter() {
		driver.close();

	}

	@DataProvider
	public Object[][] getData() {

		return new Object[][] {

				{ "6", "", "samasa", 's' }, { "7", "", "samasa", 's' }, { "8", "", "samasa", 's' },

		};

	}
}
