package testCases;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;

import pageClasses.HomePage;

public class Rough extends BaseTest {

	private HomePage login;
	
	
	
	@Test(dataProvider="data")
	public void filloTest(HashMap<String, String> testData) {
		login = new HomePage();
		

	}
	
	

	@DataProvider(name="data")
	public Object[][] hashData(Method m) throws FilloException{
		return new Object[][] {
			{
			readTestDataFromExcel(m.getName())
			}
		};
	}

}
