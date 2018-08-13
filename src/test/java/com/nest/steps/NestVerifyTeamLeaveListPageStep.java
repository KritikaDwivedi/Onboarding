package com.nest.steps;

import static com.qmetry.qaf.automation.step.CommonStep.verifyPresent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;

public class NestVerifyTeamLeaveListPageStep {
	@QAFTestStep(description = "following columns should be present {0} on {1} page")
	public void verifyTeamLeaveListTableColumns(String columns, String page){
		// step implementation
		List<String> columnsList = new ArrayList<String>(Arrays.asList(columns.split(",")));
		for(int i=0 ; i< columnsList.size() ; i++)
		{	if (page.equals("Bright Spark"))
			{
			QAFExtendedWebElement columnName= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("teamLeaveList.columns.tbl.loc"),columnsList.get(i)));
			columnName.isDisplayed();
			}
		
			if (page.equals("Leave"))
			{
			QAFExtendedWebElement columnName= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("teamLeaveList.columns.tbl.loc"),columnsList.get(i)));
			columnName.isDisplayed();
			}
		
		}
	}
	
	@QAFTestStep(description = "there should be submit and approve all button on the team leave list page")
	public void verifyTeamLeaveListButtons(){
		// step implementation
		verifyPresent("teamLeaveList.submit.btn.loc");
		verifyPresent("teamLeaveList.approveAll.btn.loc");
		}
	
}
