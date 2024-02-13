package pageObjects;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class UsedCarsPage extends BasePage{

	public UsedCarsPage(WebDriver driver) {
		super(driver);
	}
	
	//Elements
	@FindBy(xpath="//nav/div/ul/li[7]/a")
	WebElement usedCars;
	
	@FindBy(xpath="//li/span[contains(text(),'Chennai')]")
	WebElement chennai;
	
	@FindBy (xpath="//*[@id=\"Header\"]/div/div[1]/div[1]/a/img")
	WebElement homeBtn;
	
	@FindBy(xpath="//div[@class=\"gsc_thin_scroll\"]/ul/li/label")
	List<WebElement> allModels;
	
	@FindBy(xpath="//div[@class=\"gsc_thin_scroll\"]/ul/li/span/input")
	List<WebElement> checkboxes;
	
	@FindBy(xpath = "//*[@id=\"usedcarttlID\"]" )
	WebElement welcomeMsgElement;
	
	//Action Methods
	public void findUsedCars() {
		Actions act = new Actions(driver);
		act.moveToElement(usedCars).perform();
		act.moveToElement(chennai).perform();
		js.executeScript("arguments[0].click();", chennai);
	}
	
	public void verifyChennaiVisibility() {
		try {
//			reportInfo("verifying Honda is present in manufacture");
			boolean elementPresent = verifyElementIsEnabled(chennai);
			if (elementPresent == true)
				System.out.println("Honda is present");
			else
				System.out.println("Honda is not present");
		}
		catch(Exception e) {
//			reportFail(e.getMessage());
		}
	}
	
	public void clickModels() throws Exception {

		for(WebElement ele: checkboxes) {
			js.executeScript("window.scrollBy(0,300)");
			js.executeScript("arguments[0].click();", ele);
			Thread.sleep(500);
		}
		js.executeScript("arguments[0].click();", homeBtn);
	}
	
	public List<String> getAllModels(){
		
		List<String> carModels = new LinkedList<>();
		for(WebElement i: allModels) {
			carModels.add(i.getText());
		}
		return carModels; 
	}
	
	public String WelcomeMsg() {
		return welcomeMsgElement.getText();
	}
	
	
	
	
}
