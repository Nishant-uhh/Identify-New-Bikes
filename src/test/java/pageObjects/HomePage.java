package pageObjects;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utilities.Screenshots;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//*[@id=\"headerNewNavWrap\"]/nav/div/ul/li[3]/a")
	WebElement newBikes;
	
	@FindBy(xpath="//*[@id=\"headerNewNavWrap\"]/nav/div/ul/li[3]/ul/li[5]/span")
	WebElement upcomingBikes;
	
	@FindBy(xpath="//select[@id='makeId']")
	WebElement manufacturer;
	@FindBy(xpath="//h1[@class='mt-0 pt-2 pull-left zm-cmn-colorBlack']")
	public WebElement hondaText;
	
	@FindBy(xpath = "/html/body/main/div/div/div[1]/div[1]/div[1]/div/div[1]/h1")
	WebElement welcomeMsgElement;
	
	public String title;
	
	//Action methods
	
	public String titleValidation() throws IOException {
		Screenshots ss = new Screenshots(driver);
		title = driver.getTitle();
		ss.ScreenShot("HomePage");
		return title;
	}
	
	public void findNewBikes(){
		Actions act = new Actions(driver);
		act.moveToElement(newBikes).perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		act.moveToElement(upcomingBikes).click().perform();
	}
	
	//manufacturer dropdown visible
		public void verifyVisibilityManufacturer() {
			try {
//				reportInfo("Verifying manufacture is present");
				boolean elementPresent = verifyElementIsEnabled(manufacturer);
				if (elementPresent == true)
					System.out.println("Manufacturer dropdown is present");
				else
					System.out.println("Manufacturer dropdown is not present");
			}
			catch(Exception e) {
//				reportFail(e.getMessage());
			}

		}
		
		//honda visible
		public void verifyVisibilityHonda() {
			try {
//				reportInfo("verifying Honda is present in manufacture");
				boolean elementPresent = verifyElementIsEnabled(hondaText);
				if (elementPresent == true)
					System.out.println("Honda is present");
				else
					System.out.println("Honda is not present");
			}
			catch(Exception e) {
//				reportFail(e.getMessage());
			}

		}
	
	public void setBikeModel(){
		Select selector = new Select(manufacturer);

		selector.selectByVisibleText("Honda");
		highlightElement(hondaText);
		if(hondaText.getText().startsWith("Honda")) {
			System.out.println("Honda is selected");
		}
		else {
			System.out.println("Honda is not selected");
		}
		
	}
	
	public String WelcomeMsg() {
		return welcomeMsgElement.getText();
	}
	
	

}
