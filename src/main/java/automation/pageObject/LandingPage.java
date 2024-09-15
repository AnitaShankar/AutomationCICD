package automation.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail  = driver.findElement(By.id("userEmail"));

	///PageFactory 
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	//driver.findElement(By.id("userPassword"))
	@FindBy(id="userPassword")
	WebElement password;
	
	//driver.findElement(By.id("login"))
	@FindBy(id="login")
	WebElement submit;
	
	//ng-tns-c4-13 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	
	public ProtocolCatalouge LoginApplication(String email,String passcord)
	{
		userEmail.sendKeys(email);
		password.sendKeys(passcord);
		submit.click();
		
		ProtocolCatalouge protocolCatalouge = new ProtocolCatalouge(driver);
		return protocolCatalouge;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForWbElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
}
