package com.nest.utilities;

import org.openqa.selenium.JavascriptExecutor;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;

public class VerifyHeaderOfPageUtility {
	
	public void verifyHeaderOfPage(String title){
		// step implementation
		
		QAFExtendedWebElement header= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("utility.heading.header.loc"),title));
		try {
		header.isDisplayed();
		}
		catch(Exception e)
		{
			JavascriptExecutor js= (JavascriptExecutor)new WebDriverTestBase().getDriver();
			js.executeScript("arguments[0].scrollIntoView(true);", header);
			header.isDisplayed();
		}
	}
}
