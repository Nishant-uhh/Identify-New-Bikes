package testCases;

import org.testng.annotations.Test;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_003 extends BaseClass{
	LoginPage lp ;
	
	@Test(priority = 10,groups= {"sanity","regression","master"})
	public void verifyLoginBtn() {
		logger.info("**** starting TC_003_verifyLoginBtn  *****");

		lp = new LoginPage(driver);
		logger.info("Verfying the Login is enabled ");

		lp.verifyVisibilityLoginBtn();
		
		logger.info("**** finished TC_003_verifyLoginBtn  *****");

	}
	
	
	@Test(priority = 11,groups= {"sanity","regression","master"})
	public void test_verifyLogin() throws InterruptedException {
		logger.info("**** starting TC_003_test_verifyLogin  *****");

		lp = new LoginPage(driver);
		lp.clickGoogleBtn();
		String email = getEmail();
		
		logger.info("Validating the error message");
		System.out.println("Error message : "+ lp.verifyLogin(email));
		
		logger.info("**** finished TC_003_test_verifyLogin  *****");

		
	}
}
