package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import testbase.Base;
import uiActions.Util;

public class ConfirmRegisterUserPage extends Base {

    Util util = new Util();
    AndroidDriver<AndroidElement> driver;

    public ConfirmRegisterUserPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="io.selendroid.testapp:id/label_username_data")
    public WebElement userName;

    @AndroidFindBy(id="io.selendroid.testapp:id/label_password_data")
    public WebElement password;

    @AndroidFindBy(id="io.selendroid.testapp:id/label_email_data")
    public WebElement email;

    @AndroidFindBy(id="io.selendroid.testapp:id/label_name_data")
    public WebElement name;

    @AndroidFindBy(id="io.selendroid.testapp:id/label_preferedProgrammingLanguage_data")
    public WebElement programmingLanguage;

    @AndroidFindBy(id="io.selendroid.testapp:id/label_acceptAdds_data")
    public WebElement acceptAdds;

    @AndroidFindBy(id="io.selendroid.testapp:id/buttonRegisterUser")
    public WebElement registerUserButton;

    public String verifyRegisterUserPage() {
        try {
            util.waitForElementClickable(driver, registerUserButton, 15);
            registerUserButton.isDisplayed();
            userName.isDisplayed();
            password.isDisplayed();
            email.isDisplayed();
            name.isDisplayed();
            programmingLanguage.isDisplayed();
            acceptAdds.isDisplayed();
            return "Confirm register user page display";
        } catch (Exception e) {
            return "Confirm register user page not display - " + e.getCause();
        }
    }

    public String getUserName() {
        try {
            return userName.getText();
        } catch (Exception e) {
            return "Unable to get username - " + e.getCause();
        }
    }

    public String getPassword() {
        try {
            return password.getText();
        } catch (Exception e) {
            return "Unable to get password - " + e.getCause();
        }
    }

    public String getUserEmail() {
        try {
            return email.getText();
        } catch (Exception e) {
            return "Unable to get email - " + e.getCause();
        }
    }

    public String getName() {
        try {
            return name.getText();
        } catch (Exception e) {
            return "Unable to get name - " + e.getCause();
        }
    }

    public String geProgrammingLanguage() {
        try {
            return programmingLanguage.getText();
        } catch (Exception e) {
            return "Unable to get programming language - " + e.getCause();
        }
    }

    public String getAcceptAdds() {
        try {
            return acceptAdds.getText();
        } catch (Exception e) {
            return "Unable to get accept adds - " + e.getCause();
        }
    }

    public String clickOnRegisterUser() {
        try {
            registerUserButton.click();
            return "Click on Register User button";
        } catch (Exception e) {
            return "Unable to click on Register User button - " + e.getCause();
        }
    }
}
