package com.RB_Auction.Utils;

import org.testng.IExecutionListener;
import org.testng.ISuite;

public class ISuiteListener implements org.testng.ISuiteListener,IExecutionListener {

	public void onStart(ISuite suite) {
		ExtentReportManager.startReport();
	}

	public void onFinish(ISuite suite) {
		ExtentReportManager.stopReport();
	}


	
	

}
