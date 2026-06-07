package testpackage_DataDrivenTestingFramework;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.google.common.io.Files;

public class Listen extends BaseClass implements ITestListener
{
	ExtentReports extent = extentreportobj();
	ExtentTest test;
	ThreadLocal<ExtentTest> thread = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		System.out.println("Test is starting.");
		String testname = result.getMethod().getMethodName();
		test = extent.createTest(testname);
		thread.set(test);
		thread.get().info("Parallel Execution Extent Report");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		System.out.println("Test has succeeded.");
		thread.get().pass("Pass");
		
		try {
			
			String testname = result.getMethod().getMethodName();
			
			w = (WebDriver) result.getTestClass().getRealClass().getField("w").get(result.getInstance());
			TakesScreenshot ts = (TakesScreenshot) w;
			File src = ts.getScreenshotAs(OutputType.FILE);
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
			String formatedDate = now.format(formatter);
		
			File dest = new File("./screenshots/"+testname+"_"+formatedDate+".png");
			Files.copy(src, dest);
			
			//Attach screenshot with every test report result.
			String filepath = dest.getAbsolutePath();
			thread.get().addScreenCaptureFromPath(filepath);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		extent.flush();//Without flush the report file may be created but remain empty.
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		System.out.println("Test has failed.");
		thread.get().fail("Fail");
		
		try {
			
			String testname = result.getMethod().getMethodName();//To get the screenshot with respect to the Method name to avoid confusion.
			
			w = (WebDriver) result.getTestClass().getRealClass().getField("w").get(result.getInstance());
			TakesScreenshot ts = (TakesScreenshot) w;
			File src = ts.getScreenshotAs(OutputType.FILE);
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
			String formatedDate = now.format(formatter);
			//File dest = new File("./screenshots/FailedScreenshots.png");
			
			File dest = new File("./screenshots/"+testname+"_"+formatedDate+".png");//Instead of FailedScreenshots we need to pass the method name with respect to the particular screenshot.
			Files.copy(src, dest);
			
			//Attach screenshot with every test report result
			String filepath = dest.getAbsolutePath();
			thread.get().addScreenCaptureFromPath(filepath);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		extent.flush();
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
		System.out.println("Timeout issue.");
	}
	
}