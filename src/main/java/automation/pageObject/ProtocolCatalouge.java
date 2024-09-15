package automation.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.AbstractComponent.AbstractComponent;

public class ProtocolCatalouge extends AbstractComponent {
	
	WebDriver driver;
	
	public ProtocolCatalouge(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> items= driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> items;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	public  List<WebElement> getProducts()
	{
		waitForElementToAppear(productsBy);
		return items;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement product=getProducts().stream().filter(item->item.findElement(By.cssSelector("b"))
				.getText().equals(productName)).findFirst().orElse(null);
		
		return product;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement product=getProductByName(productName);
		product.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDissappear(spinner);
	}


	

	
}
