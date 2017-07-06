package com.gramLabs.test;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.gramLabs.pages.AudioTranscriptPage;
import com.gramLabs.utility.TestUpdater;

@Listeners(value = TestUpdater.class)
/*
 * Test class for login page on mobile. Scripts for login page are written here.
 */
public class AudioTranscriptPageTest extends AudioTranscriptPage {
	@Test(description = "Audio to Transcript Test with recording")
	 @TestData(testId = 10, runId = 1)
	public void audioTranscript() throws InterruptedException, IOException {
		openAudioTranscriptPage();
		clickListeningButton();
		checkConverterTextBox();
	}
}
