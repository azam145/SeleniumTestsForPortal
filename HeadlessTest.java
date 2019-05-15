/**
 * Simple program for running a headless Selenium test
 * Headless Chrome only supported on Linux using Xvfb
 */
package uk.gov.dvla.seleniumtests.dsl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;


public class HeadlessTest {
	
	public static void main(String args[]) throws Exception {
		
		// user can choose which browser to use at command line: htmlunit, phantomjs, chrome
		String whichBrowser = "htmlunit";
		if(args.length>0) {
			whichBrowser = args[0];
		}
		System.out.println("User chose driver: " + whichBrowser);
		HeadlessTest ht = new HeadlessTest();
		ht.UserAgentTests(whichBrowser);
	} // main

	public HeadlessTest() {

	}

	public void UserAgentTests(String whichBrowser) {
		// choose which driver to use
		ArrayList<String> mobileUserAgents = new ArrayList<String>();
		ArrayList<String> normalUserAgents = new ArrayList<String>();
		String mobileUserA[] = {
				"Mozilla/5.0 (Linux; Android 6.0; HTC One M9 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36",
				"Mozilla/5.0 (Linux; Android 6.0.1; SAMSUNG SM-G930T1 Build/MMB29M) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/4.0 Chrome/44.0.2403.133 Mobile Safari/537.36",
				"Mozilla/5.0 (iPhone; CPU iPhone OS 8_0_2 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8.0 Mobile Safari/600.1.4",
				"Mozilla/5.0 (Linux; U; Android 4.4.4; Nexus 5 Build/KTU84P) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
				"Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0)",
				"Mozilla/5.0 (Linux; U; Tizen 2.0; en-us) AppleWebKit/537.1 (KHTML, like Gecko) Mobile TizenBrowser/2.0",
				"Nokia5250/10.0.011 (SymbianOS/9.4; U; Series60/5.0 Mozilla/5.0; Profile/MIDP-2.1 Configuration/CLDC-1.1 ) AppleWebKit/525 (KHTML, like Gecko) Safari/525 3gpp-gba",
				"Mozilla/5.0 (Android 4.4; Mobile; rv:18.0) Gecko/18.0 Firefox/18.0"
		};

		String normalUserA[] = {
				"Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0",
				"Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; fr) Presto/2.9.168 Version/11.52",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36"
		};

		mobileUserAgents.addAll(Arrays.asList(mobileUserA));
		normalUserAgents.addAll(Arrays.asList(normalUserA));

		for(String userAgent: mobileUserAgents)
		{
			try {
				WebDriver webDriver = getWebDriver(whichBrowser, userAgent);
				// change the user-agent to reflect the type of device need to test
				PortalTest(webDriver, true);

			} catch (Exception e) {
				e.getMessage();
			}
		}
		for(String userAgent: normalUserAgents)
		{
			try {
				WebDriver webDriver = getWebDriver(whichBrowser, userAgent);
				// change the user-agent to reflect the type of device need to test
				PortalTest(webDriver, false);

			} catch (Exception e) {
				e.getMessage();
			}
		}
	}
	
	public void PortalTest(WebDriver webDriver, Boolean mobile) throws Exception {

		// run test
		webDriver.get("http://localhost:9000/digital/landing");

		if (webDriver.getPageSource().contains("Start now")) {
			System.out.println("Pass");
		} else {
			System.out.println("Fail");
		}



		// wait and then check the page title of the results page
		Thread.sleep(2000);
		System.out.println("******************************************");
		System.out.println(webDriver.getTitle());
		System.out.println("******************************************");

		// Identify click button
		WebElement element = webDriver.findElement(By.className("button-start"));
		// click on start
		element.click();
		
		// wait and then check the page title of the results page
        Thread.sleep(2000);
        System.out.println("******************************************");
		System.out.println(webDriver.getTitle());
		System.out.println("******************************************");

		// Identify check button
		element = webDriver.findElement(By.id("radio-yes"));
		element.click();
		// Identify click button
		element = webDriver.findElement(By.id("submitDls"));
		// click on start
		element.click();
		// wait and then check the page title of the results page
		Thread.sleep(2000);
		System.out.println("******************************************");
		System.out.println(webDriver.getTitle());
		System.out.println("******************************************");
		// Identify check button
		element = webDriver.findElement(By.id("radio-yes"));
		element.click();
		// Identify click button
		element = webDriver.findElement(By.id("submitDls"));
		// click on start
		element.click();

		// wait and then check the page title of the results page
		Thread.sleep(2000);
		System.out.println("******************************************");
		System.out.println(webDriver.getTitle());
		System.out.println("******************************************");

		if(!mobile) {
			if (webDriver.getPageSource().contains("This device is not a mobile, please use a mobile for best results")) {
				System.out.println("Pass");
			} else {
				System.out.println("Fail");
			}
		} else {
			if (webDriver.getPageSource().contains("This device is not a mobile, please use a mobile for best results")) {
				System.out.println("Fail");
			} else {
				System.out.println("Pass");
			}
		}

		// close browser
		webDriver.quit();
		
	}
	
	/**
	 * Cheap factory for retrieving WebDriver: HtmlUnit, PhantomJS, or Chrome 
	 * @param whichBrowser
	 * @return
	 */
	public WebDriver getWebDriver(String whichBrowser, String userAgent) throws Exception {
		// load file paths to phantomjs and chrome drivers
		Properties props = new Properties();
		props.load(this.getClass().getResourceAsStream("/path.properties"));

		// choose the driver
		WebDriver driver = null;
		if("chrome".toLowerCase().equals(whichBrowser)) {
			System.setProperty("webdriver.chrome.driver", props.getProperty("webdriver.chrome.driver"));
			driver = new ChromeDriver();
		} else if ("firefox".toLowerCase().equals(whichBrowser)){
            System.setProperty("webdriver.gecko.driver", props.getProperty("webdriver.gecko.driver"));
			//Firefox Profile Settings
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("general.useragent.override", userAgent);
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(profile);
            driver = new FirefoxDriver(options);
        } else {
			throw new Exception("Only valid webdrivers are: htmlunit|phantomjs|chrome");
		}
		
		return driver;
	}
	
	

} // class
