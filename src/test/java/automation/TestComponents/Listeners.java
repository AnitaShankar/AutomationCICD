package automation.TestComponents;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import automation.resources.ExtentReporterNG;


public class Listeners extends BaseTests implements ITestListener {
	
	ExtentReports extent =ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //thread safe
	@Override
	public void onTestStart(ITestResult result)
	{
		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // it will assign unique thread id
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		extentTest.get().log(Status.PASS, "TEST IS PASS");
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		extentTest.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		String filePath=null;
		
	 try 
	 {
		 filePath=getScreenShot(result.getMethod().getMethodName(),driver);
	 } 
	 catch (IOException e) 
	 {
		// TODO Auto-generated catch block
		e.printStackTrace();
	 }
	 extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
	}	
		
		@Override
		public void onFinish(ITestContext context)
		{
		    // not implemented
			extent.flush();
		}
		
		
		
	
	
	

}
