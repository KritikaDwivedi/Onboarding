package com.nest.steps;

import java.awt.AWTException;

import org.openqa.selenium.JavascriptExecutor;
import com.nest.utilities.CommonUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.Validator;

public class NestVerifyTravelRequestOnHomePageSteps {
	public static String requestId;
	public static String travelPurpose;
	
	@QAFTestStep(description = "user selects the {0} request row in the travel request tab")
	public static void getTravelRequest(String request) {
		requestId=CommonStep.getText("viewTravelRequest.requestId.txt.loc");
		travelPurpose=CommonStep.getText("viewTravelRequest.travelPurpose.txt.loc");
	}
	
	@QAFTestStep(description = "there should be two options on the travel request row {0} , {1}")
	public static void verifyTravelActions(String approve, String reject) {
		String actions[]= {approve,reject};
		CommonStep.click("viewTravelRequest.action.dropdown.loc");
		for(int i=0;i<2;i++)
		{
		QAFExtendedWebElement action= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("viewTravelRequest.actionOptions.dropdown.loc"),actions[i]));
		action.isDisplayed();
		}
		CommonStep.click("viewTravelRequest.action.dropdown.loc");
	}
	
	@QAFTestStep(description = "supervisor selects {0} action")
	public static void travelTakeAction(String actionToBeTaken) {
		CommonStep.waitForNotVisible("viewTravelRequest.confimation.msg.loc");
		CommonStep.click("viewTravelRequest.action.dropdown.loc");
		QAFExtendedWebElement action= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("viewTravelRequest.actionOptions.dropdown.loc"),actionToBeTaken));
		action.click();
		}
	
	@QAFTestStep(description = "provides comment as {0} and clicks on submit button and again clicks on submit button")
	public static void travelRequestAction(String comment) throws InterruptedException, AWTException {
		CommonStep.sendKeys(comment,"viewTravelRequest.comment.txt.loc");
		CommonUtils.buttonClick("Submit");
		
		QAFExtendedWebElement button= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("utility.button.btn.loc"),"Submit"));
		JavascriptExecutor js= (JavascriptExecutor)CommonUtils.driver;
		js.executeScript("arguments[0].scrollIntoView(true);", button);
		button.click();
		
		}
	
	@QAFTestStep(description = "supervisor should be able to {0} the request")
	public static void verifyActionConfirmation(){
		CommonStep.verifyPresent("viewTravelRequest.confimation.msg.loc");
		String request=CommonStep.getText("viewTravelRequest.requestId.txt.loc");
		Validator.verifyTrue(!request.equals(requestId), "Action is not performed successfully", "Action is performed successfully");
		}
	
	@QAFTestStep(description = "supervisor clicks on request")
	public static void viewTravelRequest() {
		CommonStep.click("viewTravelRequest.travelPurpose.txt.loc");
		
		}

	@QAFTestStep(description = "request should open")
	public static void openTravelRequest() {
		QAFExtendedWebElement request= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("viewTravelRequest.request.info.loc"),travelPurpose,requestId));
		request.isDisplayed();
		}

	}
	

