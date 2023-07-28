package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class LoginPage extends BasePage{
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="user-name")
	private WebElement userName;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(id="login-button")
	private WebElement loginButton;
	
	
	public HomePage Login() {
		userName.sendKeys(getConfigValue("username"));
		password.sendKeys(getConfigValue("password"));
		loginButton.click();
		return new HomePage(driver);
	}
}
