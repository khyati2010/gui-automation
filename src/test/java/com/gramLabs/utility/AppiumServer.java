package com.gramLabs.utility;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

/*
 * Methods have been added to start and stop the Appium server
 */
public class AppiumServer {
	Logger logger = Logger.getLogger(AppiumServer.class);
	static AppiumDriverLocalService service;
	private static String NODE = "C:/Program Files (x86)/Appium/node.exe";
	private static String APPIUM = "C:/Program Files (x86)/Appium/node_modules/appium/bin/Appium.js";

	public void startAppiumServer() throws IOException, InterruptedException {
		service = AppiumDriverLocalService.buildService(
				new AppiumServiceBuilder().usingDriverExecutable(new File(NODE)).withAppiumJS(new File(APPIUM)));
		service.start();
		Thread.sleep(5000);
		if (service != null) {
			logger.info("Appium server started \n");
		}
	}

	public void stopAppiumServer() throws IOException {
		if (service != null) {
			service.stop();
		}
		logger.info("Appium server stopped \n");
	}
}
