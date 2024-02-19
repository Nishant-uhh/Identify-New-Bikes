package testCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.UsedCarsPage;
import testBase.BaseClass;

public class TC_002_usedCarsChennai extends BaseClass{
	UsedCarsPage uc;
	@Test(priority =7,groups= {"regression","master"})
	public void verifyUsedCarsPage() {
		logger.info("**** starting TC_002_verifyUsedCarsPage  *****");

		uc = new UsedCarsPage(driver);
		goBack();
		goBack();
		
		logger.info("Navigating to Used Cars section");
		uc.findUsedCars();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		logger.info("Validating the Welcome message");
		String actual = uc.WelcomeMsg();
		String expected = getMsg2();
		Assert.assertEquals(actual, expected);
		logger.info("**** finished TC_002_verifyUsedCarsPage  *****");

		
	}
	
	@Test(priority = 8,groups= {"regression","master"})
	public void visibilityChennai() {
		logger.info("**** starting TC_002_visibilityChennai  *****");

		uc = new UsedCarsPage(driver);
		logger.info("Verifying chennai visibility");

		uc.verifyChennaiVisibility();
		
		logger.info("**** finished TC_002_visibilityChennai  *****");

	}
	
	@Test(priority = 9,groups= {"regression","master"})
	public void printPopularModels() throws Exception {
		logger.info("**** starting TC_002_printPopularModels  *****");

		logger.info("Retrieving popular models & writing in Excel");

		List<String> models =  uc.getAllModels();
		for (int i=0;i<models.size();i++) {
			et.setCellData("Models",i,0,models.get(i));
		}
		
		logger.info("clicking all models ");
		uc.clickModels();
		
		logger.info("**** finished TC_002_printPopularModels  *****");

	}
	
}
