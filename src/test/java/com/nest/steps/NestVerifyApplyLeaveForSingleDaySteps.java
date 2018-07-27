package com.nest.steps;

import static com.qmetry.qaf.automation.step.CommonStep.clear;
import static com.qmetry.qaf.automation.step.CommonStep.click;
import static com.qmetry.qaf.automation.step.CommonStep.sendKeys;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;

import com.nest.utilities.NavigateToPageUtility;
import com.nest.utilities.VerifyHeaderOfPageUtility;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;

public class NestVerifyApplyLeaveForSingleDaySteps {
	
	NavigateToPageUtility navigate=new NavigateToPageUtility();
	VerifyHeaderOfPageUtility head=new VerifyHeaderOfPageUtility();
	public static String leaveType="";
	/**
	 * @param searchTerm
	 *            : search term to be searched
	 * @throws InterruptedException 
	 */
	
	@QAFTestStep(description = "user selects Type of leave as {0} and if special then subtype as {1}")
	public void selectLeaveType(String type, String subType) throws InterruptedException{
		// step implementation
		leaveType=type;
		if (leaveType.equals("Special"))
		{	
			click("applyLeave.specialType.lnk.loc");
			Thread.sleep(5000);
			QAFExtendedWebElement subTypeLeave= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("applyLeave.specialSubType.radiobtn.loc"),subType));
			subTypeLeave.click();
		}
		else
		{
			return;
		}
	}
	
	@QAFTestStep(description = "select Leave from date as {0} and to date as {1}")
	public void selectLeaveDates(String dateFrom, String dateTo) throws InterruptedException{
		// step implementation
		if (leaveType.equals("Special"))
		{
			click("applyLeave.specialFromDate.txt.loc");
			clear("applyLeave.specialFromDate.txt.loc");
			sendKeys(dateFrom, "applyLeave.specialFromDate.txt.loc");
			Thread.sleep(15000);
			click("applyLeave.specialToDate.txt.loc");
			clear("applyLeave.specialToDate.txt.loc");
			sendKeys(dateTo, "applyLeave.specialToDate.txt.loc");
			Thread.sleep(10000);
		}
		else if (leaveType.equals("PTO"))
		{
			click("applyLeave.ptoFromDate.txt.loc");
			clear("applyLeave.ptoFromDate.txt.loc");
			sendKeys(dateFrom, "applyLeave.ptoFromDate.txt.loc");
			Thread.sleep(5000);
			click("applyLeave.ptoToDate.txt.loc");
			clear("applyLeave.ptoToDate.txt.loc");
			sendKeys(dateTo, "applyLeave.ptoToDate.txt.loc");
			Thread.sleep(5000);
		}
	}
	
	@QAFTestStep(description = "selects the leave reason as {0} and if other then comment as {1}")
	public void selectLeaveReason(String reason, String comment) throws InterruptedException{
		// step implementation
		if (leaveType.equals("Special"))
			return;
		else if (leaveType.equals("PTO"))
		{
			click("applyLeave.reason.dropdown.loc");
			QAFExtendedWebElement reasonType= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("applyLeave.reasonOptions.dropdown.loc"),reason));
			JavascriptExecutor js= (JavascriptExecutor)new WebDriverTestBase().getDriver();
			js.executeScript("arguments[0].scrollIntoView(true);", reasonType);
			reasonType.click();
			if (reason.equals("Other"))
			{
				Thread.sleep(2000);
				click("applyLeave.OtherOptions.txt.loc");
				sendKeys(comment, "applyLeave.OtherOptions.txt.loc");
				Thread.sleep(1000);
			}
			
		}
	}
	
	@QAFTestStep(description = "selects {0} Leave")
	public void selectHalfOrFullDay(String day){
		// step implementation
		if (leaveType.equals("Special"))
			return;
		else if (leaveType.equals("PTO"))
		{
		QAFExtendedWebElement dayType= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("applyLeave.halfDayOrFullDay.radiobtn.loc"),day));
		JavascriptExecutor js= (JavascriptExecutor)new WebDriverTestBase().getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", dayType);
		dayType.click();
		}
	}
	
	@QAFTestStep(description = "clicks on Apply button")
	public void applyClick(){
		// step implementation
		if (leaveType.equals("Special"))
			click("applyLeave.specialApply.btn.loc");
		else if (leaveType.equals("PTO"))
			click("applyLeave.apply.btn.loc");
	}
	
}
