package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;

import utilities.ExcelUtility;

public class BaseClass {
	public static WebDriver driver;
	public static Logger logger;
	public static Properties prop;
	public static ExcelUtility et;
	
	@BeforeTest(groups= {"sanity","regression","master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws Exception {
	
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		prop = new Properties();
		prop.load(file);
		//Logger
		logger = LogManager.getLogger(Test.class);
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote"))
	 	{	
		DesiredCapabilities capabilities=new DesiredCapabilities();
		//OS
		if(os.equalsIgnoreCase("windows"))
		{
			capabilities.setPlatform(Platform.WIN11);
		}
		else if(os.equalsIgnoreCase("mac"))
		{
			capabilities.setPlatform(Platform.MAC);
		}
		else
		{
			System.out.println("No matching os..");
			return;
		}
		//browser
		switch(br.toLowerCase())
		{
		case "chrome" : capabilities.setBrowserName("chrome"); break;
		case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
		default: System.out.println("No matching browser.."); return;
		}
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
	    }
		
		//If execution_env is local then run in local system
		else if(prop.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			//launching browser based on condition - locally
			switch(br.toLowerCase())
			{
			case "chrome": driver=new ChromeDriver(); break;
			case "edge": driver=new EdgeDriver(); break;
			default: System.out.println("No matching browser..");
						return;
			}
		
//		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//		String Url = getUrl();
//		driver.get(Url);
		driver.get(prop.getProperty("appURL"));
		driver.manage().window().maximize();
		}
	}
	
	
	@AfterTest(groups= {"sanity","regression","master"})
	public void teardown() {
		driver.quit();
	}
	
	//Method to take ExtentReport_screenshot
	public String captureScreen1(String tname) throws IOException {

		//String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname +".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}

	public static void goBack() {
		driver.navigate().back();
	}
	
	
	public String getUrl() {
		return prop.getProperty("appURL");
	}
	
	public static String getEmail() {
		return prop.getProperty("email");

	}
	
	public static String getMsg1() {
		return prop.getProperty("WelcomeMsg1");
	}
	
	public static String getMsg2() {
		return prop.getProperty("WelcomeMsg2");
	}
	
	
//	/****************** Reporting Functions ***********************/
//	public void reportFail(String reportString) {
//		logger.log(Status.FAIL, reportString);
////		takeScreenShotOn();
//		Assert.fail(reportString);
//	}
//
//	public void reportPass(String reportString) {
//		logger.log(Status.PASS, reportString);
//	}
//	
//	public void reportInfo(String reportString) {
//		logger.log(Status.INFO, reportString);
//	}
	
}
