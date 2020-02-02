package pageClasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseClass.BaseClass;

public class RoughPage extends BaseClass{

	@FindBy(id="twotabsearchtextbox") 
	WebElement searchBox;
	
	public RoughPage() {
		PageFactory.initElements(getWebDriver(), this);
	}

	
	public void enterSearchBox(String text) {
		enterText("Search", searchBox, text);
	}
	
	
}
