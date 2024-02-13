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
	
	@Test(priority=1,groups= {"regression","master"})
	public void test_titleValidation() throws IOException {
		logger.info("**** starting TC_001_test_titleValidation  *****");
		homePage = new HomePage(driver);
		
		logger.info("Validating home title of page");
		String title = homePage.titleValidation();
		Assert.assertEquals(title, "New Cars & Bikes, Prices, News, Reviews, Buy & Sell Used Cars - ZigWheels.com");
		logger.info("**** finished TC_001_test_titleValidation  *****");

	}
	
	@Test(priority =2,groups= {"regression","master"})
	public void clickUpcomingBikes(){
		logger.info("**** starting TC_001_clickUpcomingBikes *****");
		
		logger.info("Navigating to Upcoming Bikes");
		homePage.findNewBikes();
		logger.info("**** finished TC_001_clickUpcomingBikes  *****");

	}
	
	@Test(priority =3,groups= {"regression","master"})
	public void manufacturerVisbleTest() {
		logger.info("**** starting TC_001_manufacturerVisbleTest *****");

		homePage = new HomePage(driver);
		
		logger.info("Verifying manufacturer button");
		homePage.verifyVisibilityManufacturer();
		logger.info("**** finished TC_001_manufacturerVisbleTest  *****");

	}
	
	@Test(priority =4,groups= {"regression","master"})
	public void setBikeModelHonda() {

		logger.info("**** starting TC_001_setBikeModelHonda *****");
		logger.info("Searching for Honda Bikes");
		homePage.setBikeModel();
		logger.info("Validating the Welcome message");
		String expected = getMsg1();
		String actual = homePage.WelcomeMsg();
		Assert.assertEquals(actual, expected);
		logger.info("**** finished TC_001_setBikeModelHonda *****");

	}
	
	@Test(priority =5,groups= {"regression","master"})
	public void hondaVisibleTest() {
		logger.info("**** starting TC_001_hondaVisibleTest *****");

		homePage = new HomePage(driver);
		logger.info("Verfying the Honda is enabled ");

		homePage.verifyVisibilityHonda();
		logger.info("**** finished TC_001_hondaVisibleTest *****");

	}
	
	@Test(priority = 6,groups= {"regression","master"})
	public void printBikesDetails() throws Exception {
		logger.info("**** starting TC_001_printBikesDetails *****");

		np = new NewBikesPage(driver);
		et = new ExcelUtility(System.getProperty("user.dir")+"\\testData\\CarModels.xlsx");
		logger.info("Clicking view more button");
		np.click_allBikes_btn();
		
		logger.info("writing the bike details in Excel sheet");

		Map<String, Float> map = np.print_names_prices();
		int i = 0;
		for (Map.Entry<String, Float> e : map.entrySet()) {
			et.setCellData("Bikes", i , 0, e.getKey());
			et.setCellData("Bikes", i , 1, e.getValue());
			i++;
		}
		List<String> launch_dates = np.get_launchDates();
		
		for (int j=0;j<launch_dates.size();j++) {
			et.setCellData("Bikes", j , 2, launch_dates.get(j));
		}
		logger.info("**** finished TC_001_printBikesDetails *****");

	}
	
	
	
	
}

