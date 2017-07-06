package com.gramLabs.test;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.gramLabs.pages.NluModulePage;
//import com.gramLabs.utility.TestUpdater;

//@Listeners(value = TestUpdater.class)
/*
 * Test class for login page on mobile. Scripts for login page are written here.
 */
public class NluModulePageTest extends NluModulePage {
	@Test(description = "NLU Module Test With chinese and english characters")
	//@TestData(testId = 10, runId = 1)
	public void testWithChineseCharacters() throws InterruptedException, IOException {
		openNluModulepage();
		inputChineseCharacter();
		clickConvertToEnglishButton();
		englishConversionDisplay();
	}

}
