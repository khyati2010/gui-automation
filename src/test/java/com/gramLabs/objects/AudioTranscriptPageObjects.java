package com.gramLabs.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.gramLabs.utility.MainClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/*
 * Class containing page elements/location for Login Page on mobile.
 */

public class AudioTranscriptPageObjects extends MainClass {

	public AudioTranscriptPageObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span/cite[contains(text(), 'Start Recording')]")
	public static WebElement listeningButton;

	@FindBy(css = "div.transcripts")
	public static WebElement converterTextBox;

}
