package testCases;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.NewBikesPage;
import testBase.BaseClass;
import utilities.ExcelUtility;

public class TC_001 extends BaseClass{
	HomePage homePage;
	NewBikesPage np;
	
	@Test(priority=1)
	public void test_titleValidation() throws IOException {
		logger.info("**** starting TC_001_test_titleValidation  *****");
		homePage = new HomePage(driver);
		String title = homePage.titleValidation();
		Assert.assertEquals(title, "New Cars & Bikes, Prices, News, Reviews, Buy & Sell Used Cars - ZigWheels.com");

	}
	
	@Test(priority =2)
	public void clickUpcomingBikes(){
		logger.info("**** starting TC_001_searchHondaBikes  *****");

		homePage.findNewBikes();
		logger.info("Found Upcoming Bikes");
	}
	
	@Test(priority =3)
	public void manufacturerVisbleTest() {
		homePage = new HomePage(driver);
		homePage.verifyVisibilityManufacturer();
	}
	
	@Test(priority =4)
	public void setBikeModelHonda() {

		homePage.setBikeModel();
		logger.info("Searched for Honda Bikes");
		String expected = getMsg1();
		String actual = homePage.WelcomeMsg();
		Assert.assertEquals(actual, expected);
	}
	
	@Test(priority =5)
	public void hondaVisibleTest() {
		homePage = new HomePage(driver);
		homePage.verifyVisibilityHonda();
	}
	
	@Test(priority = 6)
	public void printBikesDetails() throws Exception {
		np = new NewBikesPage(driver);
		et = new ExcelUtility(System.getProperty("user.dir")+"\\testData\\CarModels.xlsx");
		np.click_allBikes_btn();
		
		Map<String, Float> map = np.print_names_prices();
		int i = 0;
		for (Map.Entry<String, Float> e : map.entrySet()) {
			et.setCellData("Bikes", i , 0, e.getKey());
			et.setCellData("Bikes", i , 1, e.getValue());
			i++;
		}
		List<String> launch_dates = np.print_launch_dates();
		
		for (int j=0;j<launch_dates.size();j++) {
			et.setCellData("Bikes", j , 2, launch_dates.get(j));
		}
		
	}
	
	
}

