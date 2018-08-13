package com.nest.steps;

import static com.qmetry.qaf.automation.step.CommonStep.verifyAttribute;

import com.qmetry.qaf.automation.step.QAFTestStep;

public class NestBlankLoginStep {

	/**
	 * @param searchTerm
	 *            : search term to be searched
	 */
		
	@QAFTestStep(description = "I should get error message {0}")
	public void verifyErrorForBlankLogin(String error) {
		// step implementation
		verifyAttribute("login.username.txt.loc", "msg-required", error);
	}
}

