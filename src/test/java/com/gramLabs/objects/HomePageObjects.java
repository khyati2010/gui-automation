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

public class HomePageObjects extends MainClass {

	public HomePageObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[contains(text(),'NLU Module')]")
	public static WebElement nluMenu;

	@FindBy(xpath = "//a[contains(text(),'OCR Module')]")
	public static WebElement ocrMenu;

	@FindBy(xpath = "//a[contains(text(),'Audio to Transcript')]")
	public static WebElement audioTranscriptMenu;
}
