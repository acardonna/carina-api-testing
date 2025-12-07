package com.solvd.carinatestautomation.web;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.carinatestautomation.gui.pages.desktop.CartPage;
import com.solvd.carinatestautomation.gui.pages.desktop.CoffeeCartPage;
import com.zebrunner.carina.core.IAbstractTest;

public class WebTests implements IAbstractTest {

    @Test
    public void testPageIsOpened() {
        CoffeeCartPage page = new CoffeeCartPage(getDriver());
        page.open();
        Assert.assertTrue(page.isPageOpened(), "Coffee Cart page is not opened");
    }

    @Test
    public void testCoffeeListPresence() {
        CoffeeCartPage page = new CoffeeCartPage(getDriver());
        page.open();
        Assert.assertTrue(page.isCoffeePresent("Espresso"), "Espresso not found");
        Assert.assertTrue(page.isCoffeePresent("Cappuccino"), "Cappuccino not found");
        Assert.assertTrue(page.isCoffeePresent("Mocha"), "Mocha not found");
    }

    @Test
    public void testAddEspresso() {
        CoffeeCartPage page = new CoffeeCartPage(getDriver());
        page.open();
        page.selectCoffee("Espresso");
        Assert.assertTrue(page.getTotalAmount().contains("$10.00"), "Total amount incorrect for Espresso");
    }

    @Test
    public void testAddCappuccino() {
        CoffeeCartPage page = new CoffeeCartPage(getDriver());
        page.open();
        page.selectCoffee("Cappuccino");
        Assert.assertTrue(page.getTotalAmount().contains("$19.00"), "Total amount incorrect for Cappuccino");
    }

    @Test
    public void testAddMultipleItems() {
        CoffeeCartPage page = new CoffeeCartPage(getDriver());
        page.open();
        page.selectCoffee("Espresso");
        page.selectCoffee("Mocha"); // because $10 + $8 = $18
        Assert.assertTrue(page.getTotalAmount().contains("$18.00"), "Total amount incorrect for multiple items");
    }

    @Test
    public void testAddEspressoAndCheckCart() {
        CoffeeCartPage page = new CoffeeCartPage(getDriver());
        page.open();
        page.selectCoffee("Espresso");
        CartPage cartPage = page.openCartPage();
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page is not opened");
    }

    @Test
    public void testTotalButtonInitialState() {
        CoffeeCartPage page = new CoffeeCartPage(getDriver());
        page.open();
        Assert.assertTrue(page.getTotalAmount().contains("$0.00"), "Initial total should be $0.00");
    }

    @Test
    public void testAddEspressoMacchiato() {
        CoffeeCartPage page = new CoffeeCartPage(getDriver());
        page.open();
        page.selectCoffee("Espresso Macchiato");
        Assert.assertTrue(page.getTotalAmount().contains("$12.00"), "Total amount incorrect for Espresso Macchiato");
    }

    @Test
    public void testAddFlatWhite() {
        CoffeeCartPage page = new CoffeeCartPage(getDriver());
        page.open();
        page.selectCoffee("Flat White");
        Assert.assertTrue(page.getTotalAmount().contains("$18.00"), "Total amount incorrect for Flat White");
    }

    @Test
    public void testCheckoutProcess() {
        CoffeeCartPage page = new CoffeeCartPage(getDriver());
        page.open();
        page.selectCoffee("Espresso");
        page.clickTotal();

        page.typeName("John Doe");
        page.typeEmail("john.doe@example.com");
        page.submitPayment();

        Assert.assertTrue(page.getSuccessMessage().contains("Thanks for your purchase"), "Success message not displayed");
    }
}
