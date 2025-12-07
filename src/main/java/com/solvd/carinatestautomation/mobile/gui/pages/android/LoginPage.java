package com.solvd.carinatestautomation.mobile.gui.pages.android;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.solvd.carinatestautomation.mobile.gui.pages.common.LoginPageBase;
import com.solvd.carinatestautomation.mobile.gui.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.factory.DeviceType.Type;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = Type.ANDROID_PHONE, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase implements IMobileUtils {

    @FindBy(id = "login_email")
    private ExtendedWebElement emailFieldContainer;

    @FindBy(id = "login_password")
    private ExtendedWebElement passwordFieldContainer;

    @FindBy(xpath = "//*[@resource-id='com.epam.mobitru:id/login_email']//android.widget.EditText")
    private ExtendedWebElement emailInput;

    @FindBy(xpath = "//*[@resource-id='com.epam.mobitru:id/login_password']//android.widget.EditText")
    private ExtendedWebElement passwordInput;

    @FindBy(id = "login_signin")
    private ExtendedWebElement signInBtn;

    @FindBy(xpath = "//*[@text='Sign in with correct user']")
    private ExtendedWebElement signInWithCorrectUserBtn;

    @FindBy(xpath = "//*[@text='Incorrect email or password' or contains(@text,'Incorrect email')]")
    private ExtendedWebElement loginError;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void typeEmail(String email) {
        waitForLoginForm();
        if (emailInput.isElementPresent(2)) {
            emailInput.click();
            emailInput.type(email);
            return;
        }
        emailFieldContainer.type(email);
    }

    @Override
    public void typePassword(String password) {
        waitForLoginForm();
        if (passwordInput.isElementPresent(2)) {
            passwordInput.click();
            passwordInput.type(password);
            return;
        }
        passwordFieldContainer.type(password);
    }

    private void waitForLoginForm() {
        int retries = 5;
        while (!emailInput.isElementPresent(1) && retries-- > 0) {
            pause(1);
        }
    }

    @Override
    public ProductListPageBase tapSignIn() {
        signInBtn.click();
        ProductListPageBase listPage = initPage(getDriver(), ProductListPageBase.class);
        int retries = 5;
        while (!listPage.isPageOpened() && retries-- > 0) {
            pause(1);
        }
        return listPage;
    }

    @Override
    public ProductListPageBase tapSignInWithCorrectUser() {
        signInWithCorrectUserBtn.click();
        ProductListPageBase listPage = initPage(getDriver(), ProductListPageBase.class);
        int retries = 5;
        while (!listPage.isPageOpened() && retries-- > 0) {
            pause(1);
        }
        return listPage;
    }

    @Override
    public void tapSignInExpectingError() {
        signInBtn.click();
        int retries = 5;
        while (!loginError.isElementPresent(1) && retries-- > 0) {
            pause(1);
        }
    }

    @Override
    public boolean isErrorVisible() {
        return loginError.isElementPresent(5);
    }

    @Override
    public boolean isPageOpened() {
        return signInBtn.isElementPresent(5);
    }
}
