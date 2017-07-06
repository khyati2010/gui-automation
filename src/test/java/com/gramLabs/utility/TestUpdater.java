package com.gramLabs.utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.codepine.api.testrail.TestRailException;


 //Functionality for updating the result on TestRail has been added here.
 

public class TestUpdater extends MainClass implements ITestListener {

	boolean update_result;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		/*update_result = Boolean.valueOf(property.getProperty("update_result"));
		if (update_result) {
			int[] Id = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestData.class).testId();
			for (int id : Id) {
				try {
					addTestResults(Integer.parseInt(property.getProperty("testrun_Id")), id, 1);

				} catch (TestRailException ex) {
					logger.info("Could not update the test result " + ex);
				}
			}
		}*/

	}

	@Override
	public void onTestFailure(ITestResult result) {
		/*update_result = Boolean.valueOf(property.getProperty("update_result"));
		if (update_result) {
			int[] Id = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestData.class).testId();
			for (int id : Id) {
				try {
					addTestResults(Integer.parseInt(property.getProperty("testrun_Id")), id, 5);
				} catch (TestRailException ex) {
					logger.info("Could not update the test result " + ex);
				}
			}
		}*/
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}

}
