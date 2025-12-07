package com.solvd.carinatestautomation.mobile.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class LoginPageBase extends AbstractPage {

    public LoginPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void typeEmail(String email);

    public abstract void typePassword(String password);

    public abstract ProductListPageBase tapSignIn();

    public abstract ProductListPageBase tapSignInWithCorrectUser();

    public abstract void tapSignInExpectingError();

    public abstract boolean isErrorVisible();

    public abstract boolean isPageOpened();
}
