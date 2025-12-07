package com.solvd.carinatestautomation.mobile.gui.pages.android;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.solvd.carinatestautomation.mobile.gui.pages.common.AccountPageBase;
import com.solvd.carinatestautomation.mobile.gui.pages.common.CartPageBase;
import com.solvd.carinatestautomation.mobile.gui.pages.common.OrdersPageBase;
import com.solvd.carinatestautomation.mobile.gui.pages.common.ProductListPageBase;
import com.solvd.carinatestautomation.mobile.gui.pages.common.SortDialogPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.factory.DeviceType.Type;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = Type.ANDROID_PHONE, parentClass = ProductListPageBase.class)
public class ProductListPage extends ProductListPageBase implements IMobileUtils {

    @FindBy(id = "category")
    private ExtendedWebElement categoryTitle;

    @FindBy(id = "product_list")
    private ExtendedWebElement productList;

    @FindBy(id = "cart_title")
    private ExtendedWebElement cartTitle;

    @FindBy(id = "sortBy")
    private ExtendedWebElement sortBtn;

    @FindBy(xpath = "//*[@text='Account']")
    private ExtendedWebElement accountTab;

    @FindBy(xpath = "//*[@text='Shop']")
    private ExtendedWebElement shopTab;

    @FindBy(xpath = "//*[@text='Orders']")
    private ExtendedWebElement ordersTab;

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return categoryTitle.isElementPresent(5) && productList.isElementPresent(5);
    }

    @Override
    public void openSortDialog() {
        sortBtn.click();
    }

    @Override
    public ProductListPageBase sortBy(String optionText) {
        openSortDialog();
        SortDialogPageBase dialog = initPage(getDriver(), SortDialogPageBase.class);
        dialog.selectOption(optionText);
        return dialog.apply();
    }

    @Override
    public void addProductToCart(int index) {
        ExtendedWebElement addBtn = findExtendedWebElement(org.openqa.selenium.By.xpath(
                "//*[@resource-id='com.epam.mobitru:id/product_list']" +
                        "//android.view.ViewGroup[@index='" + index + "']//*[@resource-id='com.epam.mobitru:id/addToCart']"));
        swipeToElement(addBtn, 5);
        addBtn.click();
        pause(1);
    }

    @Override
    public void removeProductFromCartIfPresent(int index) {
        ExtendedWebElement removeBtn = findExtendedWebElement(org.openqa.selenium.By.xpath(
                "//*[@resource-id='com.epam.mobitru:id/product_list']" +
                        "//android.view.ViewGroup[@index='" + index + "']//*[@resource-id='com.epam.mobitru:id/removeFromCart']"));
        if (removeBtn != null && removeBtn.isElementPresent(1)) {
            removeBtn.click();
            pause(1);
        }
    }

    @Override
    public int getCartCount() {
        String text = cartTitle.getText();
        Matcher matcher = Pattern.compile("\\((\\d+)\\)").matcher(text);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 0;
    }

    @Override
    public CartPageBase openCart() {
        cartTitle.click();
        return initPage(getDriver(), CartPageBase.class);
    }

    @Override
    public AccountPageBase openAccount() {
        accountTab.click();
        return initPage(getDriver(), AccountPageBase.class);
    }

    @Override
    public String getCategoryTitle() {
        return categoryTitle.getText();
    }

    public OrdersPageBase openOrders() {
        ordersTab.click();
        return initPage(getDriver(), OrdersPageBase.class);
    }

    private void swipeToElement(ExtendedWebElement el, int maxSwipes) {
        int attempts = maxSwipes;
        while (el != null && !el.isElementPresent(1) && attempts-- > 0) {
            swipeUp(500);
        }
    }
}
