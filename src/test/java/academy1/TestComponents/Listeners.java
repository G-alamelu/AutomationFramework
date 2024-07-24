package academy1.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import academy1.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	//ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	@Override
    public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		//extentTest.set(test);
        
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	test.log(Status.PASS, "Test passed");
        
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	
    	test.fail(result.getThrowable());
    	try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
    	String filepath ;
    	try {
			filepath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	        
    }

 
	@Override
    public void onTestSkipped(ITestResult result) {
        
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
 
    }

    @Override
    public void onFinish(ITestContext context) {
      
    	extent.flush();
    }

}

