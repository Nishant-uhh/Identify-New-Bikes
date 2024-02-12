package pageObjects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;


public class BasePage {
	public WebDriver driver;
	public static JavascriptExecutor js ;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;

	}
	
	public void highlightElement(WebElement element) {
		try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
		}catch(Exception e) {}
	}

	public boolean verifyElementIsEnabled(WebElement xpathKey){
		try {
			if(xpathKey.isEnabled()){
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
//			reportFail(e.getMessage());
		}
		return false;
		
	}
	


}