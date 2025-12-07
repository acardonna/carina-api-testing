package com.solvd.carinatestautomation.mobile.gui.pages.android;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.solvd.carinatestautomation.mobile.gui.pages.common.OrdersPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.factory.DeviceType.Type;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = Type.ANDROID_PHONE, parentClass = OrdersPageBase.class)
public class OrdersPage extends OrdersPageBase {

    @FindBy(xpath = "//*[@text='Completed' or contains(@text,'Completed')]")
    private ExtendedWebElement completedTab;

    @FindBy(xpath = "(//androidx.recyclerview.widget.RecyclerView//android.view.ViewGroup)[1]")
    private ExtendedWebElement firstOrderItem;

    @FindBy(id = "title")
    private ExtendedWebElement orderTitle;

    public OrdersPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return completedTab.isElementPresent(5) || firstOrderItem.isElementPresent(5);
    }

    @Override
    public boolean hasCompletedTab() {
        return completedTab.isElementPresent(5);
    }

    @Override
    public void openFirstOrder() {
        if (firstOrderItem.isElementPresent(5)) {
            firstOrderItem.click();
            pause(1);
        }
    }

    @Override
    public boolean isOrderDetailShown() {
        return orderTitle.isElementPresent(5);
    }
}
