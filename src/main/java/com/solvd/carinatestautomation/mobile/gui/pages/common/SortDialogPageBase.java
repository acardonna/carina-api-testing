package com.solvd.carinatestautomation.mobile.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class SortDialogPageBase extends AbstractPage {

    public SortDialogPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void selectOption(String text);

    public abstract ProductListPageBase apply();
}
