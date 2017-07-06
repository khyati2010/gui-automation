package com.gramLabs.utility;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/*Drivers initialization class for WebDriver, Appium & Sikuli.
  Implemented functionality for testing on multiple browsers*/

public class DriverInit extends AppiumServer {
	public static ThreadLocal<WebDriver> ThreadDriver = new ThreadLocal<WebDriver>();
	// protected static Screen obj;
	protected static WebDriverWait wait;
	protected static AppiumDriver<MobileElement> adriver;
	public static String browserType;
	public static String OS;

	public static WebDriver getDriver() throws InterruptedException, IOException {
		OS = System.getProperty("os.name").toLowerCase();
		// obj = new Screen();
		WebDriver driver = ThreadDriver.get();
		String workingDir = System.getProperty("user.dir");
		// ImagePath.add(workingDir + "/img");
		if (driver == null) {
			if (browserType.equals("firefox")) {
				driver = new EventFiringWebDriver(new FirefoxDriver());
				 FirefoxProfile profile = new FirefoxProfile();
				    profile.setAssumeUntrustedCertificateIssuer(false);
				    profile.setAcceptUntrustedCertificates(false);
				ThreadDriver.set(driver);
				getDriver().manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
				wait = new WebDriverWait(driver, 30);
			}
			if (browserType.equals("chrome")) {
				if (isWindows()) {
					System.setProperty("webdriver.chrome.driver", workingDir + "//driver//chromedriver.exe");
				} else if (isMac()) {
					System.setProperty("webdriver.chrome.driver", workingDir + "//driver//chromedriver");
				} else if (isLinux()) {
					System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
				}
				driver = new EventFiringWebDriver(new ChromeDriver());
				ThreadDriver.set(driver);
				if (isMac()) {
					maximizeScreen();
				}
				getDriver().manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
				wait = new WebDriverWait(driver, 30);
			}
			if (browserType.equals("ie")) {
				System.setProperty("webdriver.ie.driver", workingDir + "//driver//IEDriverServer.exe");
				driver = new EventFiringWebDriver(new InternetExplorerDriver());
				ThreadDriver.set(driver);
				getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				wait = new WebDriverWait(driver, 30);
			}
		}
		return driver;
	}

	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	public static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	public static boolean isLinux() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
	}

	@Parameters("browser")
	public static void setupDriver(String browser) {
		browserType = browser;
	}

	public static void quitDriver() throws InterruptedException, IOException {
		getDriver().quit();
		ThreadDriver.set(null);
	}

	public void getADriver() throws MalformedURLException {
		if (adriver == null) {
			if (browserType.equals("mobile")) {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("newCommandTimeout", 20000);
				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Browser");
				capabilities.setCapability(MobileCapabilityType.VERSION, "6.0");
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
				capabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
				adriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				wait = new WebDriverWait(adriver, 120);
			}
		}

	}

	public static void maximizeScreen() throws InterruptedException, IOException {
		java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point position = new Point(0, 0);
		getDriver().manage().window().setPosition(position);
		Dimension maximizedScreenSize = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
		getDriver().manage().window().setSize(maximizedScreenSize);
	}

}
