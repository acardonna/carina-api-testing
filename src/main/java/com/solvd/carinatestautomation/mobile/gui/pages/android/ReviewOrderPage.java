package com.solvd.carinatestautomation.mobile.gui.pages.android;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.solvd.carinatestautomation.mobile.gui.pages.common.ReviewOrderPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.factory.DeviceType.Type;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = Type.ANDROID_PHONE, parentClass = ReviewOrderPageBase.class)
public class ReviewOrderPage extends ReviewOrderPageBase implements IMobileUtils {

    @FindBy(id = "first_name")
    private ExtendedWebElement firstNameInput;

    @FindBy(id = "last_name")
    private ExtendedWebElement lastNameInput;

    @FindBy(id = "address")
    private ExtendedWebElement addressInput;

    @FindBy(id = "save")
    private ExtendedWebElement saveBtn;

    @FindBy(id = "confirm")
    private ExtendedWebElement confirmBtn;

    @FindBy(id = "ic_done")
    private ExtendedWebElement doneIcon;

    @FindBy(id = "completed_text")
    private ExtendedWebElement completedText;

    @FindBy(id = "go_back")
    private ExtendedWebElement goBackBtn;

    public ReviewOrderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return firstNameInput.isElementPresent(5) && confirmBtn.isElementPresent(5);
    }

    @Override
    public void fillUserInfo(String firstName, String lastName, String address) {
        typeInto(firstNameInput, firstName);
        typeInto(lastNameInput, lastName);
        typeInto(addressInput, address);
    }

    private void typeInto(ExtendedWebElement el, String text) {
        if (el == null) return;
        el.click();
        el.type(text);
    }

    @Override
    public void saveUserInfo() {
        if (saveBtn.isElementPresent(2)) {
            saveBtn.click();
            pause(1);
        }
    }

    @Override
    public void confirmOrder() {
        confirmBtn.click();
        pause(1);
    }

    @Override
    public boolean isConfirmationShown() {
        return doneIcon.isElementPresent(5) || completedText.isElementPresent(5);
    }

    @Override
    public void goBackToShop() {
        if (goBackBtn.isElementPresent(3)) {
            goBackBtn.click();
        }
    }
}
