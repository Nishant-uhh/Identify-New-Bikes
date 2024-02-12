package pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ExcelUtils;
import utilities.ExcelUtility;
import utilities.Screenshots;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	
	@FindBy(id="forum_login_title_lg")
	WebElement loginBtn;
	
	@FindBy(xpath="//span[text()='Google']")
	WebElement googleBtn;
	
	@FindBy(id="identifierId")
	WebElement signInBtn;
	
	@FindBy(id="identifierNext")
	WebElement nextBtn;
	
	@FindBy(xpath="//div[@class=\"o6cuMc Jj6Lae\"]")
	WebElement errorMsg;
	
	
	public void clickGoogleBtn() throws InterruptedException {
		highlightElement(loginBtn);
		loginBtn.click();
		Thread.sleep(1000);
		googleBtn.click();

	}
	
//	verify visibility login button
	public void verifyVisibilityLoginBtn() {
		try {
//			reportInfo("Verifying login button is present");
			boolean elementPresent = verifyElementIsEnabled(loginBtn);
			if (elementPresent == true)
				System.out.println("Login button is present");
			else
				System.out.println("Login button is not present");
		}
		catch(Exception e) {
//			reportFail(e.getMessage());
		}

	}
	
	
	public String verifyLogin(String email) {
		Set<String> winIdSet = driver.getWindowHandles();
		List<String> winIdList =new ArrayList<>(winIdSet);
		driver.switchTo().window(winIdList.get(1));
		
		signInBtn.sendKeys(email);
		
		js.executeScript("arguments[0].click();", nextBtn);

		return errorMsg.getText();
	}
	
}
