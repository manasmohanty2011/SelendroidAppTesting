package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import testbase.Base;
import uiActions.Util;

public class RegisterUserPage extends Base {

    Util util = new Util();
    AndroidDriver<AndroidElement> driver;

    public RegisterUserPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "io.selendroid.testapp:id/inputUsername")
    public WebElement userNameInputBox;

    @AndroidFindBy(id = "io.selendroid.testapp:id/inputEmail")
    public WebElement emailInputBox;

    @AndroidFindBy(id = "io.selendroid.testapp:id/inputPassword")
    public WebElement passwordInputBox;

    @AndroidFindBy(id = "io.selendroid.testapp:id/input_preferedProgrammingLanguage")
    public WebElement programmingLanguageInputBox;

    @AndroidFindBy(id = "io.selendroid.testapp:id/inputName")
    public WebElement nameInputBox;

    @AndroidFindBy(id = "io.selendroid.testapp:id/input_adds")
    public WebElement addCheck;

    @AndroidFindBy(id = "io.selendroid.testapp:id/btnRegisterUser")
    public WebElement registerButton;

    public String verifyRegisterUserPage() {
        try {
            util.waitForElementClickable(driver, userNameInputBox, 30);
            driver.hideKeyboard();
            userNameInputBox.isDisplayed();
            emailInputBox.isDisplayed();
            passwordInputBox.isDisplayed();
            programmingLanguageInputBox.isDisplayed();
            nameInputBox.isDisplayed();
            addCheck.isDisplayed();
            registerButton.isDisplayed();
            return "Register user page display";
        } catch (Exception e) {
            return "Register user page not display - " + e.getCause();
        }
    }

    public String enterUserName(String data) {
        try {
            userNameInputBox.clear();
            userNameInputBox.sendKeys(data);
            return "Enter username";
        } catch (Exception e) {
            return "Unable to enter username - " + e.getCause();
        }
    }

    public String enterEmail(String data) {
        try {
            emailInputBox.clear();
            emailInputBox.sendKeys(data);
            return "Enter email";
        } catch (Exception e) {
            return "Unable to enter email - " + e.getCause();
        }
    }

    public String enterPassword(String data) {
        try {
            passwordInputBox.clear();
            passwordInputBox.sendKeys(data);
            return "Enter password";
        } catch (Exception e) {
            return "Unable to enter password - " + e.getCause();
        }
    }

    public String enterName(String data) {
        try {
            nameInputBox.clear();
            nameInputBox.sendKeys(data);
            return "Enter name";
        } catch (Exception e) {
            return "Unable to enter name - " + e.getCause();
        }
    }

    public String checkIAcceptAdd(String data) {
        try {
            if(data.contains("Yes") || data.contains("yes")) {
                addCheck.click();
            }
            return "Check i accept adds";
        } catch (Exception e) {
            return "Unable to check i accept adds - " + e.getCause();
        }
    }

    public String clickOnRegisterUser() {
        try {
            registerButton.click();
            return "Click on Register User";
        } catch (Exception e) {
            return "Unable to click on Register User - " + e.getCause();
        }
    }
}
