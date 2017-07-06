package com.gramLabs.test;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.gramLabs.pages.HomePage;
//import com.gramLabs.utility.TestUpdater;

//@Listeners(value = TestUpdater.class)
/*
 * Test class for login page on mobile. Scripts for login page are written here.
 */
public class HomePageTest extends HomePage {
	@Test(description = "Home Page Test With Correct links")
//	@TestData(testId = 10, runId = 1)
	public void HomePageTestLinks()	throws InterruptedException, IOException {
		openHomePage();
		nluMenu();
		ocrMenu();
		audioTranscriptMenu();
	}

}
