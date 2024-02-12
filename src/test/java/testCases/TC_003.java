package testCases;

import java.io.IOException;

import org.testng.annotations.Test;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_003 extends BaseClass{
	LoginPage lp ;
	
	@Test(priority =9)
	public void verifyLoginBtn() {
		lp = new LoginPage(driver);
		lp.verifyVisibilityLoginBtn();
	}
	
	@Test(priority =10)
	public void test_verifyLogin() throws InterruptedException {
		
		lp = new LoginPage(driver);
		lp.clickGoogleBtn();
		String email = getEmail();
		System.out.println("Error message : "+ lp.verifyLogin(email));
		
	}
}
