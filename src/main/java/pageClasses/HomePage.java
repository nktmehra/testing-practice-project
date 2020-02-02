package pageClasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseClass.BaseClass;

public class HomePage extends BaseClass {
	
	@FindBy(css ="a[class^='text-center flights']")
	WebElement flightBtn;
	
	public HomePage() {
		PageFactory.initElements(getWebDriver(), this);
	}
	
	public void gotToWebsite(String url) {
		openUrl(url);
	}
	
	public void clickOnFlightBtn() {
		clickOnElement("Flights", flightBtn);
	}
}
