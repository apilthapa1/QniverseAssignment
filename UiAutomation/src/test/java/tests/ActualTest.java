package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;

public class ActualTest extends BaseTest{
	
	LoginPage loginPage;
	HomePage homePage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	
	private int itemsAdded = 0;
	
	public WebDriver driver;
	
	@BeforeClass
	public void oneTimeSetUp() {
		this.driver = _driver;
		loginPage = new LoginPage(driver);
	}
	
	@Test(priority=1)
	public void Login() {
		homePage = loginPage.Login();
		Assert.assertTrue(homePage.verifyHomePage(), "Login Not Successfull - Login Page");
	}
	
	@Test(priority=2)
	public void addToCart_Item1() {
		String itemName = homePage.getTestData("item1Name");
		itemsAdded = homePage.addItemToCart(itemName);
		cartPage = homePage.goToCart();
		Assert.assertTrue(cartPage.verifyAddedItem(itemsAdded, itemName, homePage));
	}
	
	@Test(priority=3)
	public void removeItem() {
		Assert.assertTrue(cartPage.verifyRemovedItem(), "Item not removed - Cart Page");
		homePage.goToAllItems();
	}
	
	@Test(priority=4)
	public void addToCart_Item2() {
		String itemName = homePage.getTestData("item2Name");
		itemsAdded = homePage.addItemToCart(itemName);
		homePage.goToCart();
		Assert.assertTrue(cartPage.verifyAddedItem(itemsAdded, itemName, homePage));
		checkoutPage = cartPage.clickCheckout();
	}
	
	@Test(priority=5)
	public void checkoutPage_Step1() {
		checkoutPage.clickContinueButton();
		Assert.assertTrue(checkoutPage.checkErrorMessaage(), "Required field error message not seen - Checkout Page");
	}
	
	@Test(priority=6)
	public void checkoutPage_Step2() {
		checkoutPage.clickContinueButton();
		Assert.assertTrue(checkoutPage.checkCartItems(itemsAdded), "Error in checkout step 2");
	}
	
	@Test(priority=7)
	public void CheckoutComplete() {
		Assert.assertTrue(checkoutPage.isCheckoutComplete(), "Checkout is not complete - Checkout Complete Page");
	}
	
	@Test(priority=8)
	public void backHome() {
		checkoutPage.clickBackHome();
		Assert.assertTrue(homePage.verifyHomePage(), "Back to home button not working - Checkout Complete Page");
	}
	
	@Test(priority=9)
	public void logout() {
		Assert.assertTrue(homePage.logout(), "Logout not successfull");
	}
	
	
	
}
