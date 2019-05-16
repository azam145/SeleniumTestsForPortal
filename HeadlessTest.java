/**
 * 	Selenium test
 *  To make Headless
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

/**
 *
 */
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

	/**
	 *
	 */
	public class DeviceToTest {

		private String deviceName;
		private String useragent;

		/**
		 *
		 * @param device
		 * @param userAgent
		 */
		DeviceToTest(String device, String userAgent) {
			this.deviceName = device;
			this.useragent = userAgent;
		}

		/**
		 *
		 * @return
		 */
		public String getDeviceName() {
			return deviceName;
		}

		/**
		 *
		 * @param deviceName
		 */
		public void setDeviceName(String deviceName) {
			this.deviceName = deviceName;
		}

		public String getUseragent() {
			return useragent;
		}

		public void setUseragent(String useragent) {
			this.useragent = useragent;
		}
	}

	/**
	 *
	 * @param whichBrowser
	 */
	public void UserAgentTests(String whichBrowser) {
		// choose which driver to use
		ArrayList<DeviceToTest> mobileUserAgents = new ArrayList<>();
		ArrayList<DeviceToTest> normalUserAgents = new ArrayList<>();
		String mobileUserA[] = {
			"HTC One M9 @ Mozilla/5.0 (Linux; Android 6.0; HTC One M9 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36",
			"AMSUNG SM-G930T1 @ Mozilla/5.0 (Linux; Android 6.0.1; SAMSUNG SM-G930T1 Build/MMB29M) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/4.0 Chrome/44.0.2403.133 Mobile Safari/537.36",
			"iPhone @ Mozilla/5.0 (iPhone; CPU iPhone OS 8_0_2 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8.0 Mobile Safari/600.1.4",
			"Nexus 5 @  Mozilla/5.0 (Linux; U; Android 4.4.4; Nexus 5 Build/KTU84P) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
			"Windows Phone OS 7.5 @ Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0)",
			"Tizen 2.0 @ Mozilla/5.0 (Linux; U; Tizen 2.0; en-us) AppleWebKit/537.1 (KHTML, like Gecko) Mobile TizenBrowser/2.0",
			"SymbianOS/9.4 @ Nokia5250/10.0.011 (SymbianOS/9.4; U; Series60/5.0 Mozilla/5.0; Profile/MIDP-2.1 Configuration/CLDC-1.1 ) AppleWebKit/525 (KHTML, like Gecko) Safari/525 3gpp-gba",
			"Android 4.4; Mobile @ Mozilla/5.0 (Android 4.4; Mobile; rv:18.0) Gecko/18.0 Firefox/18.0",

			// iphones
			"iPhone OS 6_1_4 @  Mozilla/5.0 (iPhone; CPU iPhone OS 6_1_4 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10B350 Safari/8536.25",
			"iPhone OS 7_0 Safari @ Mozilla/5.0 (iPhone; CPU iPhone OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53",
			"iPhone OS 6_1_3 Safari @ Mozilla/5.0 (iPhone; CPU iPhone OS 6_1_3 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10B329 Safari/8536.25",
			"iPad; CPU OS 6_1_3 @ Mozilla/5.0 (iPad; CPU OS 6_1_3 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10B329 Safari/8536.25",
			"iPhone OS 6_0_1 @ Mozilla/5.0 (iPhone; CPU iPhone OS 6_0_1 like Mac OS X; nl-nl) AppleWebKit/536.26 (KHTML, like Gecko) CriOS/23.0.1271.96 Mobile/10A523 Safari/8536.25 (1C019986-AF73-46A7-8F31-0E86ADFFCDB4)",
            // Android phones
            "Android 2.2 @ Mozilla/5.0 (Linux; U; Android 2.2) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
            "Nexus 5 @ Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko; googleweblight) Chrome/38.0.1025.166 Mobile Safari/535.19",
            "Samsung SM-G930T1 @ Mozilla/5.0 (Linux; Android 6.0.1; SAMSUNG SM-G930T1 Build/MMB29M) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/4.0 Chrome/44.0.2403.133 Mobile Safari/537.36 Mozilla/5.0 (Linux; Android 6.0.1; SM-G532G Build/MMB29T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.83 Mobile Safari/537.36",
            "Android 6.0.1 Safari @ Mozilla/5.0 (Linux; Android 6.0; vivo 1713 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.124 Mobile Safari/537.36",
            "Android 7 Samsung-G6 @ Mozilla/5.0 (Linux; Android 7.0; SM-G610M Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Mobile Safari/537.36",
            "Android 7.1 N2G47H @ Mozilla/5.0 (Linux; Android 7.1; Mi A1 Build/N2G47H) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.83 Mobile Safari/537.36",
            "Android 7.0 Samsung-G5 @ Mozilla/5.0 (Linux; Android 7.0; SM-G570M Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Mobile Safari/537.36",
            "Android 6.0 Nexus @ Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Mobile Safari/537.36",
            "Android 5.1 Neo 9 @ Mozilla/5.0 (Linux; Android 5.1; A37f Build/LMY47V) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.93 Mobile Safari/537.36",
            "Android vivo X7 @ Mozilla/5.0 (Linux; Android 5.1.1; vivo X7 Build/LMY47V; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/48.0.2564.116 Mobile Safari/537.36 baiduboxapp/8.6.5 (Baidu; P1 5.1.1)",
            "Android 6.0.1 Oppo R9s @  Mozilla/5.0 (Linux; Android 6.0.1; CPH1607 Build/MMB29M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/63.0.3239.111 Mobile Safari/537.36",

		};

		String normalUserA[] = {
			"Linux laptop @ Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0",
			"Macintosh @ Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; fr) Presto/2.9.168 Version/11.52",
			"Windows laptop @ Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36",
			"Macintosh @ Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.1 Safari/537.36",
			"Linux laptop @ Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36",
			"Linux laptop @ Mozilla/5.0 (X11; CrOS i686 3912.101.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.116 Safari/537.36",
			"FreeBSD laptop @ Mozilla/5.0 (X11; FreeBSD amd64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.115 Safari/537.36",
			"Macintosh @ Windows NT 6.0 laptop @ Opera/9.80 (Windows NT 6.0) Presto/2.12.388 Version/12.14",
			"Macintosh @ Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; fr) Presto/2.9.168 Version/11.52",
			"Linux laptop @ Opera/9.80 (X11; Linux i686; Ubuntu/14.10) Presto/2.12.388 Version/12.16",
			"FreeBSD laptop @ Mozilla/5.0 (X11; U; FreeBSD i386; zh-tw; rv:31.0) Gecko/20100101 Firefox/31.0 Opera/13.0",
			"Macintosh @ Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10; rv:33.0) Gecko/20100101 Firefox/33.0",
			"Linux laptop @ Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0",
			"Macintosh @ Mozilla/5.0 (X11; U; CrOS i686 9.10.0; en-US) AppleWebKit/532.5 (KHTML, like Gecko) Gecko/20100101 Firefox/29.0",
			"FreeBSD laptop @ Mozilla/5.0 (X11; FreeBSD amd64; rv:40.0) Gecko/20100101 Firefox/40.0",
			"OS/2 laptop @ Mozilla/5.0 (OS/2; U; Warp 4.5; en-US; rv:1.7.12) Gecko/20050922 Firefox/1.0.7",
			"Windows NT 6.1 laptop @ Mozilla/5.0 (Windows NT 6.1; rv:60.0) Gecko/20100101 Firefox/60.0 Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/533.20.25 (KHTML, like Gecko)", 							   // safari on windows
			"Macintosh @ Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/7046A194A", // safari on mac os
			"Linux laptop @ Mozilla/5.0 (X11; U; Linux x86_64; en-us) AppleWebKit/531.2+ (KHTML, like Gecko) Version/5.0", // safari linux
			"Windows NT 6.3 laptop @ Mozilla/5.0 (compatible, MSIE 11, Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko",           // IE windows
			"Windows NT 10.0 laptop @ Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10240", // Edge on windows
			// Most Common user agents - Chrome 73.0 Win10 12.6% in 2019
			"Windows NT 10.0 laptop @ Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36",
			// Chrome generic - Win 10 6.5%
			"Windows NT 10.0 laptop @ Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36",
			// Firefox 66.0 Win10 6.3%
			"Windows NT 10.0 laptop @ Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0",
			// Chrome 73.0 macOS  3.6%
            "Macintosh @ Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36",
			// Safari Generic macOS 3.1%
			"Macintosh @ Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.1 Safari/605.1.15",
			// Chrome 68.0 Win8 2.9%
			"Windows NT 6.2 laptop @ Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36",
			// Chrome 73.0 Win 7 2.7%
			"Windows NT 6.1 @ Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36",
			// 	Firefox 66.0 Linux 2.4%
			"Linux laptop @ Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:66.0) Gecko/20100101 Firefox/66.0",
			// Firefox 66.0 Win7 1.7%%
			"Windows NT 6.1 laptop @ Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0",
			// Another --	Chrome Generic Win10 1.6%
			"Windows NT 10.0 laptop @ Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.108 Safari/537.36!",
			// Chrome Generic macOS 1.6%
			"Macintosh @ Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36",
			// Chrome 73.0 macOS 1.6%
			"Macintosh @ Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36",
			// Firefox 66.0 macOS 1.4%
			"Macintosh @ Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:66.0) Gecko/20100101 Firefox/66.0",
			// Firefox 60.0 Win7 1.4%
			"Windows NT 6.1 @ Mozilla/5.0 (Windows NT 6.1; rv:60.0) Gecko/20100101 Firefox/60.0",
			// Tablets
			"tab @ Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53",
			"tab @ Mozilla/5.0 (iPad; CPU OS 5_1_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9B206 Safari/7534.48.3",
			"tab @ Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Mobile/11A465",
		};

		for (String device : mobileUserA) {
			String values[]  = device.split("@");
			mobileUserAgents(new DeviceToTest())
		}
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

	/**
	 *
	 * @param webDriver
	 * @param mobile
	 * @throws Exception
	 */
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
