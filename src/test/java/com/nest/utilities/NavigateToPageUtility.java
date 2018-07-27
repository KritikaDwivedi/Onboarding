package com.nest.utilities;

import static com.qmetry.qaf.automation.step.CommonStep.click;

import org.openqa.selenium.JavascriptExecutor;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;

public class NavigateToPageUtility {
	
	public void navigationToMenuAndSubMenu(String view, String menuOption, String subMenuOption) throws InterruptedException {
		// step implementation
		Thread.sleep(1000);
		JavascriptExecutor js= (JavascriptExecutor)new WebDriverTestBase().getDriver();
		click("home.navigation.pane.loc");
		Thread.sleep(5000);
		
		QAFExtendedWebElement menu= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("home.menu.options.loc"),view,menuOption));
		try
		{
			menu.click();
		}
		catch(Exception e)
		{	
			js.executeScript("arguments[0].scrollIntoView(true);", menu);
			menu.click();
		}
		Thread.sleep(5000);
		
		QAFExtendedWebElement submenu= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("home.submenu.options.loc"),view,subMenuOption));
		try
		{
			submenu.click();
		}
		catch(Exception e)
		{	
			js.executeScript("arguments[0].scrollIntoView(true);", submenu);
			submenu.click();
		}
		Thread.sleep(10000);
	}
}
