package com.nest.utilities;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;

public class VerifyHeaderOfPageUtility {
	
	public void verifyHeaderOfPage(String title){
		// step implementation
		
		QAFExtendedWebElement header= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("utility.heading.header.loc"),title));
		header.isDisplayed();
	}
}
