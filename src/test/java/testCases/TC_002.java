package testCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.UsedCarsPage;
import testBase.BaseClass;

public class TC_002 extends BaseClass{
	UsedCarsPage uc;
	@Test(priority =7)
	public void verifyUsedCarsPage() {
		uc = new UsedCarsPage(driver);
		goBack();
		goBack();
		uc.findUsedCars();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String actual = uc.WelcomeMsg();
		String expected = getMsg2();
		Assert.assertEquals(actual, expected);
		
	}
	
	@Test(priority =8)
	public void printPopularModels() throws Exception {
	
		List<String> models =  uc.getAllModels();
		for (int i=0;i<models.size();i++) {
			et.setCellData("Models",i,0,models.get(i));
		}
		uc.clickModels();
		
	}
	
}
