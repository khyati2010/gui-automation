package com.gramLabs.test;

import java.io.IOException;

//import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.gramLabs.pages.OcrModulePage;
//import com.gramLabs.utility.TestUpdater;
//import com.gramLabs.pages.HomePage;

//@Listeners(value = TestUpdater.class)
/*
 * Test class for login page on mobile. Scripts for login page are written here.
 */
public class OcrModulePageTest extends OcrModulePage {
	@Test(description = "OCR Module Test With Chinese and English input and submit")
	// @TestData(testId = 10, runId = 1)
	public void submitChineseCharacters() throws InterruptedException,
			IOException {
		openOcrModulePage();
		inputChineseCharacters();
		clickAddTextButton();
		clickSubmitButton();
	}

	@Test(description = "OCR Module Test With Chinese and English input and submit")
	// @TestData(testId = 10, runId = 1)
	public void submitEnglishCharacters() throws InterruptedException,
			IOException {
		openOcrModulePage();
		inputEnglishCharacters();
		clickAddTextButton();
		clickSubmitButton();
	}

	@Test(description = "OCR Module Test With Chinese and English input and clear")
	// @TestData(testId = 10, runId = 1)
	public void clearAllChineseCharacters() throws InterruptedException, IOException {
		openOcrModulePage();
		inputChineseCharacters();
		clickAddTextButton();
		clickClearButton();
	}

	@Test(description = "OCR Module Test With Chinese and English input and clear")
	// @TestData(testId = 10, runId = 1)
	public void clearAllEnglishCharacters() throws InterruptedException, IOException {
		openOcrModulePage();
		inputEnglishCharacters();
		clickAddTextButton();
		clickClearButton();
	}
}
