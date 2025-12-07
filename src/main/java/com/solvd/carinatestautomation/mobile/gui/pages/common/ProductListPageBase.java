package com.solvd.carinatestautomation.mobile.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class ProductListPageBase extends AbstractPage {

    public ProductListPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isPageOpened();

    public abstract void openSortDialog();

    public abstract ProductListPageBase sortBy(String optionText);

    public abstract void addProductToCart(int index);

    public abstract void removeProductFromCartIfPresent(int index);

    public abstract int getCartCount();

    public abstract CartPageBase openCart();

    public abstract AccountPageBase openAccount();

    public abstract String getCategoryTitle();

    public abstract OrdersPageBase openOrders();
}
