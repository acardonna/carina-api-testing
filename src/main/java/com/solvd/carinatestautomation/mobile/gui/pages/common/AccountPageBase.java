package com.solvd.carinatestautomation.mobile.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class AccountPageBase extends AbstractPage {

    public AccountPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isAccountScreenOpened();

    public abstract LoginPageBase logout();
}
