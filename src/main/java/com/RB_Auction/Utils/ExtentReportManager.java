package com.RB_Auction.Utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	
	public static ExtentReports extent = new ExtentReports();
	public static ExtentSparkReporter spark;
	
	public static Map<Long, ExtentTest> testMap = new HashMap<>();
	public static Map<String, ExtentTest> extentMap = new HashMap<>();

	
	public static void startReport()
	{
		String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
		String dateStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());

		spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/"+dateStamp+"/"+"RB_Auction"+timeStamp+".html");
		
		extent.attachReporter(spark);
		/*extent.createTest("MyFirstTest")
		  .log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");
		 */	
		extent.setSystemInfo("Env_type", "RB_Auction");
		extent.setSystemInfo("user_name", "RB_Auction");
		spark.config().setDocumentTitle("RBAuction_html_Report");
		spark.config().setReportName("RBAuction_html_Report");
		spark.config().setTheme(Theme.DARK);
		
	}
	
	public static void startTest(String testName, String description, String categories)
	{
		ExtentTest test = extent.createTest(testName, description);
		test.assignCategory(categories);
		testMap.put(Thread.currentThread().getId(), test);
		extentMap.put(testName, test);

	}
	public static void logScreenshot(Media mediaModel) throws IOException {
		// getCurrentTest().addScreenCaptureFromBase64String(ScreenshotUtil.takeScreenshot(DriverFactory.getInstance().getWebDriver()));
		/*
		 Media mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(BaseClass
				).build();
		*/
		getCurrentTest().info("", mediaModel);

	}
	public static void logFail(String message) {
		getCurrentTest().log(Status.FAIL, message);

	}

	
	public static void logInfo(String message) {
		getCurrentTest().log(Status.INFO, message);
	}

	public static void endCurrentTest() {
		getCurrentTest().getExtent().flush();

		testMap.remove(Thread.currentThread().getId());
	}

	public static ExtentTest getCurrentTest() {
		return testMap.get(Thread.currentThread().getId());

	}


	public static void logPass(String message) {
		getCurrentTest().log(Status.PASS, message);
	}

	
	public static void stopReport()
	{
		extent.flush();
	}
	
	@Test
	public void extendReport()
	{
		startReport();
		stopReport();
	}

}
