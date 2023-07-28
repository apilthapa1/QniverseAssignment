package base;

import java.util.Properties;

import utilities.Utilities;

public class BasePage {
	
	public Properties configProp;
	public Properties testDataProp;
	
	public BasePage() {
		configProp = Utilities.loadPropertiesFile("config");
		testDataProp = Utilities.loadPropertiesFile("testdata");
	}
	
	
	protected String getConfigValue(String key) {
		return configProp.getProperty(key);
	}
	
	public String getTestData(String key) {
		return testDataProp.getProperty(key);
	}
	
}
