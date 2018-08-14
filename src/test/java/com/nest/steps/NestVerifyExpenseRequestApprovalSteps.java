package com.nest.steps;

import com.nest.utilities.CommonUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;

public class NestVerifyExpenseRequestApprovalSteps {
	@QAFTestStep(description = "I click on raised request by employee")
	public void clickRaisedRequest() throws InterruptedException{
		// step implementation
		String title=NestVerifyExpenseFunctionalityWithInvalidDetailsSteps.map.get("titleActual");
		QAFExtendedWebElement request= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("teamReimbursement.title.lnk.loc"),title));
		request.click();
		CommonUtils.waitForPageToLoad(10000);
	}
	
	@QAFTestStep(description = "I enter comment as {0} and click on approve button")
	public void enterCommentAndClickApproveButton(String comment) throws InterruptedException{
		// step implementation
		CommonStep.sendKeys(comment, "teamReimbursement.comment.txt.loc");
		CommonStep.waitForEnabled("teamReimbursement.approve.btn.loc");
		CommonStep.click("teamReimbursement.approve.btn.loc");
	}
	
	@QAFTestStep(description = "I should be able to approve the raised request")
	public void verifySuccessfulApproval() {
		// step implementation
		CommonStep.verifyPresent("teamReimbursement.approveSuccess.msg.loc");
	}
}
