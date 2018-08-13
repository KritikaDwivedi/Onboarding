package com.nest.steps;

import static com.qmetry.qaf.automation.step.CommonStep.clear;
import static com.qmetry.qaf.automation.step.CommonStep.click;
import static com.qmetry.qaf.automation.step.CommonStep.sendKeys;

import org.hamcrest.Matchers;
import org.openqa.selenium.JavascriptExecutor;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.Validator;

public class NestVerifyApplyLeaveForSingleDaySteps {
	
	public static String leaveType="";
	public static String leaveDateFrom="";
	public static String leaveDateTo="";
	/**
	 * @param searchTerm
	 *            : search term to be searched
	 * @throws InterruptedException 
	 */
	
	@QAFTestStep(description = "I select type of leave as {0} and if special then subtype as {1}")
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
	
	@QAFTestStep(description = "I select leave from date as {0} and to date as {1}")
	public void selectLeaveDates(String dateFrom, String dateTo) throws InterruptedException{
		// step implementation
		leaveDateFrom=dateFrom;
		leaveDateTo=dateTo;
		if (leaveType.equals("Special"))
		{	
			click("applyLeave.specialFromDate.txt.loc");
			clear("applyLeave.specialFromDate.txt.loc");
			sendKeys(dateFrom, "applyLeave.specialFromDate.txt.loc");
			Thread.sleep(2000);
			click("applyLeave.specialToDate.txt.loc");
			clear("applyLeave.specialToDate.txt.loc");
			sendKeys(dateTo, "applyLeave.specialToDate.txt.loc");
			Thread.sleep(2000);
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
	
	@QAFTestStep(description = "I select the leave reason as {0} and if other then comment as {1}")
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
//				Thread.sleep(2000);
				CommonStep.waitForPresent("applyLeave.OtherOptions.txt.loc", 2000);
				click("applyLeave.OtherOptions.txt.loc");
				sendKeys(comment, "applyLeave.OtherOptions.txt.loc");
				Thread.sleep(1000);
			}
			
		}
	}
	
	@QAFTestStep(description = "I select {0} leave")
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
	
	@QAFTestStep(description = "I click on apply button")
	public void applyClick() throws InterruptedException{
		// step implementation
		if (leaveType.equals("Special"))
			click("applyLeave.specialApply.btn.loc");
		else if (leaveType.equals("PTO"))
			click("applyLeave.apply.btn.loc");
		Thread.sleep(15000);
	}
	
	@QAFTestStep(description = "leave should be applied successfully")
	public void applyConfirmation() throws InterruptedException{
		// step implementation
		CommonStep.verifyPresent("applyLeave.applyConfirmation.info.loc");
	}
	
	@QAFTestStep(description = "applied leave should be displayed correctly on my leave list page")
	public void verifyAppliedLeave() throws InterruptedException{
		// step implementation
		
		
//		QAFExtendedWebElement leaveDate=new QAFExtendedWebElement((ConfigurationManager.getBundle().getString("myLeave.leaveDate.radiobtn.loc")));
		Thread.sleep(5000);
		click("myLeave.leaveDate.radiobtn.loc");
		
		click("myLeave.leaveFrom.txt.loc");
		clear("myLeave.leaveFrom.txt.loc");
		sendKeys(leaveDateFrom, "myLeave.leaveFrom.txt.loc");
		Thread.sleep(5000);
		
		click("myLeave.leaveTo.txt.loc");
		clear("myLeave.leaveTo.txt.loc");
		sendKeys(leaveDateTo, "myLeave.leaveTo.txt.loc");
		Thread.sleep(5000);
		
		click("myLeave.search.btn.loc");
		Thread.sleep(5000);
		
		String myLeaveDuration=CommonStep.getText("myLeave.leaveDuration.txt.loc");
		if(leaveDateFrom.equals(leaveDateTo))
		{
			Validator.verifyThat("1", Matchers.containsString(myLeaveDuration));
		}
		else
		{
			int totalWorkingDays=NestVerifyApplyLeaveForMultipleDaysIncludingHolidaySteps.workingDays;
			Validator.verifyThat(myLeaveDuration, Matchers.containsString(Integer.toString(totalWorkingDays)));
		}
		
		
	}
	
}
