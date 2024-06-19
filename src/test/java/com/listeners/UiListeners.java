package com.listeners;

import java.io.File; 
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.utils.BrowserUtils;


public class UiListeners implements ITestListener {

	private ExtentSparkReporter extentSparkReporter;
	private ExtentReports extentReports;
	private ExtentTest extentTest;


	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReports.createTest(result.getMethod().getMethodName());
		System.out.println("---- "+result.getTestName());
		System.out.println("---- "+result.getMethod().getMethodName());
		System.out.println("---- "+result.getMethod().getDescription());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.pass(result.getTestName()+" Passed!!!");
		System.out.println("---- "+result.getTestName()+" Passed!!! ----");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		extentTest.fail(result.getTestName()+" Failed!!!");
		System.out.println("---- "+result.getMethod().getMethodName()+" Failed!!! ----");
		
		extentTest.addScreenCaptureFromPath(BrowserUtils.takeScreenshot(result.getMethod().getMethodName()));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String errorMessage = result.getThrowable().getMessage();
		extentTest.log(Status.FAIL, errorMessage);
		extentTest.skip(result.getTestName()+" Skipped!!!");
		System.out.println("---- "+result.getTestName()+" Skipped!!! ----");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {

		extentReports = new ExtentReports();
		//Code which will create empty folder for us
		//check if the report folder is present... if not present then create one
		//else delete it and then create 
		String reportPath = System.getProperty("user.dir")+"/Reports/"; 
		File f = new File(reportPath);
		if(f.exists()) {
			try {
				FileUtils.forceDelete(f);
				FileUtils.forceMkdir(f);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				FileUtils.forceMkdir(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("d-MMM-YY hh-mm-ss");
		String data  = formater.format(date);
		extentSparkReporter = new ExtentSparkReporter(reportPath+" reports-"+data);
		extentReports.attachReporter(extentSparkReporter);
	}

	@Override
	public void onFinish(ITestContext context) {

		extentReports.flush();
	}


}
