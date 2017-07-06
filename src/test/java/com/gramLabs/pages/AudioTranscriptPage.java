package com.gramLabs.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;

import com.gramLabs.objects.AudioTranscriptPageObjects;
import com.gramLabs.utility.MainClass;
import com.gramLabs.utility.Reporting;

@Listeners(value = Reporting.class)

/*
 * Class containing logic methods for login page
 */

public class AudioTranscriptPage extends MainClass {

	static Logger logger = Logger.getLogger(AudioTranscriptPage.class);

	// Methods for mobile automation
	public void clickListeningButton() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.visibilityOf(AudioTranscriptPageObjects.listeningButton));
		AudioTranscriptPageObjects.listeningButton.click();
		getDriver().manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		logger.info("Clicked on listeningButton \n");
	}

	public void checkConverterTextBox() {
		wait.until(ExpectedConditions.visibilityOf(AudioTranscriptPageObjects.converterTextBox));
		AudioTranscriptPageObjects.converterTextBox.isDisplayed();
		logger.info("converterTextBox is displayed \n");
	}

}
