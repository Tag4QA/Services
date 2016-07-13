package com.tag4qa.util;

/***
 * Launch the browser based on the Param value given in the TestNG.xml file
 * @author mperumal
 * 
 */

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserLaunch {

	private WebDriver webdriver;
	private EventFiringWebDriver driver;
	private WebDriverWait wait;
	int timeOutInSeconds = 30;
	int defaultWaitTime = 10;
	private static final BrowserLaunch BROWSER_INSTANCE = new BrowserLaunch();
	private static final ThreadLocal<EventFiringWebDriver> THREADLOCAL_DRIVER_INSTANCE = new ThreadLocal<EventFiringWebDriver>();
	private static final ThreadLocal<WebDriverWait> THREADLOCAL_WAIT_INSTANCE = new ThreadLocal<WebDriverWait>();
	private final Logger LOGGER = Logger.getLogger(BrowserLaunch.class);

	public EventFiringWebDriver configBrowser(String browser) {
		LOGGER.info("Invoking the browser: " + browser);
		switch (browser.toLowerCase()) {
		case ("firefox"):
			FirefoxProfile ffprofile = null;
			ffprofile = new FirefoxProfile();
			ffprofile.setPreference("security.mixed_content.block_active_content", false);
			ffprofile.setPreference("security.mixed_content.block_display_content", false);
			ffprofile.setEnableNativeEvents(false);
			ffprofile.setAcceptUntrustedCertificates(true);
			ffprofile.setAssumeUntrustedCertificateIssuer(false);
			ffprofile.setPreference("browser.startup.homepage_override.mstone", "ignore");
			ffprofile.setPreference("startup.homepage_welcome_url.additional", "about:blank");
			webdriver = new FirefoxDriver(ffprofile);
			LOGGER.debug("Invoked the Firefox browser");
			break;
		case ("chrome"):
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
			webdriver = new ChromeDriver();
			LOGGER.debug("Invoked the Chrome browser");
			break;
		case ("android"):
			// ToDo the configuration
			break;
		case ("ios"):
			// ToDo the configuration
			break;
		default:
			FirefoxProfile ffprofile_default = null;
			ffprofile_default = new FirefoxProfile();
			ffprofile_default.setPreference("security.mixed_content.block_active_content", false);
			ffprofile_default.setPreference("security.mixed_content.block_display_content", false);
			ffprofile_default.setEnableNativeEvents(false);
			ffprofile_default.setAcceptUntrustedCertificates(true);
			ffprofile_default.setAssumeUntrustedCertificateIssuer(false);
			ffprofile_default.setPreference("browser.startup.homepage_override.mstone", "ignore");
			ffprofile_default.setPreference("startup.homepage_welcome_url.additional", "about:blank");
			webdriver = new FirefoxDriver(ffprofile_default);
			LOGGER.debug("Invoked the Firefox browser");
			break;
		}
		webdriver.manage().timeouts().implicitlyWait(defaultWaitTime, TimeUnit.SECONDS);
		wait = new WebDriverWait(webdriver, timeOutInSeconds);
		// Casted WebDriver as EventFiringWebDriver instance
		driver = new EventFiringWebDriver(webdriver);
		EventHandler handler = new EventHandler();
		driver.register(handler);
		THREADLOCAL_DRIVER_INSTANCE.set(driver);
		THREADLOCAL_WAIT_INSTANCE.set(wait);
		driver.manage().window().maximize();
		return driver;
	}

	public EventFiringWebDriver getEventFiringWebDriver() {
		return THREADLOCAL_DRIVER_INSTANCE.get();
	}

	public WebDriver getWebDriver() {
		return webdriver;
	}

	public WebDriverWait getWebDriverWait() {
		return THREADLOCAL_WAIT_INSTANCE.get();
	}

	public static BrowserLaunch getInstance() {
		return BROWSER_INSTANCE;
	}
}
