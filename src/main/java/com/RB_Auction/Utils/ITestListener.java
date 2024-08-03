package com.RB_Auction.Utils;

import java.io.IOException;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;

public class ITestListener implements org.testng.ITestListener {
  
	public void onTestStart(ITestResult result) {
		ExtentReportManager.startTest(result.getMethod().getMethodName(), result.getMethod().getDescription(), "Test Case Name : "+result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		ExtentReportManager.logPass(result.getMethod().getMethodName()+" Test Case passed successfully");
		try {
			Media mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(Browser.takeScreenShot()).build();
			ExtentReportManager.logScreenshot(mediaModel);
			MediaEntityBuilder.createScreenCaptureFromPath(Browser.capcturingPicture("image 1"), "RB_Auction");
			ExtentReportManager.logScreenshot(mediaModel);
		} catch (IOException e) {
			
		}
		
	}

	public void onTestFailure(ITestResult result) {
		ExtentReportManager.logFail(result.getThrowable().getMessage());
		
		try {
			Media mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(Browser.takeScreenShot()).build();
			ExtentReportManager.logScreenshot(mediaModel);
		} catch (IOException e) {
			
		}
		try {
			Browser.takeScreenShot();
		}catch(Exception e) {
			
		}
	}
	
	
	
	

}
