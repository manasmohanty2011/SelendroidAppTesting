package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import testbase.Base;
import uiActions.Util;

public class LandingPage extends Base {

    Util util = new Util();
    AndroidDriver<AndroidElement> driver;

    public LandingPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @AndroidFindBy(id = "com.android.permissioncontroller:id/continue_button")
    public WebElement continueButton;

    @AndroidFindBy(id = "android:id/button1")
    public WebElement okButton;

    @AndroidFindBy(id = "io.selendroid.testapp:id/startUserRegistration")
    public WebElement newRegisterButton;

    @AndroidFindBy(id = "io.selendroid.testapp:id/buttonTest")
    public WebElement enButton;

    @AndroidFindBy(id = "android:id/button2")
    public WebElement enButtonAlertNoButton;

    @AndroidFindBy(id = "io.selendroid.testapp:id/visibleButtonTest")
    public WebElement displayTextViewButton;

    @AndroidFindBy(id = "io.selendroid.testapp:id/visibleTextView")
    public WebElement displayTextView;

    @AndroidFindBy(id = "io.selendroid.testapp:id/waitingButtonTest")
    public WebElement showProgressBarForWhileButton;

    @AndroidFindBy(id = "android:id/progress")
    public WebElement progressBar;

    public String verifyLandingPage() {
        try {
            util.waitForElementClickable(driver, newRegisterButton, 15);
            newRegisterButton.isDisplayed();
            enButton.isDisplayed();
            displayTextViewButton.isDisplayed();
            showProgressBarForWhileButton.isDisplayed();
            return "Application landing page display";
        } catch (Exception e) {
            return "Application landing page not display - " + e.getCause();
        }
    }

    public String clickOnNewRegisterButton() {
        try {
            newRegisterButton.click();
            return "Click on new register button";
        } catch (Exception e) {
            return "Unable to click on click on new register button - " + e.getCause();
        }
    }

    public String clickOnEnButton() {
        try {
            enButton.click();
            return "Click on EN Button";
        } catch (Exception e) {
            return "Unable to click on EN Button - " + e.getCause();
        }
    }

    public String verifyEnAlertMessage() {
        return util.ifAlertPresentCloseIt(driver,enButtonAlertNoButton);
    }

    public String clickOnDisplayTextViewButton() {
        try {
            displayTextViewButton.click();
            return "Click on Display Text View Button";
        } catch (Exception e) {
            return "Unable to click on Display Text View Button - " + e.getCause();
        }
    }

    public String verifyDisplayTextView() {
        return displayTextView.getText();
    }

    public String clickOnShowProgressBarForWhileButton() {
        try {
            showProgressBarForWhileButton.click();
            return "Click on Show Progress Bar for a while";
        } catch (Exception e) {
            return "Unable to click on Show Progress Bar for a while - " + e.getCause();
        }
    }

    public String verifyProgressbar() {
        try {
            util.waitForElementClickable(driver, progressBar, 30);
            if(progressBar.isDisplayed()) {
                return "Progress bar display";
            } else {
                return "Progress bar not display";
            }
        } catch (Exception e) {
            return "Progress bar not display" +e.getCause();
        }
    }

    public String clickOnContinueButton() {
        try {
            util.waitForElementClickable(driver, continueButton, 15);
            if(continueButton.isDisplayed()) {
                continueButton.click();
                return "Continue Button clicked";
            } else {
                return "Continue Button not displayed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Continue Button not displayed " +e.getCause();
        }
    }

    public String clickOnOkButton() {
        try {
            util.waitForElementClickable(driver, okButton, 15);
            if(okButton.isDisplayed()) {
                okButton.click();
                return "Ok Button clicked";
            } else {
                return "Ok Button not displayed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Ok Button not displayed " +e.getCause();
        }
    }



}
