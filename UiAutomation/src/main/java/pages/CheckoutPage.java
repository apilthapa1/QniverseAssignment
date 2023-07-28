package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class CheckoutPage extends BasePage{
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="first-name")
	private WebElement firstName;
	
	@FindBy(id="last-name")
	private WebElement lastName;
	
	@FindBy(id="postal-code")
	private WebElement postalCode;
	
	@FindBy(id="continue")
	private WebElement continueButton;
	
	@FindBy(xpath="//h3[@data-test='error']")
	private WebElement errorSeen;
	
	@FindBy(className="inventory_item_price")
	private List<WebElement> inventoryItemsPrice;
	
	@FindBy(className="cart_item")
	private List<WebElement> cartItems;
	
	@FindBy(className="summary_tax_label")
	private WebElement tax;
	
	@FindBy(xpath="//div[contains(@class,'summary_total_label')]")
	private WebElement totalPrice;
	
	@FindBy(xpath="//button[text()='Finish']")
	private WebElement finishButton;
	
	@FindBy(className="title")
	private WebElement title;
	
	@FindBy(className="complete-header")
	private WebElement completeHeader;
	
	@FindBy(className="complete-text")
	private WebElement completeText;
	
	@FindBy(xpath="//button[text()='Back Home']")
	private WebElement backHome;
	
	
	public boolean checkErrorMessaage() {
		if(!checkError(getTestData("firstNameError"))) {
			return false;
		}
		firstName.sendKeys(getTestData("firstName"));
		clickContinueButton();
		if(!checkError(getTestData("lastNameError"))) {
			return false;
		}
		lastName.sendKeys(getTestData("lastName"));
		clickContinueButton();
		if(!checkError(getTestData("postalCodeError"))) {
			return false;
		}
		postalCode.sendKeys(getTestData("postalCode"));
		return true;
	}
	
	public void clickContinueButton() {
		continueButton.click();
	}
	
	private boolean checkError(String error) {
		if(error.equals(getErrorSeen())){
			return true;
		}
		return false;
	}
	
	private String getErrorSeen() {
		return errorSeen.getText().trim();
	}
	
	private float getPrice(WebElement element) {
		String elementText = element.getText();
		int index = elementText.indexOf('$');
		float price = Float.parseFloat(elementText.substring(index + 1));
		return price;
	}
	
	public boolean checkCartItems(int itemsAdded) {
		if(itemsAdded != cartItems.size()) {
			return false;
		}
		float itemPrice = getPrice(tax);
		for (WebElement webElement : this.inventoryItemsPrice) {
			itemPrice+=getPrice(webElement);
		}
		
		float totalPriceSeen = getPrice(totalPrice);
		if(itemPrice != totalPriceSeen) {
			return false;
		}
		return true;
	}
	
	public boolean isCheckoutComplete() {
		finishButton.click();
		String titleText = title.getText().trim();
		String completeHeaderText = completeHeader.getText().trim();
		String completeTextValue = completeText.getText().trim();
		if(getTestData("checkoutCompleteText").equals(titleText + completeHeaderText + completeTextValue)) {
			return true;
		}
		return false;
	}
	
	public void clickBackHome() {
		backHome.click();
	}
	
	
}
