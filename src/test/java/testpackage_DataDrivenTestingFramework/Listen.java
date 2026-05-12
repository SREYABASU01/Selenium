package testpackage_DataDrivenTestingFramework;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.google.common.io.Files;

public class Listen extends BaseClass implements ITestListener
{

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		System.out.println("Test is starting.");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		System.out.println("Test has succeeded.");
		try {
			w = (WebDriver) result.getTestClass().getRealClass().getField("w").get(result.getInstance());
			TakesScreenshot ts = (TakesScreenshot) w;
			File src = ts.getScreenshotAs(OutputType.FILE);
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
			String formatedDate = now.format(formatter);
			File dest = new File("./screenshots/PassedScreenshots.png");
			Files.copy(src, dest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		System.out.println("Test has failed.");
		try {
			w = (WebDriver) result.getTestClass().getRealClass().getField("w").get(result.getInstance());
			TakesScreenshot ts = (TakesScreenshot) w;
			File src = ts.getScreenshotAs(OutputType.FILE);
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
			String formatedDate = now.format(formatter);
			File dest = new File("./screenshots/FailedScreenshots.png");
			Files.copy(src, dest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
		System.out.println("Timeout issue.");
	}
	
}