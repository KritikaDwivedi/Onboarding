package com.nest.steps;

import com.nest.utilities.CommonUtils;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;

public class NestLogoutStep {
	/**
	 * @param searchTerm
	 *            : search term to be searched
	 * @throws InterruptedException 
	 */
	@QAFTestStep(description = "clicks on logout button")
	public void logoutButtonClick() {
		// step implementation
		CommonStep.click("home.logout.btn.loc");
	}		
	@QAFTestStep(description = "user should land on the login page")
	public void verifyNestLoginPage() {
		// step implementation
		CommonUtils.waitForPageToLoad(10000);
		CommonStep.verifyPresent("login.username.txt.loc");
	}
}
