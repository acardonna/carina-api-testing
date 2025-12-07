package com.solvd.carinatestautomation.mobile.android;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.carinatestautomation.mobile.gui.pages.android.LoginPage;
import com.solvd.carinatestautomation.mobile.gui.pages.common.AccountPageBase;
import com.solvd.carinatestautomation.mobile.gui.pages.common.CartPageBase;
import com.solvd.carinatestautomation.mobile.gui.pages.common.LoginPageBase;
import com.solvd.carinatestautomation.mobile.gui.pages.common.OrdersPageBase;
import com.solvd.carinatestautomation.mobile.gui.pages.common.ProductListPageBase;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.utils.mobile.IMobileUtils;

public class AndroidTests implements IAbstractTest, IMobileUtils {

	private static final String VALID_EMAIL = "testuser@mobitru.com";
	private static final String VALID_PASSWORD = "password1";

	@Test
	public void testLoginSuccess() {
		ProductListPageBase listPage = login(VALID_EMAIL, VALID_PASSWORD);
		Assert.assertTrue(listPage.isPageOpened(), "Product list not visible after login");
	}

	@Test
	public void testLoginWithInvalidCredentialsShowsError() {
		LoginPageBase loginPage = new LoginPage(getDriver());
		Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
		loginPage.typeEmail("wrong@example.com");
		loginPage.typePassword("badpass");
		loginPage.tapSignInExpectingError();
		Assert.assertTrue(loginPage.isErrorVisible(), "Error message not shown for invalid login");
	}

	@Test
	public void testAddTwoProductsUpdatesCartBadge() {
		ProductListPageBase listPage = quickLogin();
		listPage.addProductToCart(0);
		listPage.addProductToCart(1);
		Assert.assertEquals(listPage.getCartCount(), 2, "Cart count should be 2 after adding two products");
	}

	@Test
	public void testRemoveProductResetsCartBadge() {
		ProductListPageBase listPage = quickLogin();
		listPage.addProductToCart(0);
		listPage.removeProductFromCartIfPresent(0);
		Assert.assertEquals(listPage.getCartCount(), 0, "Cart count should be 0 after removal");
	}

	@Test
	public void testOpenCartShowsItemsAndPromo() {
		ProductListPageBase listPage = quickLogin();
		listPage.addProductToCart(0);
		CartPageBase cartPage = listPage.openCart();
		Assert.assertTrue(cartPage.isPageOpened(), "Cart page not opened");
		Assert.assertFalse(cartPage.isEmpty(), "Cart should not be empty after adding product");
		Assert.assertTrue(cartPage.isPromoControlsVisible(), "Promo controls not visible in cart");
	}

	@Test
	public void testOpenCartEmptyWhenNoItems() {
		ProductListPageBase listPage = quickLogin();
		CartPageBase cartPage = listPage.openCart();
		Assert.assertTrue(cartPage.isPageOpened(), "Cart page not opened");
		Assert.assertTrue(cartPage.isEmpty(), "Cart should be empty before adding products");
		Assert.assertFalse(cartPage.isPromoControlsVisible(), "Promo controls should be hidden when cart is empty");
	}

	@Test
	public void testCategoryTitleVisible() {
		ProductListPageBase listPage = quickLogin();
		Assert.assertTrue(listPage.getCategoryTitle().contains("Mobile"), "Category title not shown as expected");
	}

	@Test
	public void testViewOrdersAndOpenDetail() {
		ProductListPageBase listPage = quickLogin();
		OrdersPageBase ordersPage = listPage.openOrders();
		Assert.assertTrue(ordersPage.isPageOpened(), "Orders page not opened");
		Assert.assertTrue(ordersPage.hasCompletedTab(), "Completed tab not visible");
		ordersPage.openFirstOrder();
		Assert.assertTrue(ordersPage.isOrderDetailShown(), "Order detail not visible");
	}

	@Test
	public void testSortByPriceDescending() {
		ProductListPageBase listPage = quickLogin();
		listPage.sortBy("Price descending");
		Assert.assertTrue(listPage.isPageOpened(), "Product list not visible after sorting");
	}

	@Test
	public void testLogoutFlow() {
		ProductListPageBase listPage = quickLogin();
		AccountPageBase accountPage = listPage.openAccount();
		Assert.assertTrue(accountPage.isAccountScreenOpened(), "Account screen not opened");
		LoginPageBase loginPage = accountPage.logout();
		Assert.assertTrue(loginPage.isPageOpened(), "Login page not shown after logout");
	}

	private ProductListPageBase login(String email, String password) {
		LoginPageBase loginPage = new LoginPage(getDriver());
		Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
		loginPage.typeEmail(email);
		loginPage.typePassword(password);
		ProductListPageBase listPage = loginPage.tapSignIn();
		Assert.assertTrue(listPage.isPageOpened(), "Product list is not opened");
		return listPage;
	}

	private ProductListPageBase quickLogin() {
		LoginPageBase loginPage = new LoginPage(getDriver());
		Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
		ProductListPageBase listPage = loginPage.tapSignInWithCorrectUser();
		Assert.assertTrue(listPage.isPageOpened(), "Product list is not opened after quick login");
		return listPage;
	}
}
