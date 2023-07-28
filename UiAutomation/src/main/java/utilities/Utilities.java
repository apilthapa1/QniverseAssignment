package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	
	public final static int IMPLICIT_WAIT_TIME = 2;
	 
	
	public static Properties loadPropertiesFile(String value) {
		Properties properties = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\"+value+"\\"+value+".properties");
		
		try {
			FileInputStream fis = new FileInputStream(propFile);
			properties.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public static String captureScreenshot(WebDriver driver, String testName) {
		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenShotPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenShotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationScreenShotPath;
	}
	
	
}
