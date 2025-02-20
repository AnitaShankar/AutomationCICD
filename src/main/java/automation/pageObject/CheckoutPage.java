package automation.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.cssSelector("[placeholder='Select Country']"
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	//driver.findElement(By.cssSelector(".action__submit")).click();
	@FindBy(css=".action__submit")
	WebElement submit;
	
	//driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	//By.cssSelector(".ta-results")
	By results= By.cssSelector(".ta-results");
	
	
	public void selectCountry(String countryName)
	{
		Actions a =new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
		
	}
	
	public confirmationPage submitOrder()
	{
		submit.click();
		//below is the object with return method 
		return new confirmationPage(driver);
	}
	
	
	
}







