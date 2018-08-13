package com.nest.steps;

import java.awt.AWTException;
import java.util.List;

import com.nest.beans.RecognitionApprovalBean;
import com.nest.utilities.CommonUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;

public class NestVerifyBrightSpartWithReportingManagerView {
	
	@QAFTestStep(description = "the raised R & R request with status as {0} for {1} should be present")
	public static void verifyPresenceOfRnRrequest(String status, String loggedUser) throws InterruptedException {
		
		CommonStep.click("rnrRequest.fromDate.txt.loc");
		CommonStep.click("rnrRequest.today.btn.loc");
		CommonStep.click("rnrRequest.toDate.txt.loc");
		CommonStep.click("rnrRequest.today.btn.loc");
		CommonStep.click("rnrRequest.status.dropdown.loc");
		QAFExtendedWebElement requestStatus= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("rnrRequest.statusOption.dropdown.loc"),status));
		requestStatus.click();
		CommonUtils.buttonClick("Search");
		CommonUtils.waitForPageToLoad(10000);
		
		if (loggedUser.equals("Reporting Manager")||loggedUser.equals("Delivery Manager")) {
			QAFExtendedWebElement request= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("rnrRequest.requestManager.lnk.loc"),(String)ConfigurationManager.getBundle().getProperty("brightSpark.nominee"),status,(String)ConfigurationManager.getBundle().getProperty("awardType")));
			List<QAFWebElement> approvedList=request.findElements(String.format(ConfigurationManager.getBundle().getString("rnrRequest.requestManager.lnk.loc"),(String)ConfigurationManager.getBundle().getProperty("brightSpark.nominee"),status,(String)ConfigurationManager.getBundle().getProperty("awardType")));
			approvedList.get(0).isPresent();
		}
		if(loggedUser.equals("HR")) {
			QAFExtendedWebElement request= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("rnrRequest.requestHR.lnk.loc"),(String)ConfigurationManager.getBundle().getProperty("brightSpark.nominee"),status,(String)ConfigurationManager.getBundle().getProperty("awardType")));
			List<QAFWebElement> approvedList=request.findElements(String.format(ConfigurationManager.getBundle().getString("rnrRequest.requestHR.lnk.loc"),(String)ConfigurationManager.getBundle().getProperty("brightSpark.nominee"),status,(String)ConfigurationManager.getBundle().getProperty("awardType")));
			approvedList.get(0).isPresent();
		}
	}
	
	@QAFTestStep(description = "I open the raised R & R request with status as {0} for {1}")
	public static void openRnRrequest(String status, String loggedUser) {
		if (loggedUser.equals("Reporting Manager")||loggedUser.equals("Delivery Manager")) {
			QAFExtendedWebElement request= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("rnrRequest.requestManager.lnk.loc"),(String)ConfigurationManager.getBundle().getProperty("brightSpark.nominee"),status,(String)ConfigurationManager.getBundle().getProperty("awardType")));
			List<QAFWebElement> approvedList=request.findElements(String.format(ConfigurationManager.getBundle().getString("rnrRequest.requestManager.lnk.loc"),(String)ConfigurationManager.getBundle().getProperty("brightSpark.nominee"),status,(String)ConfigurationManager.getBundle().getProperty("awardType")));
			approvedList.get(0).click();
		}
		if(loggedUser.equals("HR")) {
			QAFExtendedWebElement request= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("rnrRequest.requestHR.lnk.loc"),(String)ConfigurationManager.getBundle().getProperty("brightSpark.nominee"),status,(String)ConfigurationManager.getBundle().getProperty("awardType")));
			List<QAFWebElement> approvedList=request.findElements(String.format(ConfigurationManager.getBundle().getString("rnrRequest.requestHR.lnk.loc"),(String)ConfigurationManager.getBundle().getProperty("brightSpark.nominee"),status,(String)ConfigurationManager.getBundle().getProperty("awardType")));
			approvedList.get(0).click();
		}
		CommonUtils.waitForPageToLoad(10000);
	}
	
	@QAFTestStep(description = "there should be Bright Spark request with Approve and Reject buttons")
	public static void verifyUIRnRrequest() throws AWTException, InterruptedException {
		CommonUtils.scrollDownToView();
		CommonStep.verifyPresent("rnrRequest.approve.btn.loc");
		CommonStep.verifyPresent("rnrRequest.reject.btn.loc");
	}
	
	@QAFTestStep(description = "I {0} the request when logged in as {1} with {2} and {3}")
	public static void actionOnRnRrequest(String action,String loggedUser, String userName, String password) throws AWTException, InterruptedException {
		NestValidLoginStep.nestLogin(userName,password);
		NestVerifyUIMyPostsPageSteps.changeView("Manager View");
		NestVerifyUIMyPostsPageSteps.navigationToPage("R & R","R & R Requests");
		openRnRrequest("Pending", loggedUser);
		
		if (action.equals("approve")) {
			CommonUtils.scrollDownToView();
			CommonUtils.scrollDownToView();;
			CommonStep.verifyVisible("rnrRequest.approve.btn.loc");
			CommonStep.click("rnrRequest.approve.btn.loc");
		}
		
		if (action.equals("reject")) {
			CommonUtils.scrollDownToView();
			CommonUtils.scrollDownToView();
			CommonStep.verifyVisible("rnrRequest.reject.btn.loc");
			CommonStep.click("rnrRequest.reject.btn.loc");
		}
		
		RecognitionApprovalBean bean = new RecognitionApprovalBean();
		bean.fillRandomData();
		bean.fillUiElements();
		
		CommonUtils.buttonClick("Submit");
		CommonStep.verifyPresent("rnrRequest.confirmation.msg.loc");
		CommonStep.waitForNotVisible("rnrRequest.confirmation.msg.loc");
		CommonUtils.waitForPageToLoad(20000);
			
	}
	
}
