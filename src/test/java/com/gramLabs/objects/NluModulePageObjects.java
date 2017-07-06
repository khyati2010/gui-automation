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

public class NluModulePageObjects extends MainClass {

	public NluModulePageObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "textarea#keyboard")
	public static WebElement chineseKeyboard;

	@FindBy(xpath = "//a[contains(text(),'Convert to English')]")
	public static WebElement convertToEnglishButton;
	
	@FindBy(xpath = "//div//textarea[2]")
	public static WebElement englishDisplay;
}
