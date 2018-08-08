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
	
	@QAFTestStep(description = "clicks on new expense button")
	public void expenseButtonClick() throws InterruptedException {
		// step implementation
		Thread.sleep(5000);
		click("newExpense.newExpense.btn.loc");
	}	
	@QAFTestStep(description = "fills the details for title as {0} , expense date as {1} , project as {2} if other then {3} , expense category as {4} , expense amount as {5}")
	public void expenseEnterDetails(String title, String expenseDate, String project, String other, String category, String amount) throws InterruptedException {
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
		QAFExtendedWebElement categoryOptions= new QAFExtendedWebElement("newExpense.categoryOptions.dropdown.loc");
		categoryOptionsList=categoryOptions.findElements("myNominations.approved.data.loc");
		sendKeys(category, "newExpense.category.txt.loc");
		Actions act=new Actions(new WebDriverTestBase().getDriver());
		act.sendKeys(Keys.ENTER).perform();
		
		click("newExpense.amount.txt.loc");
		sendKeys(amount, "newExpense.amount.txt.loc");
	
	}
	
	@QAFTestStep(description = "clicks on expense submit button")
	public void expenseSubmitButtonClick() throws InterruptedException {
		// step implementation
		QAFExtendedWebElement btn=new QAFExtendedWebElement("newExpense.submit.btn.loc");
		JavascriptExecutor js= (JavascriptExecutor)new WebDriverTestBase().getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", btn);
		Thread.sleep(10000);
		click("newExpense.submit.btn.loc");
	}	
	
	
	@QAFTestStep(description = "there should be a success message on successful submission of request")
	public void verifyExpenseSuccessfulSubmission() throws InterruptedException {
		// step implementation
		CommonStep.verifyPresent("newExpense.successfulSubmission.msg.loc");
		Thread.sleep(5000);
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
