package com.RB_Auction.Pages;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.RB_Auction.Utils.Browser;
import com.RB_Auction.Utils.ExtentReportManager;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@type='text']")
	WebElement searchBox;
	@FindBy(xpath = "//h2[contains(text(),'Showing')]")
	WebElement resultsLabel;
	@FindBy(xpath = "(//ul[@id='searchResults']//a//following::a)[1]")
	WebElement firstResult;

	@FindBy(xpath = "//input[@id='manufactureYearRange_min']")
	WebElement fromYearTextBox;

	@FindBy(xpath = "//input[@id='manufactureYearRange_max']")
	WebElement toYearTextBox;

	public void searchBox(String value) throws Exception {
		Browser.waitForElementVisability(searchBox);
		searchBox.sendKeys(value, Keys.ENTER);
		ExtentReportManager.logInfo("Entered Value in to Search Box Field as ::: " + value);
	}

	public int captureTheTotalNoOfResults() {
		String resultsLabelName[] = resultsLabel.getText().split(" ");
		String countValue = resultsLabelName[3];
		int totalNoOfResults = Integer.valueOf(countValue);
		ExtentReportManager.logInfo("Total No Of Results ::: " + totalNoOfResults);
		return totalNoOfResults;
	}

	public boolean verifyingTheFirstResultOnThePageHasTheWordAs(String searchValue) {

		Browser.scrollUpToElement(firstResult);
		String actualfirstResultName = firstResult.getText();
		boolean status = false;

		try {
			if (actualfirstResultName.contains(searchValue)) {
				status = true;
			} else {
				status = false;
			}
		} catch (Exception e) {
			status = false;
		}
		ExtentReportManager.logInfo("Successfully verified the first result contains the " + searchValue);

		return status;
	}

	public void applyingTheYearFilter(String fromYear) {
		try
		{
		Browser.scrollUpToElement(fromYearTextBox);
		fromYearTextBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), fromYear);
		LocalDate currentDate = LocalDate.now();
		int currentYear = currentDate.getYear();
		System.out.println("Current Year: " + currentYear);
		toYearTextBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), String.valueOf(currentYear),Keys.ENTER);
		Thread.sleep(5000);
		Browser.scrollToTop();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public boolean verifyTheNumberOfResultsBeforeApplyingFilterAndAfterApplyingFilter(int beforeApplyingFilterResults, int afterApplyingFilterResults)
	{
		boolean status=false;
		
		if(beforeApplyingFilterResults>afterApplyingFilterResults)
		{
			status=true;
		}
		else
		{
			status=false;
		}
		
		return status;
	}

}
