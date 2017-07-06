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

public class OcrModulePageObjects extends MainClass {
	
	public OcrModulePageObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@type='text']")
	public static WebElement inputTextBox;
	
	@FindBy(xpath="//button[contains(text(),'Add Text')]")
	public static WebElement addTextButton;
	
	@FindBy(xpath="//a[contains(text(),'Submit')]")
	public static WebElement submitButton;	
	
	@FindBy(xpath="//a[contains(text(),'Clear Canvas')]")
	public static WebElement clearButton;	
	

	public static WebElement openImage;
}
