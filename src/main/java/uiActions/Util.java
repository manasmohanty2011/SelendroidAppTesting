
package uiActions;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import frameworkdata.ExtentManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import testbase.Base;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Test
public class Util extends Base {

    public static AndroidDriver<AndroidElement> driver;
    public ExtentReports rep = ExtentManager.getInstance();
    public static ExtentTest test;
//    private static Logger log = LogManager.getLogger(Util.class.getName());
    private static boolean testFailureFlag = true;

    /**
     * Click Action
     */

    public void click(AndroidDriver<AndroidElement> driver, WebElement element) {
        waitForElementVisibility(driver, element, 15);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public void doubleclick(AndroidDriver<AndroidElement> driver, WebElement element) {
        waitForElementVisibility(driver, element, 15);
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }

    /**
     * Hover and Click Action
     */

    public void hover(AndroidDriver<AndroidElement> driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public void hoverAndClick(AndroidDriver<AndroidElement> driver,WebElement elementToHover,WebElement elementToClick) {
        Actions action = new Actions(driver);
        action.moveToElement(elementToHover).click(elementToClick).build().perform();
    }

    public void clickOnCanvas(AndroidDriver<AndroidElement> driver,WebElement elementToHover) {
        Actions builder = new Actions(driver);
        Action drawAction = builder.moveToElement(elementToHover,-30,-10)
                .click()
                .build();
        drawAction.perform();
    }

    /**
     * Explicit Wait/Expected Conditions
     */

    public void waitForElementVisibility(AndroidDriver<AndroidElement> driver, WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.visibilityOf(element));
            } catch (Exception e1) {
                Assert.assertFalse(false, "Failed the test - " + e.getMessage());
            }
        }
    }

    public void waitForElementClickable(AndroidDriver<AndroidElement> driver, WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element));
            } catch (Exception e1) {
                Assert.assertFalse(false, "Failed the test - " + e.getMessage());
            }
        }
    }

    public void verifyIfAlertPresentThenDismiss(AndroidDriver<AndroidElement> driver, WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element));
            } catch (Exception e1) {
                Assert.assertFalse(false, "Failed the test - " + e.getMessage());
            }
        }
    }

    public String ifAlertPresentCloseIt(AndroidDriver<AndroidElement> driver, WebElement webElement) {
        String currentContext = driver.getContext();
        driver.context("NATIVE_APP");
        String res = "";
        if (webElement.isDisplayed()) {
            webElement.click();
            res= "Alert closed";
        } else {
            res= "Alert not present!";
        }
        driver.context(currentContext);
        return res;
    }

    public void waitToLoad() throws InterruptedException {
        Thread.sleep(3000);
    }

    public void waitToLoadWebElement(int i) {
        try {
            Thread.sleep(i);
        } catch (Exception e) {

        }
    }

    /**
     * Create Dynamic Test data
     */

    public String returnTodayDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String returnOneDayAdditionDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_MONTH, 1);
        String newDate = sdf.format(c.getTime());
        return newDate;
    }

    public String returnAdditionalDayDate(int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, day);
        String newDate = sdf.format(c.getTime());
        return newDate;
    }

    public String returnOneMonthDayAdditionDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_MONTH, 30);
        String newDate = sdf.format(c.getTime());
        return newDate;
    }

    public String getRandomNumber() {
        return "" + 1 + (int) (Math.random() * 1000);
    }

    public static void setTestFailureFlag(boolean testFailureFlag) {
        Util.testFailureFlag = testFailureFlag;
    }

}


