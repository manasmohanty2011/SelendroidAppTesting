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

public class VerifyDisplayTextViewButtonFunctionality extends Base {

    LandingPage landingPage;
    Util util = new Util();

    @BeforeTest
    public void driverInitialize() throws IOException {
        test = rep.startTest("VerifyDisplayTextViewButtonFunctionality");
        test.log(LogStatus.INFO, "Starting the test VerifyDisplayTextViewButtonFunctionality - Verify all section links");

        initializeDriver();

        /**
         * Driver references
         */
        landingPage = new LandingPage(driver);
        landingPage.clickOnContinueButton();
        landingPage.clickOnOkButton();
    }

    @Test()
    public void VerifyDisplayTextViewButtonFunctionality() throws InterruptedException {

        Assert.assertEquals(landingPage.verifyLandingPage(), "Application landing page display");
        test.log(LogStatus.PASS, "Application landing page display.");
        util.takeScreenShot();

        Assert.assertEquals(landingPage.clickOnDisplayTextViewButton(), "Click on Display Text View Button");
        test.log(LogStatus.PASS, "Click on Display Text View Button.");
        util.takeScreenShot();

        Assert.assertEquals(landingPage.verifyDisplayTextView(), "Text is sometimes displayed");
        test.log(LogStatus.PASS, "Text displayed correctly.");
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
