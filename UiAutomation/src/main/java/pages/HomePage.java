package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;
import utilities.Utilities;

public class HomePage extends BasePage {
	
	WebDriver driver;
	public HomePage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "app_logo")
	private WebElement appLogo;
	
	@FindBy(className = "inventory_item")
	private List<WebElement> inventoryItem;
	
	private String inventoryItemName = "inventory_item_name";
	
	private String inventoryItemDescription = "inventory_item_desc";
	
	@FindBy(className = "shopping_cart_link")
	private WebElement cart;
	
	@FindBy(xpath = "//button[text()='Open Menu']")
	private WebElement openMenu;
	
	@FindBy(linkText = "All Items")
	private WebElement allItems;
	
	@FindBy(linkText = "Logout")
	private WebElement logout;
	
	private String itemDescription;
	
	private void setItemDescription(String value) {
		itemDescription=value;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	
	public boolean verifyHomePage() {
		if(appLogo.isDisplayed()) {
			if(appLogo.getText().trim().equals(getTestData("appLogoText"))) {
				return true;
			}
		}
		return false;
	}
	
	public int addItemToCart(String itemName) {
		for (WebElement webElement : inventoryItem) {
			if(webElement.findElement(By.className(inventoryItemName)).getText().equals(itemName)) {
				addToCart(itemName);
				String description = webElement.findElement(By.className(inventoryItemDescription)).getText();
				setItemDescription(description);
				break;
			}
		}
		try {
			return Integer.parseInt(cart.findElement(By.tagName("span")).getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	private void addToCart(String itemName) {
		switch (itemName) {
		case "Sauce Labs Backpack":
			driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
			break;
		case "Test.allTheThings() T-Shirt (Red)":
			driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
			break;
		default:
			break;
		}
	}
	
	public void goToAllItems() {
		openMenu.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		allItems.click();
	}
	
	public CartPage goToCart() {
		cart.click();
		return new CartPage(driver);
	}
	
	public boolean logout() {
		openMenu.click();
		logout.click();
		String url = driver.getCurrentUrl();
		if(url.equals(getConfigValue("url"))) {
			return true;
		}
		return false;
	}
	
	
}
