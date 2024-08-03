package com.RB_Auction.TestScripts;

import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.RB_Auction.Pages.LoginPage;
import com.RB_Auction.Utils.Browser;
import com.RB_Auction.Utils.ExcellReader;
import com.RB_Auction.Utils.ITestListener;


public class LoginPageTest {

	
	WebDriver driver;
	String imegename;
	int count = 0;
	ITestListener itl= new ITestListener();
	LoginPage login;

	@BeforeMethod
	public void setUp(Method method) throws Exception {
		String celldata = ExcellReader.getData("info", "Sheet1", 1, 0);
		driver = Browser.lanchBrowser(celldata);
		String url = ExcellReader.getData("info", "Sheet1",1,1);
		driver.get(url);
		//ExtentReportManager.startTest(method.getName(), method.getName(), method.getName());
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Browser.tackScreenShot("imegename");
		Browser.closeBrowser();
	}
	
	@Test(enabled = true)
	public void capturingTheTotalNumberOfResults() throws Exception {	
		login=new LoginPage(driver);
	    imegename = "capturingTheTotalNumberOfResults_"+count++;
		String searchTextValue = ExcellReader.getData("config", "searchTextValue");
		login.searchBox(searchTextValue);
		int results=login.captureTheTotalNoOfResults();
        Assert.assertTrue(Integer.class.isInstance(results), "The method did not return an integer.");	
	}
	
	@Test (enabled = true)
	public void verifyingTheFirstResultOnThePageHasTheWordAsSearchText() throws Exception {	
		login=new LoginPage(driver);
	    imegename = "verifyingTheFirstResultOnThePageHasTheWordAsSearchText_"+count++;
		String searchtextValue = ExcellReader.getData("config", "searchTextValue");
		login.searchBox(searchtextValue);
		boolean results=login.verifyingTheFirstResultOnThePageHasTheWordAs(searchtextValue);
		Assert.assertTrue(results,"The first result doesn't contains the searchText ");
	}
	
	@Test (enabled = true)
	public void verifyingTheNoOfResultsReturnedAfterApplyingTheYearFilterFrom2010ToCurrentYear() throws Exception {	
		login=new LoginPage(driver);
	    imegename = "ApplyingTheYearFilterVerifyTheResults_"+count++;
		String searchtextValue = ExcellReader.getData("config", "searchTextValue");
		login.searchBox(searchtextValue);
		int totalResults=login.captureTheTotalNoOfResults();
		String fromYear = ExcellReader.getData("config", "fromYear");
		login.applyingTheYearFilter(fromYear);
		int afterApplyingYearFilterResults=login.captureTheTotalNoOfResults();
		boolean results=login.verifyTheNumberOfResultsBeforeApplyingFilterAndAfterApplyingFilter(totalResults, afterApplyingYearFilterResults);
		Assert.assertTrue(results,"After applying the year filter test results are grater then total results ");
	}
	
}
