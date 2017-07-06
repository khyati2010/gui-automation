package com.gramLabs.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;

import com.gramLabs.objects.NluModulePageObjects;
import com.gramLabs.utility.MainClass;
import com.gramLabs.utility.Reporting;

@Listeners(value = Reporting.class)

/*
 * Class containing logic methods for login page
 */

public class NluModulePage extends MainClass {

	static Logger logger = Logger.getLogger(NluModulePage.class);

	// Methods for mobile automation
	public void inputChineseCharacter() {
		wait.until(ExpectedConditions.visibilityOf(NluModulePageObjects.chineseKeyboard));
		NluModulePageObjects.chineseKeyboard.sendKeys("土竹十卜土木木水口土竹廿");
		logger.info("Input Chinese Character \n");
	}

	public void clickConvertToEnglishButton() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.visibilityOf(NluModulePageObjects.convertToEnglishButton));
		NluModulePageObjects.convertToEnglishButton.click();
		getDriver().manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		logger.info("Clicked on convert To English Button \n");
	}

	public void englishConversionDisplay() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(NluModulePageObjects.englishDisplay));
		NluModulePageObjects.englishDisplay.getText().equals(property.getProperty("englishConversion"));
		logger.info("Check English Characters \n");
	}
	
}
