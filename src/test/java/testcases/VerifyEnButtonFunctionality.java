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

public class VerifyEnButtonFunctionality extends Base {

    LandingPage landingPage;
    Util util = new Util();

    @BeforeTest
    public void driverInitialize() throws IOException {
        test = rep.startTest("VerifyEnButtonFunctionality");
        test.log(LogStatus.INFO, "Starting the test VerifyEnButtonFunctionality - Verify all section links");

        initializeDriver();

        /**
         * Driver references
         */
        landingPage = new LandingPage(driver);
        landingPage.clickOnContinueButton();
        landingPage.clickOnOkButton();
    }

    @Test()
    public void VerifyEnButtonFunctionality() throws InterruptedException {

        Assert.assertEquals(landingPage.verifyLandingPage(), "Application landing page display");
        test.log(LogStatus.PASS, "Application landing page display.");
        util.takeScreenShot();

        Assert.assertEquals(landingPage.clickOnEnButton(), "Click on EN Button");
        test.log(LogStatus.PASS, "Click on EN Button.");
        util.takeScreenShot();

        Assert.assertEquals(landingPage.verifyEnAlertMessage(), "Alert closed");
        test.log(LogStatus.PASS, "Alert closed.");
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
