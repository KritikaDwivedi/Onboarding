package com.nest.steps;

import static com.qmetry.qaf.automation.step.CommonStep.clear;
import static com.qmetry.qaf.automation.step.CommonStep.click;
import static com.qmetry.qaf.automation.step.CommonStep.sendKeys;
import static com.qmetry.qaf.automation.step.CommonStep.verifyPresent;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.core.QAFTestBase;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;

public class NestVerifyExpenseFunctionalityWithInvalidDetailsSteps {
	/**
	 * @param searchTerm
	 *            : search term to be searched
	 * @throws InterruptedException 
	 */
	public static String expenseDateActual="";
	public static HashMap<String,String> map=new HashMap<>();
	public static List<QAFWebElement> categoryOptionsList;
	
	@QAFTestStep(description = "I click on new expense button")
	public void expenseButtonClick() throws InterruptedException {
		// step implementation
		CommonStep.waitForVisible("newExpense.newExpense.btn.loc");
		click("newExpense.newExpense.btn.loc");
		QAFTestBase.pause(3000);
	}	
	@QAFTestStep(description = "I fill the {0} details")
	public void expenseEnterDetails(String details) {
		// step implementation
		if(details.equals("invalid")) 
		{
			map.put("titleActual",(String)ConfigurationManager.getBundle().getProperty("expense.invalidTitle"));
			map.put("dateActual",(String)ConfigurationManager.getBundle().getProperty("expense.expense.invalidExpenseDate"));
			map.put("projectActual",(String)ConfigurationManager.getBundle().getProperty("expense.invalidProject"));
			map.put("otherProjectActual",(String)ConfigurationManager.getBundle().getProperty("expense.invalidProjectOther"));
			map.put("categoryActual",(String)ConfigurationManager.getBundle().getProperty("expense.invalidCategory"));
			map.put("amountActual",(String)ConfigurationManager.getBundle().getProperty("expense.invalidAmount"));
		}
		else
		{
			map.put("titleActual",(String)ConfigurationManager.getBundle().getProperty("expense.validTitle"));
			map.put("dateActual",(String)ConfigurationManager.getBundle().getProperty("expense.expense.validExpenseDate"));
			map.put("projectActual",(String)ConfigurationManager.getBundle().getProperty("expense.validProject"));
			map.put("otherProjectActual",(String)ConfigurationManager.getBundle().getProperty("expense.validProjectOther"));
			map.put("categoryActual",(String)ConfigurationManager.getBundle().getProperty("expense.validCategory"));
			map.put("amountActual",(String)ConfigurationManager.getBundle().getProperty("expense.validAmount"));
		}
		click("newExpense.title.txt.loc");
		sendKeys(map.get("titleActual"), "newExpense.title.txt.loc");
		
		click("newExpense.expenseDate.txt.loc");
		clear("newExpense.expenseDate.txt.loc");
		sendKeys(map.get("dateActual"), "newExpense.expenseDate.txt.loc");
		
		click("newExpense.project.txt.loc");
		sendKeys(map.get("projectActual"), "newExpense.project.txt.loc");
		
		if (map.get("projectActual").equals("Other"))
		{	Actions act=new Actions(new WebDriverTestBase().getDriver());
			act.sendKeys(Keys.ENTER).perform();
			click("newExpense.projectOther.txt.loc");
			sendKeys(map.get("otherProjectActual"), "newExpense.projectOther.txt.loc");
		}
		
		click("newExpense.category.txt.loc");
		QAFExtendedWebElement categoryOptions= new QAFExtendedWebElement("newExpense.categoryOptions.dropdown.loc");
		categoryOptionsList=categoryOptions.findElements("myNominations.approved.data.loc");
		sendKeys(map.get("categoryActual"), "newExpense.category.txt.loc");
		Actions act=new Actions(new WebDriverTestBase().getDriver());
		act.sendKeys(Keys.ENTER).perform();
		
		click("newExpense.amount.txt.loc");
		sendKeys(map.get("amountActual"), "newExpense.amount.txt.loc");
	
	}
	
	@QAFTestStep(description = "I click on expense submit button")
	public void expenseSubmitButtonClick() throws InterruptedException {
		// step implementation
		QAFExtendedWebElement btn=new QAFExtendedWebElement("newExpense.submit.btn.loc");
		JavascriptExecutor js= (JavascriptExecutor)new WebDriverTestBase().getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", btn);
		QAFTestBase.pause(1000);
		click("newExpense.submit.btn.loc");
	}	
	
	
	@QAFTestStep(description = "there should be a success message on successful submission of request")
	public void verifyExpenseSuccessfulSubmission() throws InterruptedException {
		// step implementation
		CommonStep.verifyPresent("newExpense.successfulSubmission.msg.loc");
		QAFTestBase.pause(5000);
	}	
	
	@QAFTestStep(description = "there should be an error message on the form with focus")
	public void verifyExpenseErrorMessage() throws InterruptedException {
		// step implementation
		System.out.println("title is " +map.get("titleActual"));
		if(map.get("titleActual").equals(""))
			verifyPresent("newExpense.titleError.msg.loc");
		
		String expensedate=map.get("dateActual");
		try
		{
	        new SimpleDateFormat("dd-MMM-yyyy").parse(expensedate);
			System.out.println("valid date format");
		}
		catch(Exception e)
		{
	    	 verifyPresent("newExpense.errorDate.msg.loc");
		}
		
		if(map.get("projectActual").equals("") || !map.get("projectActual").equals("Other"))
			verifyPresent("newExpense.errorProject.msg.loc");
		
		else
			if (map.get("otherProjectActual").equals(""))
				verifyPresent("newExpense.errorProjectOther.msg.loc");
		
		if(map.get("categoryActual").equals("") || !categoryOptionsList.contains(map.get("categoryActual")))
				verifyPresent("newExpense.errorCategory.msg.loc");		

		if(map.get("amountActual").equals(""))
			verifyPresent("newExpense.errorAmount.msg.loc");			
		
	}
		
}
