package com.solvd.carinatestautomation.mobile.gui.pages.android;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.solvd.carinatestautomation.mobile.gui.pages.common.ProductListPageBase;
import com.solvd.carinatestautomation.mobile.gui.pages.common.SortDialogPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.factory.DeviceType.Type;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = Type.ANDROID_PHONE, parentClass = SortDialogPageBase.class)
public class SortDialogPage extends SortDialogPageBase {

    @FindBy(id = "apply")
    private ExtendedWebElement applyBtn;

    public SortDialogPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void selectOption(String text) {
        ExtendedWebElement option = findExtendedWebElement(org.openqa.selenium.By.xpath("//*[@text='" + text + "']"));
        option.click();
    }

    @Override
    public ProductListPageBase apply() {
        applyBtn.click();
        return initPage(getDriver(), ProductListPageBase.class);
    }
}
