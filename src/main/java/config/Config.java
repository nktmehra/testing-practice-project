package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;

public class Config {

	protected final static String CHROME_EXE_EPATH=System.getProperty("user.dir")+"\\src\\main\\resources\\executables\\chromedriver.exe";
	protected final static String CONFIG_FILE_PATH=System.getProperty("user.dir")+"\\src\\main\\resources\\properties\\config.properties";
	protected static ChromeOptions options = null;
	protected static WebDriver driver = null;
	protected static Properties prop = new Properties();
	protected static FileInputStream fis = null;
	protected static Logger log = null;
	protected static ThreadLocal<WebDriver> driverThreadSafe = new ThreadLocal<WebDriver>();
	


	protected static synchronized ChromeOptions setChromeCapabilities() {
		options  = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		options.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-default-apps");
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-infobars");
		return options;
	}
	
	protected static synchronized void initProperties() {
		try {
			fis = new FileInputStream(CONFIG_FILE_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected static synchronized String getPropertyValue(String key) {
		try {
			return prop.getProperty(key);
		}catch(Exception e) {
			e.printStackTrace();
		}return null;
	}
	
	protected static synchronized void initLogs() {
		log = Logger.getLogger("devpinoyLogger");
	}
}