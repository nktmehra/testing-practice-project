package listeners;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import BaseClass.BaseClass;

public class Listeners extends BaseClass implements ITestListener  {

	protected static Logger log = Logger.getLogger(Listeners.class);
	
	public void onFinish(ITestContext result) {
		
	}

	public void onStart(ITestContext result) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailure(ITestResult result) {
		log.info("<<------ Method----"+result.getMethod().getMethodName()+"------failed due to error----"+result.getThrowable().getMessage() +"--------");
	}
	
	public void onTestSkipped(ITestResult result) {

		
	}


	public void onTestStart(ITestResult result) {
		log.info("<<------ Method----"+result.getMethod().getMethodName()+"------Execution started--------");
	}

	public void onTestSuccess(ITestResult result) {
		log.info("<<------ Method----"+result.getMethod().getMethodName()+"------Completed Successfully--------");
	}




}
