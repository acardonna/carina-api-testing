package com.solvd.carinatestautomation.mobile.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class OrdersPageBase extends AbstractPage {

    public OrdersPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isPageOpened();

    public abstract boolean hasCompletedTab();

    public abstract void openFirstOrder();

    public abstract boolean isOrderDetailShown();
}
