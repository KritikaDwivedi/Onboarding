package com.nest.steps;

import static com.qmetry.qaf.automation.step.CommonStep.clear;
import static com.qmetry.qaf.automation.step.CommonStep.click;
import static com.qmetry.qaf.automation.step.CommonStep.sendKeys;
import static com.qmetry.qaf.automation.step.CommonStep.verifyPresent;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;

public class NestVerifyExpenseFunctionalityWithInvalidDetailsSteps {
	/**
	 * @param searchTerm
	 *            : search term to be searched
	 * @throws InterruptedException 
	 */
	public static String expenseDateActual="";
	public static HashMap<String,String> map=new HashMap<>();
	@QAFTestStep(description = "clicks on New Expense button")
	public void expenseButtonClick() throws InterruptedException {
		// step implementation
		Thread.sleep(15000);
		click("newExpense.newExpense.btn.loc");
	}	
	@QAFTestStep(description = "fills the invalid details for Title as {0} , Expense Date as {1} , Project as {2} if Other then {3} , Expense Category as {4} , Expense Amount as {5}")
	public void expenseEnterInvalidDetails(String title, String expenseDate, String project, String other, String category, String amount) throws InterruptedException {
		// step implementation
		Thread.sleep(2000);
		map.put("titleActual",title);
		map.put("dateActual",expenseDate);
		map.put("projectActual",project);
		map.put("otherProjectActual",other);
		map.put("categoryActual",category);
		map.put("amountActual",amount);
		
		click("newExpense.title.txt.loc");
		sendKeys(title, "newExpense.title.txt.loc");
		
		click("newExpense.expenseDate.txt.loc");
		clear("newExpense.expenseDate.txt.loc");
		sendKeys(expenseDate, "newExpense.expenseDate.txt.loc");
		
		click("newExpense.project.txt.loc");
		sendKeys(project, "newExpense.project.txt.loc");
		
		if (project.equals("Other"))
		{	Actions act=new Actions(new WebDriverTestBase().getDriver());
			act.sendKeys(Keys.ENTER).perform();
			click("newExpense.projectOther.txt.loc");
			sendKeys(other, "newExpense.projectOther.txt.loc");
		}
		
		click("newExpense.category.txt.loc");
		sendKeys(category, "newExpense.category.txt.loc");
		
		click("newExpense.amount.txt.loc");
		sendKeys(amount, "newExpense.amount.txt.loc");
		
		click("newExpense.submit.btn.loc");
		Thread.sleep(10000);
		
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
		
		if(map.get("categoryActual").equals(""))
			verifyPresent("newExpense.errorCategory.msg.loc");
		
		if(map.get("amountActual").equals(""))
			verifyPresent("newExpense.errorAmount.msg.loc");			
		
	}
		
}
