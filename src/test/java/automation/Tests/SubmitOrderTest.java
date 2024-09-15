package automation.Tests;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;

//import java.util.Map;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation.TestComponents.BaseTests;
import automation.pageObject.CartPage;
import automation.pageObject.CheckoutPage;
import automation.pageObject.OrderPage;
import automation.pageObject.ProtocolCatalouge;
import automation.pageObject.confirmationPage;

public class SubmitOrderTest extends BaseTests{
	
	String productName="ZARA COAT 3";

	@Test(dataProvider="getData",groups={"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
	
		ProtocolCatalouge protocolCatalouge=landingPage.LoginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> items= protocolCatalouge.getProducts();
		protocolCatalouge.addProductToCart(input.get("productName"));
		CartPage cartPage=protocolCatalouge.goToCartPage();
		
		Boolean match=cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkOutPage=cartPage.goToCheckout();
		
		
		checkOutPage.selectCountry("india");
		confirmationPage ConfirmationPage=checkOutPage.submitOrder();
		String confirmMessage = ConfirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}
	
	
	
	//To verify ZARA COAT 3 is displaying in Orders page
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void OrderHistorytest()

	{
		ProtocolCatalouge protocolCatalouge=landingPage.LoginApplication("anitamareppanavar18@gmail.com", "Anita@1999");
		OrderPage orderspage = protocolCatalouge.goToOrdersPage();
		Assert.assertTrue(orderspage.VerifyOrderHistory(productName));
	}
	
	
	
	//data driven testing with annotations , passing the values using hashmap
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "anitamareppanavar18@gmail.com");
//		map.put("password", "Anita@1999");
//		map.put("productName", "ZARA COAT 3");
		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "anitamareppanavar@gmail.com");
//		map1.put("password", "Anita@1999");
//		map1.put("productName", "ADIDAS ORIGINAL");
		
		 List<HashMap<String,String>> data = getJsonDatatoMap(System.getProperty("user.dir")+"\\src\\test\\java\\automation\\jsondata\\PurchaseOrder.json");
		return new Object[] [] {{data.get(0)} , {data.get(1)}};
		
	}
	

	
	//passing the values with multi dimensional array 
	
//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object[] [] {{"anitamareppanavar18@gmail.com","Anita@1999","ZARA COAT 3"},{"anitamareppanavar@gmail.com","Anita@1999","ADIDAS ORIGINAL"}};
		
//	}
	
}
