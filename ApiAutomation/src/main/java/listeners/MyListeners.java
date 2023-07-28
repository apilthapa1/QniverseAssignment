package listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import api.utilities.ExtentReporter;

public class MyListeners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName = null;
	
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtentReport();
	}
	
	public void onTestStart(ITestResult result) {
		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + " started executing");
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, testName + " got successfully executed.");
	}

	public void onTestFailure(ITestResult result) {
		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.createNode(testName);
		extentTest.log(Status.FAIL, testName + " Failed");
		extentTest.log(Status.INFO, result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName + " got skipped");
	}

	public void onFinish(ITestContext context) {
		extentReport.flush();
		String extentReportPath = System.getProperty("user.dir") + "\\test-output\\ExtentReports\\ExtentReport.html";
		File extentReport = new File(extentReportPath);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
