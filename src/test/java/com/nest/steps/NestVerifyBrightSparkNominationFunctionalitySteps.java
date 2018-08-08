package com.nest.steps;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.nest.utilities.CommonUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Validator;

public class NestVerifyBrightSparkNominationFunctionalitySteps {
	
	public static String nominee="";
	@QAFTestStep(description = "nominate some one say {0} as Bright Spark")
	public static void brightSparkNominate(String nomineeBrightSpark) throws InterruptedException, AWTException {
		//select card type
		CommonStep.click("brightSpark.cardType.btn.loc");
		
		//select nominee
		CommonStep.click("brightSpark.nominee.dropdown.loc");
		CommonStep.sendKeys(nomineeBrightSpark, "brightSpark.nomineeFilter.txt.loc");
		Actions act=new Actions(CommonUtils.driver);
		act.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);
		nominee=(CommonStep.getText("brightSpark.nominee.dropdown.loc")).trim();
		
		//select project
		CommonStep.click("brightSpark.project.dropdown.loc");
		act.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		
		//challenges faced
		Object challenges=ConfigurationManager.getBundle().getProperty("brightSpark.challanges");
		CommonStep.sendKeys((String)challenges, "brightSpark.facedSituations.txt.loc");
		
		//solutions provided
		Object solutions=ConfigurationManager.getBundle().getProperty("brightSpark.solutions");
		CommonStep.sendKeys((String)solutions, "brightSpark.solutionsProvided.txt.loc");
		
		//benifits accrued
		Object benifits=ConfigurationManager.getBundle().getProperty("brightSpark.benifits");
		CommonStep.sendKeys((String)benifits, "brightSpark.benifitsAccrued.txt.loc");
		
		//rationale for nomination
		Object rationale=ConfigurationManager.getBundle().getProperty("brightSpark.rationale");
		CommonStep.sendKeys((String)rationale, "brightSpark.rationale.txt.loc");
		
		//Post the nomination
		CommonUtils.scrollToViewString("brightSpark.post.btn.loc");
		CommonStep.click("brightSpark.post.btn.loc");
		
	}
	
	@QAFTestStep(description = "breadcrumb should be Home/R & R/My R & R")
	public static void myNominationBreadcrumb()  {
		//select card type
		CommonUtils.waitForPageToLoad(10000);
		CommonStep.verifyPresent("myNominations.breadcrumb.lnk.loc");
	}
	
	
	@QAFTestStep(description = "there should be five column as follows {0} , {1} , {2} , {3} , {4}")
	public static void myNominationColumns(String nominee,String noOfReward,String postedDate,String managerStatus,String hrStatus)  {
		//select card type
		String columns[]= {nominee, noOfReward, postedDate, managerStatus, hrStatus};
		for(int i=0;i<5;i++)
		{
			QAFExtendedWebElement column= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("myNominations.column.txt.loc"),columns[i]));
			Validator.verifyTrue(column.isDisplayed(), columns[i]+"is not displayed", columns[i]+"is displayed");
		}	
	}
	
	@QAFTestStep(description = "expand icon should be there before Nominee name if user is nominated for more than one rewards")
	public static void myNominationExpand() throws InterruptedException, AWTException  {
		//select card type
		QAFExtendedWebElement reward= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("myNominations.rewardCount.data.loc"),nominee));
		String rewardCount=reward.getText();
		if (Integer.parseInt(rewardCount)>1)
		{	QAFExtendedWebElement expand= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("myNominations.expandNominee.btn.loc"),nominee));
			expand.verifyPresent();
		}
	}
	
	@QAFTestStep(description = "if status is approved then it should come in green color, yellow for pending and red for rejected")
	public static void myNominationStatusColor() {
		//select card type
		QAFExtendedWebElement expand= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("myNominations.expandNominee.btn.loc"),nominee));
		expand.click();
		
		//verify approved text color
		QAFExtendedWebElement approved= new QAFExtendedWebElement("myNominations.approved.data.loc");
		List<QAFWebElement> approvedList=approved.findElements("myNominations.approved.data.loc");
		if(approvedList.size()>0)
		{
		String color=approvedList.get(0).getCssValue("color");
		Validator.verifyTrue(color.equals("rgba(86, 176, 94, 1)"), "Approved text is not green in color", "Approved text is green in color");
		}
		
		//verify pending text color
		QAFExtendedWebElement pending= new QAFExtendedWebElement("myNominations.pending.data.loc");
		List<QAFWebElement> pendingList=pending.findElements("myNominations.pending.data.loc");
		if(pendingList.size()>0)
		{
		String color=pendingList.get(0).getCssValue("color");
		Validator.verifyTrue(color.equals("rgba(239, 187, 64, 1)"), "Pending text is not yellow in color", "Pending text is yellow in color");
		}
		
		//verify rejected text color
		QAFExtendedWebElement rejected= new QAFExtendedWebElement("myNominations.rejected.data.loc");
		List<QAFWebElement> rejectedList=rejected.findElements("myNominations.rejected.data.loc");
		if(rejectedList.size()>0)
		{
		String color=rejectedList.get(0).getCssValue("color");
		Validator.verifyTrue(color.equals("rgba(234, 108, 108, 1)"), "Rejected text is not red in color", "Rejected text is red in color");
		}		
	}
	
	@QAFTestStep(description = "pagination should be present at lower right corner")
	public static void myNominationPagination() throws AWTException, InterruptedException {
		//select card type
		CommonUtils.scrollToViewString("myNominations.pagination.data.loc");
		CommonStep.verifyPresent("myNominations.pagination.data.loc");
	}
}
