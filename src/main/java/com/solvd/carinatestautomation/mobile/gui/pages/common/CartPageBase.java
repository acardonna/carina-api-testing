package com.solvd.carinatestautomation.mobile.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class CartPageBase extends AbstractPage {

    public CartPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isPageOpened();

    public abstract boolean isEmpty();

    public abstract boolean isPromoControlsVisible();

    public abstract void openCartItem(int index);

    public abstract ReviewOrderPageBase continueToCheckout();
}
