package com.nest.steps;

import static com.qmetry.qaf.automation.step.CommonStep.clear;
import static com.qmetry.qaf.automation.step.CommonStep.click;
import static com.qmetry.qaf.automation.step.CommonStep.sendKeys;

import java.awt.AWTException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.hamcrest.Matchers;
import org.openqa.selenium.JavascriptExecutor;

import com.nest.utilities.CommonUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.Validator;

public class NestVerifyApplyLeaveForSingleDaySteps {

	@QAFTestStep(description = "I select type of leave as {0} and if special then subtype as {1}")
	public void selectLeaveType(String type, String subType){
		// step implementation
		ConfigurationManager.getBundle().setProperty("leaveType",type);
		if (((String)ConfigurationManager.getBundle().getProperty("leaveType")).equals("Special"))
		{	
			click("applyLeave.specialType.lnk.loc");
			QAFExtendedWebElement subTypeLeave= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("applyLeave.specialSubType.radiobtn.loc"),subType));
			subTypeLeave.waitForVisible(5000);
			subTypeLeave.click();	
		}
	}
	
	@QAFTestStep(description = "I select leave from date as {0} and to date as {1}")
	public void selectLeaveDates(String dateFrom, String dateTo){
		// step implementation
		String dateFromArray[]=dateFrom.split("\\+");
		String dateToArray[]=dateTo.split("\\+");
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy"); 
		
	    Calendar c1 = Calendar.getInstance();
	    c1.add(Calendar.DAY_OF_MONTH,Integer.parseInt(dateFromArray[(dateFromArray.length)-1]));  
		String newFromDate = formatter.format(c1.getTime()); 
		
		Calendar c2 = Calendar.getInstance();
		c2.add(Calendar.DAY_OF_MONTH,Integer.parseInt(dateToArray[(dateToArray.length)-1]));  
		String newToDate = formatter.format(c2.getTime()); 
		
		ConfigurationManager.getBundle().setProperty("leaveDateFrom",newFromDate);
		ConfigurationManager.getBundle().setProperty("leaveDateTo",newToDate);
		
		if (((String)ConfigurationManager.getBundle().getProperty("leaveType")).equals("Special"))
		{	
			click("applyLeave.specialFromDate.txt.loc");
			clear("applyLeave.specialFromDate.txt.loc");
			sendKeys(newFromDate, "applyLeave.specialFromDate.txt.loc");
			CommonUtils.waitForPageToLoad(2000);
			
			click("applyLeave.specialToDate.txt.loc");
			clear("applyLeave.specialToDate.txt.loc");
			sendKeys(newToDate, "applyLeave.specialToDate.txt.loc");
			CommonUtils.waitForPageToLoad(2000);
		}
		else if (((String)ConfigurationManager.getBundle().getProperty("leaveType")).equals("PTO"))
		{
			click("applyLeave.ptoFromDate.txt.loc");
			clear("applyLeave.ptoFromDate.txt.loc");
			sendKeys(newFromDate, "applyLeave.ptoFromDate.txt.loc");
			CommonUtils.waitForPageToLoad(2000);
			
			click("applyLeave.ptoToDate.txt.loc");
			clear("applyLeave.ptoToDate.txt.loc");
			sendKeys(newToDate, "applyLeave.ptoToDate.txt.loc");
			CommonUtils.waitForPageToLoad(2000);
		}
	}
	
	@QAFTestStep(description = "I select the leave reason as {0} and if other then comment as {1}")
	public void selectLeaveReason(String reason, String comment){
		// step implementation
		if (((String)ConfigurationManager.getBundle().getProperty("leaveType")).equals("Special"))
			return;
		else if (((String)ConfigurationManager.getBundle().getProperty("leaveType")).equals("PTO"))
		{
			click("applyLeave.reason.dropdown.loc");
			QAFExtendedWebElement reasonType= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("applyLeave.reasonOptions.dropdown.loc"),reason));
			JavascriptExecutor js= (JavascriptExecutor)new WebDriverTestBase().getDriver();
			js.executeScript("arguments[0].scrollIntoView(true);", reasonType);
			reasonType.click();
			if (reason.equals("Other"))
			{
				CommonStep.waitForVisible("applyLeave.OtherOptions.txt.loc", 5000);
				click("applyLeave.OtherOptions.txt.loc");
				sendKeys(comment, "applyLeave.OtherOptions.txt.loc");
				CommonUtils.waitForPageToLoad(5000);
			}
		}
	}
	
	@QAFTestStep(description = "I select {0} leave")
	public void selectHalfOrFullDay(String day){
		// step implementation
		if (((String)ConfigurationManager.getBundle().getProperty("leaveType")).equals("Special"))
			return;
		else if (((String)ConfigurationManager.getBundle().getProperty("leaveType")).equals("PTO")){
			QAFExtendedWebElement dayType= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("applyLeave.halfDayOrFullDay.radiobtn.loc"),day));
			CommonUtils.scrollDownToView();
			dayType.waitForVisible(5000);
			dayType.click();
		}
	}
	
	@QAFTestStep(description = "I click on apply button")
	public void applyClick(){
		// step implementation
		if (((String)ConfigurationManager.getBundle().getProperty("leaveType")).equals("Special"))
			click("applyLeave.specialApply.btn.loc");
		else if (((String)ConfigurationManager.getBundle().getProperty("leaveType")).equals("PTO")) {
			CommonUtils.scrollDownToView();
			click("applyLeave.apply.btn.loc");
		}
		CommonUtils.waitForPageToLoad(10000);
	}
	
	@QAFTestStep(description = "leave should be applied successfully")
	public void applyConfirmation() throws InterruptedException{
		// step implementation
		Validator.verifyTrue(CommonStep.verifyVisible("applyLeave.applyConfirmation.info.loc"),"Leave already applied for this date","Leave applied successfully");
	}
	
	@QAFTestStep(description = "applied leave should be displayed correctly on my leave list page")
	public void verifyAppliedLeave() throws InterruptedException, AWTException{
		// step implementation
		CommonUtils.waitForPageToLoad(20000);
		CommonUtils.scrollUpToView();
		CommonUtils.scrollUpToView();
		CommonStep.waitForVisible("myLeave.leaveDate.radiobtn.loc");
		click("myLeave.leaveDate.radiobtn.loc");
		
		click("myLeave.leaveFrom.txt.loc");
		clear("myLeave.leaveFrom.txt.loc");
		sendKeys(((String)ConfigurationManager.getBundle().getProperty("leaveDateFrom")), "myLeave.leaveFrom.txt.loc");
		CommonUtils.waitForPageToLoad(5000);
		
		click("myLeave.leaveTo.txt.loc");
		clear("myLeave.leaveTo.txt.loc");
		sendKeys(((String)ConfigurationManager.getBundle().getProperty("leaveDateTo")), "myLeave.leaveTo.txt.loc");
		CommonUtils.waitForPageToLoad(5000);
		
		click("myLeave.search.btn.loc");
		CommonUtils.waitForPageToLoad(15000);
		
		String myLeaveDuration=CommonStep.getText("myLeave.leaveDuration.txt.loc");
		if(((String)ConfigurationManager.getBundle().getProperty("leaveDateFrom")).equals(((String)ConfigurationManager.getBundle().getProperty("leaveDateTo"))))
		{
			Validator.verifyThat(myLeaveDuration, Matchers.containsString("1"));
		}
		else
		{	int totalWorkingDays=(int) ConfigurationManager.getBundle().getProperty("workingDays");
			Validator.verifyThat(myLeaveDuration, Matchers.containsString(Integer.toString(totalWorkingDays)));
		}
		
		//Cancel the leave applied
		CommonStep.waitForVisible("applyLeave.leaveAction.dropdown.loc");
		CommonStep.click("applyLeave.leaveAction.dropdown.loc");
		CommonStep.click("applyLeave.leaveCancel.dropdown.loc");
		
		QAFExtendedWebElement submit= new QAFExtendedWebElement("applyLeave.submit.btn.loc");
//		JavascriptExecutor je = (JavascriptExecutor) new WebDriverTestBase().getDriver();
//		je.executeScript("arguments[0].scrollIntoView(true);",submit);
		CommonUtils.scrollDownToView();
		CommonStep.waitForVisible("applyLeave.submit.btn.loc");
		submit.click();
	}
	
}
