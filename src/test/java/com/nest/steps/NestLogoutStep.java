package com.nest.steps;

import com.nest.utilities.CommonUtils;
import com.qmetry.qaf.automation.core.QAFTestBase;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;

public class NestLogoutStep {
	/**
	 * @param searchTerm
	 *            : search term to be searched
	 * @throws InterruptedException 
	 */
	@QAFTestStep(description = "I click on logout button")
	public void logoutButtonClick() {
		// step implementation
		CommonStep.waitForVisible("home.logout.btn.loc");
		CommonStep.click("home.logout.btn.loc");
	}		
	@QAFTestStep(description = "I should land on the login page")
	public void verifyNestLoginPage() {
		// step implementation
		CommonUtils.waitForPageToLoad(10000);
		CommonStep.verifyVisible("login.username.txt.loc");
	}
}
