package testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import BaseClass.BaseClass;

public class BaseTest extends BaseClass{

	@BeforeClass
	public void initDriver() {
		createWebDriver();
		log.info("----------Class Name: "+this.getClass().getSimpleName()+"Execution started---------------");
	}
	
	@AfterClass
	public void tearDown() {
		getWebDriver().quit();
	}
}
