package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageClasses.HomePage;

public class BookFlightTest extends BaseTest{

	private HomePage home;
	
	@BeforeClass
	public void initializePageClasses() {
		home = new HomePage();
	}
	
	@Test(description="Navigating to application page",priority =1)
	public void goToApplicationPage() {
		home.gotToWebsite(getPropertyValue("url"));
		home.clickOnFlightBtn();
	}
}
