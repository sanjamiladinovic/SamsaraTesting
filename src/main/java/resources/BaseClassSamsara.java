package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClassSamsara {

	public static WebDriver driver;
	public Properties prop;

	public WebDriver initializeDriver() throws IOException {
		// WebDriverWait w=new WebDriverWait(driver,20);
		prop = new Properties();

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\sanja.miladinovic\\PracticeSamsara\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);

		String browserName = prop.getProperty("browser");
		System.out.println(browserName);
		if (browserName.contentEquals("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\sanja.miladinovic\\Instalacije Sanja\\chromedriver_win32_OLD\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equals("firefox")) {

			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\sanja.miladinovic\\Instalacije Sanja\\geckodriver-v0.26.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.contentEquals("IE")) {

		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}

}
