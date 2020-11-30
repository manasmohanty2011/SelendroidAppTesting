package listners;

import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testbase.Base;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Listners extends Base implements ITestListener{

Base b = new Base();

private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	public void onTestStart(ITestResult result) {
		System.out.println("on test start");
	}
	public void onTestSuccess(ITestResult result) {
		System.out.println("on test success");
		test.log(LogStatus.PASS, result.getMethod().getMethodName() + "test is passed");
	}

public void onTestFailure(ITestResult result) {
	System.out.println("on test failure");
	test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed");
	TakesScreenshot ts = (TakesScreenshot) driver;
	File src = ts.getScreenshotAs(OutputType.FILE);
    Date d=new Date();
    String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
	try {
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"//screenshots//" + screenshotFile));
		String file = test.addScreenCapture(System.getProperty("user.dir")+"//screenshots//" + screenshotFile);
		test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", file);
		test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", result.getThrowable().getMessage());
	} catch (IOException e) {
		e.printStackTrace();
	}
}

public void onTestSkipped(ITestResult result) {
	System.out.println("on test skipped");
	test.log(LogStatus.SKIP, result.getMethod().getMethodName() + "test is skipped");
	
}

public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(result));
	onTestFailure( result);
}

public void onStart(ITestContext context) {
}

public void onFinish(ITestContext context) {
}
}