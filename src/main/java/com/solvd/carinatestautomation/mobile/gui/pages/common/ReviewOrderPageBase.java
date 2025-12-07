package com.solvd.carinatestautomation.mobile.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class ReviewOrderPageBase extends AbstractPage {

    public ReviewOrderPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isPageOpened();

    public abstract void fillUserInfo(String firstName, String lastName, String address);

    public abstract void saveUserInfo();

    public abstract void confirmOrder();

    public abstract boolean isConfirmationShown();

    public abstract void goBackToShop();
}
