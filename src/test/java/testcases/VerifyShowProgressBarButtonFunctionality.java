package testcases;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LandingPage;
import testbase.Base;
import uiActions.Util;

import java.io.IOException;

public class VerifyShowProgressBarButtonFunctionality extends Base {

    LandingPage landingPage;
    Util util = new Util();

    @BeforeTest
    public void driverInitialize() throws IOException {
        test = rep.startTest("VerifyShowProgressBarButtonFunctionality");
        test.log(LogStatus.INFO, "Starting the test VerifyShowProgressBarButtonFunctionality - Verify all section links");

        initializeDriver();

        /**
         * Driver references
         */
        landingPage = new LandingPage(driver);
        landingPage.clickOnContinueButton();
        landingPage.clickOnOkButton();
    }

    @Test()
    public void VerifyShowProgressBarButtonFunctionality() throws InterruptedException {

        Assert.assertEquals(landingPage.verifyLandingPage(), "Application landing page display");
        test.log(LogStatus.PASS, "Application landing page display.");
        util.takeScreenShot();

        Assert.assertEquals(landingPage.clickOnShowProgressBarForWhileButton(), "Click on Show Progress Bar for a while");
        test.log(LogStatus.PASS, "Click on Show Progress Bar for a while.");
        util.takeScreenShot();

        Assert.assertEquals(landingPage.verifyProgressbar(), "Progress bar display");
        test.log(LogStatus.PASS, "Progress bar display.");
        util.takeScreenShot();
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
        driver = null;
        rep.endTest(test);
        rep.flush();
    }
}
