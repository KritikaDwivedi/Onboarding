package com.nest.steps;

import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;

public class NestLogoutStep {
	/**
	 * @param searchTerm
	 *            : search term to be searched
	 * @throws InterruptedException 
	 */
			
	@QAFTestStep(description = "user should land on the Login page")
	public void verifyNestLoginPage() {
		// step implementation
		CommonStep.verifyPresent("login.username.txt.loc");
	}
}
