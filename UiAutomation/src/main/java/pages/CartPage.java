package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class CartPage extends BasePage{
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private String cartItemName = "inventory_item_name";
	
	private String cartItemDescription = "inventory_item_desc";
	
	@FindBy(className="cart_item")
	private List<WebElement> cartItems;
	
	@FindBy(className="shopping_cart_badge")
	private WebElement cartBadge;
	
	@FindBy(xpath="//button[text()='Remove']")
	private WebElement removeButton;
	
	@FindBy(xpath="//button[text()='Checkout']")
	private WebElement checkout;
	
	public boolean verifyAddedItem(int itemCount, String itemName, HomePage homePage) {
		int addedItemsCount = cartItems.size();
		if(itemCount != addedItemsCount) {
			return false;
		}
		if(!matchDescription(itemName, homePage)) {
			return false;
		}
		return true;
	}
	
	private boolean matchDescription(String itemName, HomePage homePage) {
		for (WebElement webElement : cartItems) {
			String itemNameFromCart = webElement.findElement(By.className(cartItemName)).getText();
			if(itemNameFromCart.equals(itemName)) {
				String itemDescriptionFromCart = webElement.findElement(By.className(cartItemDescription)).getText();
				if(itemDescriptionFromCart.equals(homePage.getItemDescription())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean verifyRemovedItem() {
		removeButton.click();
		int countAfterRemoval = cartItems.size();
		if(countAfterRemoval != 0) {
			return false;
		}
		return true;
	}
	
	public CheckoutPage clickCheckout() {
		checkout.click();
		return new CheckoutPage(driver);
	}
}
