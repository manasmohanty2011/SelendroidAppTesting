package testcases;

import com.relevantcodes.extentreports.LogStatus;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ConfirmRegisterUserPage;
import pages.LandingPage;
import pages.RegisterUserPage;
import testbase.Base;
import uiActions.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends Base {

    LandingPage landingPage;
    RegisterUserPage registerUserPage;
    ConfirmRegisterUserPage confirmRegisterUserPage;
    Util util = new Util();

    @BeforeTest
    public void driverInitialize() throws IOException {
        test = rep.startTest("RegisterUser");
        test.log(LogStatus.INFO, "Starting the test RegisterUser - Verify all section links");

        initializeDriver();

        /**
         * Driver references
         */
        landingPage = new LandingPage(driver);
        registerUserPage = new RegisterUserPage(driver);
        confirmRegisterUserPage = new ConfirmRegisterUserPage(driver);
        landingPage.clickOnContinueButton();
        landingPage.clickOnOkButton();
    }

    @Test(dataProvider = "excel")
    public void RegisterUser(Map<Object, Object> map) throws InterruptedException {

        Assert.assertEquals(landingPage.verifyLandingPage(), "Application landing page display");
        test.log(LogStatus.PASS, "Application landing page display.");
        util.takeScreenShot();

        Assert.assertEquals(landingPage.clickOnNewRegisterButton(), "Click on new register button");
        Assert.assertEquals(registerUserPage.verifyRegisterUserPage(), "Register user page display");
        test.log(LogStatus.PASS, "Click on new register button and verified register user page display.");
        util.takeScreenShot();

        Assert.assertEquals(registerUserPage.enterUserName(map.get("UserName").toString()), "Enter username");
        Assert.assertEquals(registerUserPage.enterEmail(map.get("Email").toString()), "Enter email");
        Assert.assertEquals(registerUserPage.enterPassword(map.get("Password").toString()), "Enter password");
        Assert.assertEquals(registerUserPage.enterName(map.get("Name").toString()), "Enter name");
        Assert.assertEquals(registerUserPage.checkIAcceptAdd(map.get("AcceptAdds").toString()), "Check i accept adds");
        test.log(LogStatus.PASS, "Data entered from datatable");
        util.takeScreenShot();

        Assert.assertEquals(registerUserPage.clickOnRegisterUser(), "Click on Register User");
        Assert.assertEquals(confirmRegisterUserPage.verifyRegisterUserPage(), "Confirm register user page display");
        test.log(LogStatus.PASS, "Click on Register User and verified Confirm register user page display.");
        util.takeScreenShot();

        softAssert.assertEquals(confirmRegisterUserPage.getUserName(), map.get("UserName").toString());
        softAssert.assertEquals(confirmRegisterUserPage.getUserEmail(), map.get("Email").toString());
        softAssert.assertEquals(confirmRegisterUserPage.getPassword(), map.get("Password").toString());
        softAssert.assertEquals(confirmRegisterUserPage.getName(), map.get("Name").toString());
        softAssert.assertEquals(confirmRegisterUserPage.getAcceptAdds(), map.get("AcceptAdds").toString());
        test.log(LogStatus.PASS, "Verified data on Confirm register user page display.");
        util.takeScreenShot();

        softAssert.assertEquals(confirmRegisterUserPage.clickOnRegisterUser(), "Click on Register User button");
        landingPage = new LandingPage(driver);
        Assert.assertEquals(landingPage.verifyLandingPage(), "Application landing page display");
        test.log(LogStatus.PASS, "Click on Register User button and verified navigates to landing page.");
        util.takeScreenShot();
    }

    @DataProvider(name = "excel")
    public Object[][] dataSupplier() throws IOException {

        File file = new File(System.getProperty("user.dir") + "/testdata/testData.xlsx");
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet(this.getClass().getSimpleName());
        wb.close();
        int lastRowNum = getRowCount(sheet);
        int lastCellNum = sheet.getRow(0).getLastCellNum();
        Object[][] obj = new Object[lastRowNum][1];
        for (int i = 0, k = 0; i < sheet.getLastRowNum(); i++, k++) {
            Map<Object, Object> datamap = new HashMap<Object, Object>();
            datamap.put("RowNo", (i + 2));
            for (int j = 0; j < lastCellNum; j++) {
                datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
            }
            obj[k][0] = datamap;
        }
        return obj;
    }

    public int getRowCount(XSSFSheet sheet) {
        int cnt = 0;
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            cnt++;
        }
        return cnt - 1;
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
        driver = null;
        rep.endTest(test);
        rep.flush();
    }
}
