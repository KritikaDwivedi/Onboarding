package com.nest.steps;

import static com.qmetry.qaf.automation.step.CommonStep.click;

import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;

public class NestLogoutStep {
	/**
	 * @param searchTerm
	 *            : search term to be searched
	 * @throws InterruptedException 
	 */
		
	@QAFTestStep(description = "clicks on Logout button")
	public void logoutButtonClick() throws InterruptedException {
		// step implementation
//		Thread.sleep(10000);
		click("home.logout.btn.loc");
	}
		
	@QAFTestStep(description = "user should land on the Login page")
	public void verifyNestLoginPage() {
		// step implementation
		CommonStep.verifyPresent("login.username.txt.loc");
	}
}
