package automation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import automation.pageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTests {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException
	{
		
		//property class
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Anita Shankar\\eclipse-workspace\\SeleniumFramework\\src\\main\\java\\automation\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName=prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		 driver= new ChromeDriver();
		
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			//firefox driver 
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//microsoft edge
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
	}
	
	
	public List<HashMap<String, String>> getJsonDatatoMap(String FilePath) throws IOException
	{
		//read JSON to String
	String JsonContent = FileUtils.readFileToString(new File(FilePath),
			StandardCharsets.UTF_8);
	
	//String to HashMap - Jackson DataBind
	
	ObjectMapper Mapper = new ObjectMapper();
	List<HashMap<String,String>> data = Mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>(){
		
	});
	
	return data;
	}
	
	
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot	ts= (TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//" + testCaseName + ".png";
	}
	
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver= initializeDriver();
		 landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		//driver.close();
	}

}
