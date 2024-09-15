package automation.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import automation.TestComponents.BaseTests;
import automation.TestComponents.Retry;
import automation.pageObject.CartPage;
import automation.pageObject.ProtocolCatalouge;

public class ErrorValidationTest extends BaseTests {
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer= Retry.class)
	public void LoginErrorValidation () throws IOException , InterruptedException 
	{

		//String productName="ZARA COAT 3";
		
		
		landingPage.LoginApplication("anitamareppanavar18@gmail.com", "Anita@1990");
		//Thread.sleep(1000);
		Assert.assertEquals("------------------", landingPage.getErrorMessage());
		
	}
	
	
	@Test
	public void ProductErrorValidation()throws IOException , InterruptedException
	{
		String productName="ZARA COAT 3";
		ProtocolCatalouge protocolCatalouge=landingPage.LoginApplication("anitamareppanavar@gmail.com", "Anita@1999");
		
		List<WebElement> items= protocolCatalouge.getProducts();
		protocolCatalouge.addProductToCart(productName);
		CartPage cartPage=protocolCatalouge.goToCartPage();
		
		Boolean match=cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
	

}
