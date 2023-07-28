package base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import utilities.Utilities;

public class BaseTest {
	
	protected WebDriver _driver;
	protected Properties configProp = Utilities.loadPropertiesFile("config");
	protected Properties testDataProp = Utilities.loadPropertiesFile("testdata");
	
	@BeforeTest
	protected void OpenBrowser() {
		String browserName = configProp.getProperty("browser").toLowerCase();
		switch (browserName) {
		case "chrome":
			_driver = new ChromeDriver();
			break;
		case "firefox":
			_driver = new FirefoxDriver();
			break;
		case "edge":
			_driver = new EdgeDriver();
			break;
		default:
			break;
		}
		_driver.manage().window().maximize();
		_driver.get(configProp.getProperty("url"));
	}
	
	@AfterClass
	protected void CloseBrowser() {
		_driver.close();
		_driver.quit();
	}
}
