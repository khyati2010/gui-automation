package com.gramLabs.utility;

import static org.testng.Assert.assertTrue;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.Case;
import com.codepine.api.testrail.model.CaseField;
import com.codepine.api.testrail.model.Project;
import com.codepine.api.testrail.model.Result;
import com.codepine.api.testrail.model.ResultField;
import com.codepine.api.testrail.model.Run;
import com.codepine.api.testrail.model.Section;
import com.codepine.api.testrail.model.Suite;
//import com.csa.objects.ORIndexPage;
import com.gramLabs.objects.OcrModulePageObjects;
import com.gramLabs.objects.AudioTranscriptPageObjects;
import com.gramLabs.objects.HomePageObjects;
import com.gramLabs.objects.NluModulePageObjects;

;

/* This is the MainClass and is being inherited in the pages.
 * TestRail integration functionality has been added in this class.
 * It also contains generic methods.
 */

public class MainClass extends DriverInit {

	public WebDriver driver;

	protected static TestRail testrail;
	protected static Project project;
	protected static Suite suite;
	protected static Section section;
	protected static Case testCase;
	protected static Run run;
	protected static Properties property;
	protected static Properties commonProperty;
	protected static InputStream input;
	static String workingDir = System.getProperty("user.dir");
	static Logger logger = Logger.getLogger(MainClass.class);
	protected static String browse;

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface TestData {
		// int testId()
		//
		// default 0;
		public int[] testId() default { 0 };

		int runId() default 0;
	}

	@BeforeSuite(alwaysRun = true)
	public void propertyInit() throws IOException {
		// Line to load the common properties to identify the application used
		input = new FileInputStream(workingDir + "//src//main//resources//config.properties");
		commonProperty = new Properties();
		commonProperty.load(input);

		// Condition to check whether running application is gramLabs then
		// gramLabs
		// property files is loaded else CSA property file is loaded.
		if (commonProperty.getProperty("applicationName").equals("gramLabs"))
			input = new FileInputStream(workingDir+ "//src//main//resources//config.properties");
		else
			input = new FileInputStream(workingDir + "//src//main//resources//config.properties");
		property = new Properties();
		property.load(input);
	}

	@BeforeClass(alwaysRun = true)
	@Parameters({ "browser" })
	public void browserUpdate(@Optional("firefox") String browser) {
		if (!commonProperty.getProperty("browser").isEmpty()) {
			browse = commonProperty.getProperty("browser");
		} else {
			browse = browser;
		}
	}

	@BeforeClass(alwaysRun = true)
	//@Parameters({ "testrun_Id" })
	public void runIDUpdate() {
		if (browse.equalsIgnoreCase("firefox")) {
			/*if (property.getProperty("testrun_Id").isEmpty()) {
				property.setProperty("testrun_Id", runid);
			}*/
		}
		if (browse.equalsIgnoreCase("chrome")) {
			/*if (property.getProperty("testrun_Id").isEmpty()) {
				property.setProperty("testrun_Id", runid);
			}*/
		}
		if (browse.equalsIgnoreCase("ie")) {
			/*if (property.getProperty("testrun_Id").isEmpty()) {
				property.setProperty("testrun_Id", runid);
			}*/
		}
	}

	/*@BeforeTest(alwaysRun = true)
	public void testRailInit() throws IOException {
		testrail = TestRail.builder(property.getProperty("testrail_link"),
						property.getProperty("testrail_username"),
						property.getProperty("testrail_password"))
				.applicationName(property.getProperty("application_name"))
				.build();
	}
*/
	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	protected void testMethodStart() throws InterruptedException, IOException {
		setupDriver(browse);
		if (browse.equalsIgnoreCase("mobile")) {
			startAppiumServer();
			getADriver();
		}
		if (!"mobile".equalsIgnoreCase(browse)) {
			getDriver().manage().window().maximize();
			getDriver().manage().deleteAllCookies();
		}
	}

