package com.automation.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automation.pages.base.basePage;

public class ListenerUtility extends basePage implements ITestListener {
	private Logger log = LogManager.getLogger(ListenerUtility.class);
	private ExtentReportsUtility extentReport = ExtentReportsUtility.getInstance();

	@Override
	public void onStart(ITestContext context) {
		log.info("MyTest started...............");
		extentReport.startExtentReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		log.info("MyTest ended.................");
		extentReport.endReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		log.info(result.getMethod().getMethodName() + " started..................");
		extentReport.startSingleTestReport(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		log.info(result.getMethod().getMethodName() + " ended with success......................");
		extentReport.logTestpassed(result.getMethod().getMethodName() + " ended with success......................");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		log.error(result.getMethod().getMethodName() + " ended with failure.....................");
		extentReport.logTestFailedWithException(result.getThrowable());
		String filename = new SimpleDateFormat("yy_MM_dd_HH_mm_ss").format(new Date());
		String path = Constants.SCREENSHOT_DIR_PATH + filename + ".png";
		takeScreenshot(path);
		extentReport.logTestWithscreenshot(path);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		log.warn(result.getMethod().getMethodName() + " skiped...........................");
		extentReport.logTestFailed(result.getMethod().getMethodName() + " skiped...........................");

	}

}
