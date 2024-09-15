package automation.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import automation.TestComponents.BaseTests;
import automation.pageObject.CartPage;
import automation.pageObject.CheckoutPage;
import automation.pageObject.LandingPage;
import automation.pageObject.ProtocolCatalouge;
import automation.pageObject.confirmationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinationImp extends BaseTests{
	
	public LandingPage landingPage;
	public ProtocolCatalouge protocolCatalouge;
	public confirmationPage ConfirmationPage;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_username_and_password(String username,String password)
	{
		protocolCatalouge =landingPage.LoginApplication(username,password);
	}
	
	 @When ("^I add the productname (.+) to cart$")
	 public void Add_the_productName_to_Cart(String productName)
	 {
		 List<WebElement> items= protocolCatalouge.getProducts();
			protocolCatalouge.addProductToCart(productName);
	 }
	 
	 @When ("^Checkout (.+) and submit the order$")
	 public void Checkout_and_submit_order(String productName)
	 {
		 
		 	CartPage cartPage=protocolCatalouge.goToCartPage();
			
			Boolean match=cartPage.VerifyProductDisplay(productName);
			Assert.assertTrue(match);
			CheckoutPage checkOutPage=cartPage.goToCheckout();
			
			
			checkOutPage.selectCountry("india");
			 ConfirmationPage=checkOutPage.submitOrder();
	 }
	 

	 @Then("{string} message is displyed on confirmation Page")
	 public void message_displyed_confirmation_page(String string)
	 {
		 String confirmMessage = ConfirmationPage.getConfirmationMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
			driver.close();
	 }
	

}
