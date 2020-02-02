package BaseClass;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class BaseClass {

	protected final static String CHROME_EXE_EPATH = System.getProperty("user.dir")+ "\\src\\main\\resources\\executables\\chromedriver.exe";
	protected final static String CONFIG_FILE_PATH = System.getProperty("user.dir")+ "\\src\\main\\resources\\config.properties";
	protected final static String EXCEL_FILE_PATH = System.getProperty("user.dir")+ "\\src\\test\\resources\\FilloTestData.xlsx";
	protected static ChromeOptions options = null;
	protected static WebDriver driver = null;
	protected static Properties prop = null;
	protected static FileInputStream fis = null;
	protected static Logger log = Logger.getLogger(BaseClass.class);
	protected static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();
	protected static Fillo fillo =null;
	protected static Connection conn = null;
	protected static ThreadLocal<Fillo> threadFillo = new ThreadLocal<Fillo>();
	protected static ThreadLocal<Connection> threadConn = new ThreadLocal<Connection>();
	protected static Object[][] data;
	protected static final String queryToReadTestData = "SELECT * FROM TestData WHERE TCName =";
	protected static HashMap<String,String> testData;
	protected static Recordset rec;
	
	
	protected static synchronized ChromeOptions setChromeCapabilities() {
		options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		options.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-default-apps");
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-infobars");
		System.setProperty("webdriver.chrome.driver", CHROME_EXE_EPATH);
		return options;
	}

	protected static synchronized void initProperties() {
		try {
		prop = new Properties();
		fis = new FileInputStream(CONFIG_FILE_PATH);
		prop.load(fis);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected static synchronized String getPropertyValue(String key) {
		initProperties();
		return prop.getProperty(key);
	}
	
	protected static synchronized void createWebDriver() {
		
			if(getPropertyValue("browser").equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver(setChromeCapabilities());
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				threadDriver.set(driver);
			}
		
	}
	
	protected static synchronized WebDriver getWebDriver() {
//		createWebDriver();
		return threadDriver.get();
	}
	
	protected static synchronized void openUrl(String url)  {
		log.info("Opening "+url);
		try {
			getWebDriver().get(url);
		}catch(Error e) {
			log.error("Unable to open "+url);
			e.getMessage();
			throw e;
		}
	}

	protected static synchronized HashMap<String, String> readTestDataFromExcel(String methodName) throws FilloException {
		fillo = new Fillo();
		conn = fillo.getConnection(EXCEL_FILE_PATH);
		rec = conn.executeQuery(queryToReadTestData+"'"+methodName+"'");
		while(rec.next()) {
			testData = new HashMap<String, String>();
			for(String columnName : rec.getFieldNames()) {
					testData.put(columnName, rec.getField(columnName.toString()));
				}
			}
		return testData;
	}
	
	protected static synchronized void clickOnElement(String description, WebElement element) {
		log.info("Click on "+description+" button");
		try {
			element.click();
		}catch(Error e) {
			log.error("Unable to click the webelement: "+description);
			e.getMessage();
			throw e;
		}
	}
	
	protected static synchronized void enterText(String description, WebElement element, String text) {
		log.info("Entering text in "+description);
		try {
			element.sendKeys(text);
		}catch(Error e) {
			log.error("Unable to enter text in: "+description);
			e.getMessage();
			throw e;
		}
	}
	
	
	
	
	
	

}
