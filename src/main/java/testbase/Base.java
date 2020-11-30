
package testbase;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import frameworkdata.ExtentManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;
import uiActions.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;


public class Base {

    public static Properties config;
    public static AndroidDriver<AndroidElement> driver;

    public ExtentReports rep = ExtentManager.getInstance();
    public static ExtentTest test;
//    private static Logger log = LogManager.getLogger(Base.class.getName());
    public SoftAssert softAssert = new SoftAssert();

    public WebDriver initializeDriver() throws IOException {

        /**
         * Configuration Properties
         */
        config = new Properties();

        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/config/Config.properties");
            config.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String platform = config.getProperty("platformName");

        /**
         * Appium server can started programmatically by uncommenting below line
         */
//        startServer();

        if (platform.equals("Android")) {
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability(MobileCapabilityType.PLATFORM_NAME, config.getProperty("platformName"));
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, config.getProperty("deviceName"));
            cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, config.getProperty("automationName"));
            cap.setCapability("app", config.getProperty("appPath"));
            cap.setCapability("appPackage", config.getProperty("appPackage"));
            cap.setCapability("appActivity", config.getProperty("appActivity"));
            cap.setCapability("noReset", false);
            cap.setCapability("uiautomator2ServerLaunchTimeout", 20000);
            String serverUrl = "http://" + config.getProperty("serverIp") + ":" + config.getProperty("serverPort") + "/wd/hub";
            /**
             * try catch to handle error
             */
            try {
                driver = new AndroidDriver<AndroidElement>(new URL(serverUrl), cap);
            } catch (NullPointerException ex) {
                throw new RuntimeException("Appium driver could not be initialised for this device");
            }
        } else if (platform.equals("ios")) {

        }
        return driver;

    }

    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    private DesiredCapabilities cap;

    public void startServer() {
        cap = new DesiredCapabilities();
        cap.setCapability("noReset", "false");
        builder = new AppiumServiceBuilder();
        builder.withIPAddress(config.getProperty("serverIp"));
        builder.usingPort(Integer.parseInt(config.getProperty("serverPort")));
        builder.withCapabilities(cap);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    public void stopServer() {
        service.stop();
    }

    @AfterMethod
    public synchronized void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Util.setTestFailureFlag(false);
            takeScreenShot();
            try {
                String str = result.getThrowable().getCause().toString()
                        .replace("<", " ")
                        .replace(">", " ");
                test.log(LogStatus.FAIL, StringUtils.substringBefore(str, "(Session info:"));
                rep.endTest(test);
                rep.flush();
            } catch (NullPointerException e) {
                test.log(LogStatus.FAIL, result.getThrowable());
                rep.endTest(test);
                rep.flush();
            }
            test.log(LogStatus.INFO, StringUtils.substringBefore("Updated with Fail status", "(Session info:"));
        }
        if (result.getStatus() == ITestResult.SUCCESS)
            test.log(LogStatus.INFO, StringUtils.substringBefore("Updated with Pass status", "(Session info:"));

    }

    /**
     * Reporting
     */

    public void reportPass(String msg) {
        test.log(LogStatus.PASS, msg);
    }

    public void reportFailure(String msg) {
        test.log(LogStatus.FAIL, msg);
        takeScreenShot();
        Assert.fail(msg);
    }


    public void takeScreenShot() {
        Date d = new Date();
        String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(scrFile, new File(System.getProperty("user.dir") + "//screenshots//" + screenshotFile));

        } catch (IOException e) {

        }
        test.log(LogStatus.INFO, test.addScreenCapture(System.getProperty("user.dir") + "//screenshots//" + screenshotFile));

    }
}

	

