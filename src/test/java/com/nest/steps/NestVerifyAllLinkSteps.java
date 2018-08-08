package com.nest.steps;

import java.awt.AWTException;

import com.nest.utilities.CommonUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;

public class NestVerifyAllLinkSteps {

	@QAFTestStep(description = "clicks on View All link in the Request section")
	public static void clickViewAll() {
		CommonStep.click("viewAll.viewAll.lnk.loc");
		CommonUtils.waitForPageToLoad(10000);
	}
	
	@QAFTestStep(description = "User should be navigated to {0}")
	public static void viewAllPage(String title) throws AWTException, InterruptedException {
		CommonUtils.verifyTitleOfPage(title);
	}
	
	@QAFTestStep(description = "{0} tab is selected")
	public static void selectTab(String tab) {
		CommonStep.click("viewAll.home.lnk.loc");
		CommonUtils.waitForPageToLoad(10000);
		QAFExtendedWebElement tabName= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("viewAll.tab.lnk.loc"),tab));
		tabName.click();
		clickViewAll();	
	}
}
