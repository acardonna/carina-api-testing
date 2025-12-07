package com.solvd.carinatestautomation.mobile.gui.pages.android;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.solvd.carinatestautomation.mobile.gui.pages.common.AccountPageBase;
import com.solvd.carinatestautomation.mobile.gui.pages.common.LoginPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.factory.DeviceType.Type;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = Type.ANDROID_PHONE, parentClass = AccountPageBase.class)
public class AccountPage extends AccountPageBase {

    @FindBy(id = "logout")
    private ExtendedWebElement logoutBtn;

    @FindBy(id = "login_signin")
    private ExtendedWebElement signInBtn;

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAccountScreenOpened() {
        return logoutBtn.isElementPresent(5);
    }

    @Override
    public LoginPageBase logout() {
        logoutBtn.click();
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
        loginPage.isPageOpened();
        return loginPage;
    }
}
