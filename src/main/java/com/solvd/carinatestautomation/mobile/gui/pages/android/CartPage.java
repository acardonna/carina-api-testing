package com.solvd.carinatestautomation.mobile.gui.pages.android;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.solvd.carinatestautomation.mobile.gui.pages.common.CartPageBase;
import com.solvd.carinatestautomation.mobile.gui.pages.common.ReviewOrderPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.factory.DeviceType.Type;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = Type.ANDROID_PHONE, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase implements IMobileUtils {

    @FindBy(id = "cart_list")
    private ExtendedWebElement cartList;

    @FindBy(id = "empty_cart_text")
    private ExtendedWebElement emptyCartText;

    @FindBy(id = "continue_to_checkout")
    private ExtendedWebElement continueToCheckoutBtn;

    @FindBy(id = "continue_to_shopping")
    private ExtendedWebElement continueShoppingBtn;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return cartList.isElementPresent(5) || emptyCartText.isElementPresent(5);
    }

    @Override
    public boolean isEmpty() {
        return isEmptyMessageVisible();
    }

    @Override
    public boolean isPromoControlsVisible() {
        if (isEmptyMessageVisible()) {
            return false;
        }
        return continueToCheckoutBtn.isElementPresent(0);
    }

    private boolean isEmptyMessageVisible() {
        if (!emptyCartText.isElementPresent(0)) {
            return false;
        }
        String text = emptyCartText.getText();
        return text != null && text.contains("Cart is empty");
    }

    @Override
    public void openCartItem(int index) {
        ExtendedWebElement item = findExtendedWebElement(org.openqa.selenium.By.xpath(
                "//*[@resource-id='com.epam.mobitru:id/cart_list']/android.view.ViewGroup[@index='" + index + "']"));
        if (item != null) {
            item.click();
        }
    }

    @Override
    public ReviewOrderPageBase continueToCheckout() {
        swipeToElement(continueToCheckoutBtn, 5);
        if (continueToCheckoutBtn.isElementPresent(3)) {
            continueToCheckoutBtn.click();
        }
        return initPage(getDriver(), ReviewOrderPageBase.class);
    }

    private void swipeToElement(ExtendedWebElement el, int maxSwipes) {
        int attempts = maxSwipes;
        while (el != null && !el.isElementPresent(1) && attempts-- > 0) {
            swipeUp(500);
        }
    }
}
