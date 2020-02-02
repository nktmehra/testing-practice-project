package utilitites;

import org.openqa.selenium.WebElement;

import BaseClass.BaseClass;

public class Utilities extends BaseClass {

	public static synchronized void openURL(String url) {
		log.info("Opening "+url);
		try {
			getWebDriver().get(url);
		}catch(Exception e) {
			log.error("Error in opening the url: "+url);
		}
	}


	public static synchronized void clickOnElement(String description, WebElement element) {
		log.info("Click on "+description);
		try {
			element.click();
		}catch(Exception e) {
			log.error("Unable to click the webelement: "+description);
			e.getMessage();
		}
	}
	
	public static synchronized void enterText(String description, WebElement element, String text) {
		log.info("Entering text in "+description);
		try {
			element.sendKeys(text);
		}catch(Exception e) {
			log.error("Unable to enter text in: "+description);
			e.getMessage();
		}
	}
	
	
}
