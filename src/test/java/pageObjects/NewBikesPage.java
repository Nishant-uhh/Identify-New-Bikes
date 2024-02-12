package pageObjects;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewBikesPage extends BasePage{

	public NewBikesPage(WebDriver driver) {
		super(driver);
	}
	
	//Elements
	@FindBy(xpath="//li[16]/span")
	WebElement viewMoreBikes;
	
	@FindBy(xpath="//li/div/div[3]")
	List<WebElement> allBikeDetails;
	
	List<String> launch_dates = new ArrayList<>();

	
	//Action Methods
	public void click_allBikes_btn() {
		js.executeScript("arguments[0].scrollIntoView();", viewMoreBikes);
		js.executeScript("arguments[0].click();", viewMoreBikes);
	}

	public Map<String, Float> print_names_prices() {
		Map<String, Float> map = new LinkedHashMap<>();
		
		for (WebElement ele: allBikeDetails) {
			String priceString = ele.findElement(By.tagName("div")).getText();
			
			String[] parts = priceString.split("\\s");
		
			if (parts.length <=2 && parts[1].equals("79,000")) {
				update_launch_dates(ele);
				map.put(ele.findElement(By.tagName("strong")).getText(),(float) 0.79);				
				
			} 
			else if (parts.length >= 2) {
				float price = Float.parseFloat(parts[1].trim());
				if (price <= 4.00) {
					update_launch_dates(ele);
					map.put(ele.findElement(By.tagName("strong")).getText(),price);
				}
			} 
			else {
				System.out.println("skipped");
				continue;
			}
		}
		
		for (Map.Entry<String, Float> entry : map.entrySet()) {
			System.out.println(entry.getKey()+": "+entry.getValue());
		}
		
		return map;
	}

	public void update_launch_dates(WebElement ele) {
		String string = ele.findElement(By.cssSelector("div.clr-try")).getText();
		String parts = string.split(":")[1];
		launch_dates.add(parts);
	}
		
	public List<String> get_launchDates() {
		return launch_dates;
	}
	
	
}
