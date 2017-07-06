package com.gramLabs.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;

import com.gramLabs.objects.HomePageObjects;
import com.gramLabs.utility.MainClass;
import com.gramLabs.utility.Reporting;

@Listeners(value = Reporting.class)
/*
 * Class containing logic methods for login page
 */
public class HomePage extends MainClass {

	static Logger logger = Logger.getLogger(HomePage.class);

	// Methods for mobile automation
	public void nluMenu() {
		wait.until(ExpectedConditions.visibilityOf(HomePageObjects.nluMenu));
		HomePageObjects.nluMenu.click();
		logger.info("Clicked on NLU Menu \n");
	}

	public static void ocrMenu() {
		wait.until(ExpectedConditions.visibilityOf(HomePageObjects.ocrMenu));
		HomePageObjects.ocrMenu.click();
		logger.info("Clicked on OCR Menu \n");
	}

	public void audioTranscriptMenu() throws InterruptedException {
		wait.until(ExpectedConditions
				.visibilityOf(HomePageObjects.audioTranscriptMenu));
		HomePageObjects.audioTranscriptMenu.click();
		logger.info("Clicked on AudioTranscript Menu \n");
	}

}
