package com.nest.utilities;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;

public class ButtonClick {
	public void buttonClicking(String buttonName) throws InterruptedException {
		// step implementation
//		JavascriptExecutor js= (JavascriptExecutor)new WebDriverTestBase().getDriver();
		QAFExtendedWebElement button= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("utility.button.btn.loc"),buttonName));
//		js.executeScript("arguments[0].scrollIntoView(true);", button);
		button.click();
	}
}
