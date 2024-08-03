package com.RB_Auction.Utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser {
	public static WebDriver driver;
	public static ChromeOptions chromeopt;
	public static EdgeOptions edgeopt;
	public static FirefoxOptions fireOpt;

	public static WebDriver lanchBrowser(String browsername) {

		if (browsername.contains("chrome")) {
			chromeopt = new ChromeOptions();
			chromeopt.addArguments("--remote-allow-origins=*");
			chromeopt.addArguments("--disable-notifications");
			chromeopt.addArguments("--use-fake-ui-for-media-stream");
			driver = new ChromeDriver(chromeopt);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		}

		else if (browsername.contains("edge")) {
			edgeopt = new EdgeOptions();
			edgeopt.addArguments("--remote-allow-origins=*");
			edgeopt.addArguments("--disable-notifications");
			edgeopt.addArguments("--use-fake-ui-for-media-stream");
			driver = new EdgeDriver(edgeopt);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}

		else if (browsername.contains("firefox")) {
			fireOpt = new FirefoxOptions();
			fireOpt.addArguments("--remote-allow-origins=*");
			fireOpt.addArguments("--disable-notifications");
			fireOpt.addArguments("--use-fake-ui-for-media-stream");
			driver = new FirefoxDriver(fireOpt);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}

		return driver;

	}

	public static void openUrl(String url) {
		driver.get(url);
	}

	public static void closeBrowser() {
		driver.quit();
	}

	public static void tackScreenShot(String imegename) throws Exception, Exception {
		TakesScreenshot ts = (TakesScreenshot) driver;
		FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), new File(".//screnshot//" + imegename + "ExtentReportManager.png"));
	}
	
	 public static  void selectVisiblitiOfText(WebElement ele , String visibilitySelect) {
		 
		 Select select = new Select(ele);
		 List<WebElement> alloptions = select.getOptions();
		 for (WebElement eleopt : alloptions) {
			String Data = eleopt.getText();
			System.out.println(Data);
		}
		 select.selectByVisibleText(visibilitySelect);
	 }
	 
	 public static void switchToWindow() {
		 Set<String> windowIDs=driver.getWindowHandles();
			List<String >windowIDslist=new ArrayList<String>(windowIDs); // covert Set ---> List
			String parentWindowID=windowIDslist.get(0);
			String childWindowID=windowIDslist.get(1);
			driver.switchTo().window(childWindowID);
			System.out.println(parentWindowID);
			System.out.println("child window title:"+driver.getTitle()); 
			//return windowIDslist;

	 }
	 
	 public static String takeScreenShot() 
		{
			TakesScreenshot scrShot =((TakesScreenshot)driver);
			String SrcFile=scrShot.getScreenshotAs(OutputType.BASE64);
			return SrcFile;
		}
	 
	 public static String capcturingPicture(String filename) throws IOException {
		File sourcefile =  ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE) ;
		File destinationFile = new File(System.getProperty("user.dir")+"//.ss//"+filename+".png");
		FileUtils.copyFile(sourcefile, destinationFile);
		return destinationFile.getAbsolutePath();
		 
	 }
	 
	 public static void waitForElementVisability(WebElement ele)
	 {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
		 wait.until(ExpectedConditions.visibilityOf(ele));
	 }
	 public static void scrollUpToElement(WebElement ele)
	 {
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", ele);
	 }
	 public static void scrollToTop() {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
	}

	
}