	// Method to take screenshot and save in proper folder. Maintains the test
	// name associated with the failed test.
	@AfterMethod(alwaysRun = true)
	public void screenshotOnTestFailure(ITestResult testResult) throws InterruptedException, IOException, WebDriverException {
		if (testResult.getStatus() == ITestResult.FAILURE) 
		{
			File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,new File(workingDir + "/testdata/SeleniumFailureScreenshots/"+ testResult.getName() + ".jpg"));
		}
	}

	@AfterMethod(alwaysRun = true)
	protected void testMethodEnd() throws InterruptedException, IOException {
		if (!"mobile".equalsIgnoreCase(browserType)) 
		{
			quitDriver();
		} else {
			adriver.quit();
			stopAppiumServer();
		}
	}

	// Method to open secure assist home page
	public HomePageObjects openHomePage() throws InterruptedException,
			IOException {
		getDriver().get(property.getProperty("url"));
		logger.info("Home index page opened");
		return new HomePageObjects(getDriver());
	}

	public NluModulePageObjects openNluModulepage() throws InterruptedException, IOException {
		getDriver().get(property.getProperty("url"));
		logger.info("Opened NluModule Page");
		return new NluModulePageObjects(getDriver());
	}

	public AudioTranscriptPageObjects openAudioTranscriptPage()	throws InterruptedException, IOException {
		getDriver().get(property.getProperty("audio_url"));
		logger.info("Opened AudioTranscript page");
		return new AudioTranscriptPageObjects(getDriver());
	}

	public OcrModulePageObjects openOcrModulePage()	throws InterruptedException, IOException {
		getDriver().get(property.getProperty("ocr_url"));
		logger.info("Opened OcrModule page");
		return new OcrModulePageObjects(getDriver());
	}

	// Generic method for verifying the presence of element.
	public static boolean isElementPresent(By locator)	throws InterruptedException, IOException {
		// wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return getDriver().findElements(locator).size() > 0;
	}

	

	// Verify URL location of current page vs. expected page, pass in the
	// expected URL when writing the test.
	public void verifyLocation(String a) throws IOException,
			InterruptedException {
		String actualUrl = getDriver().getCurrentUrl();
		assertTrue(actualUrl.contains(a));
	}

	// Verify URL location of an opened tab vs the expected URL on that opened
	// tab. Pass in the expected URL when writing the test.
	public void verifyTabLocation(String a) throws IOException,
			InterruptedException {
		List<String> browserTabs = new ArrayList<>(getDriver()
				.getWindowHandles());
		getDriver().switchTo().window(browserTabs.get(1));
		String actualUrl = getDriver().getCurrentUrl();
		assertTrue(actualUrl.contains(a));
		getDriver().close();
		getDriver().switchTo().window(browserTabs.get(0));
	}

	// testing excel work
	public static String getTriggerDataFromExcel(String sheetName,
			int rowNumber, int columnNumber) throws Exception {
		ExcelReader.setExcelFile(workingDir + "/testdata/MacWorkbook.xlsx",
				sheetName);
		String Data = ExcelReader.getCellData(rowNumber, columnNumber);
		return Data;
	}

	// Assertions
	public static void AssertEquals(String expected, String actual) {
		Assert.assertEquals(actual, expected);
	}

	public static void AssertTrue(String message, Boolean condition) {
		assertTrue(condition, message);
	}

	public static void AssertFalse(String message, Boolean condition) {
		Assert.assertFalse(condition, message);
	}

	// This method will try 5 times to click an element
	public static void tryClickingAnElement(WebElement element) {
		Boolean flag = true;
		for (int i = 0; i < 5; i++) {
			if (flag) {
				try {
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
					flag = false;
				} catch (Exception e) {
					flag = true;
				}
			}
		}
	}

	// This method will check the visibility of an element and will return true
	// on success
	public static boolean tryCheckingVisibilityOfAnElement(WebElement element) {
		Boolean flag = false;
		for (int i = 0; i < 1; i++) {
			if (flag == false) {
				try {
					wait.until(ExpectedConditions.visibilityOf(element));
					flag = true;
				} catch (Exception e) {
					flag = false;
				}
			}
		}
		return flag;
	}

	// This method will check the invisibility of an element and will return
	// true on success
	public static boolean tryCheckingInVisibilityOfAnElement(By element) {
		Boolean flag = false;
		for (int i = 0; i < 1; i++) {
			if (flag == false) {
				try {
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(element));
					flag = true;
				} catch (Exception e) {
					flag = false;
				}
			}
		}
		return flag;
	}

	/*
	 * This method will check the visibility of an element once and will return
	 * true on success
	 */
	public static boolean tryCheckingVisibilityOfAnElementOnce(
			WebElement element) {
		Boolean flag = true;
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static void removeTextUsingBackSpace(WebElement element) {
		for (int i = 0; i <= 50; i++) {
			if (element.getAttribute("value").length() > 0) {
				element.sendKeys(Keys.BACK_SPACE);
			}
		}
	}

	// Generic method to verify a text on the page
	public static boolean isTextPresent(String text) {
		try {
			boolean result = getDriver().getPageSource().contains(text);
			return result;
		} catch (Exception e) {
			return false;
		}
	}
}
