package app.himalayas.TestComponents;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import app.himalayas.Resourses.ExtentReportNG;

/**
 * @author sreeraj
 * Class which will connect the TestNG Listeners with ExtentReport
 */
public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent = ExtentReportNG.getReporterObject();
	
	
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}
	

	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
