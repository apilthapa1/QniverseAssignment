package api.utilities;

import java.io.File;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	
	static ExtentReports extentReport;
	static Properties configPropertyFile;
	public static ExtentReports generateExtentReport() {
		extentReport = new ExtentReports();
		
		File extentReportFile = new File(System.getProperty("user.dir") + "/test-output/ExtentReports/apiReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setDocumentTitle("Qniverse Candidate API Assignment Report");
		
		extentReport.attachReporter(sparkReporter);
		
		setSystemInformation("Username", System.getProperty("user.name"));
		setSystemInformation("Java Version", System.getProperty("java.version"));
		setSystemInformation("Operating system", System.getProperty("os.name"));
		
		return extentReport;
	}
	
	private static void setSystemInformation(String key, String value) {
		extentReport.setSystemInfo(key, value);
	}
}
