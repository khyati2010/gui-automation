package com.gramLabs.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;

import com.gramLabs.objects.OcrModulePageObjects;
import com.gramLabs.utility.MainClass;
import com.gramLabs.utility.Reporting;

@Listeners(value = Reporting.class)

/*
 * Class containing logic methods for login page
 */

public class OcrModulePage extends MainClass {

	static Logger logger = Logger.getLogger(OcrModulePageObjects.class);
	
//	public static void verifyText() {
//		AssertEquals(OcrModulePageObjects.lessons.getText(), property.getProperty("lesson"));
//		AssertEquals(OcrModulePageObjects.time.getText(), property.getProperty("time"));
//		AssertEquals(OcrModulePageObjects.language.getText(), property.getProperty("language"));
//		AssertEquals(OcrModulePageObjects.level.getText(), property.getProperty("level"));
//	}
	
	public void inputChineseCharacters() {
		wait.until(ExpectedConditions.visibilityOf(OcrModulePageObjects.inputTextBox));
		OcrModulePageObjects.inputTextBox.sendKeys("test");
		//OcrModulePageObjects.inputTextBox.sendKeys("土竹十扑土木木水口土土廿");
		System.out.println(OcrModulePageObjects.inputTextBox);
		logger.info("Clicked on Menu \n");
	}
	
	public void inputEnglishCharacters() {
		wait.until(ExpectedConditions.visibilityOf(OcrModulePageObjects.inputTextBox));
		OcrModulePageObjects.inputTextBox.sendKeys(property.getProperty("inputEnglishCharacters"));
		logger.info("Clicked on Menu \n");
	}

	public void clickAddTextButton() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.visibilityOf(OcrModulePageObjects.addTextButton));
		OcrModulePageObjects.addTextButton.click();
		getDriver().manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		logger.info("Clicked on addText Button \n");
	}

	public void clickSubmitButton() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.visibilityOf(OcrModulePageObjects.submitButton));
		OcrModulePageObjects.submitButton.click();
		getDriver().manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		logger.info("Clicked on submit Button \n");
	}
	
	public void clickClearButton() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.visibilityOf(OcrModulePageObjects.clearButton));
		OcrModulePageObjects.clearButton.click();
		getDriver().manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		logger.info("Clicked on clear Button \n");
	}
	
	/*public void openImage() throws InterruptedException {
		OcrModulePageObjects.
		
	}*/
}
