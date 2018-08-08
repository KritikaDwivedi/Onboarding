package com.nest.steps;

import static com.qmetry.qaf.automation.step.CommonStep.verifyPresent;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;

public class NestVerifyTeamLeaveListPageStep {
	@QAFTestStep(description = "following columns should be present {0} , {1} , {2} , {3} , {4} , {5} , {6} , {7} , {8} , {9} , {10}")
	public void verifyTeamLeaveListTableColumns(String EmployeeNameID, String AppliedDate, String Type, String LeaveDuration, String LeaveDate, String Status, String LeaveReason, String Project, String Comment, String Actions, String BackDatedLeave){
		// step implementation
		String column[]= {EmployeeNameID, AppliedDate, Type, LeaveDuration, LeaveDate, Status, LeaveReason, Project, Comment, Actions, BackDatedLeave};
		for(int i=0; i<11; i++)
		{
			QAFExtendedWebElement columnName= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("teamLeaveList.columns.tbl.loc"),column[i]));
			columnName.isDisplayed();
		}
	}
	
	@QAFTestStep(description = "there should be submit and approve all button on the team leave list page")
	public void verifyTeamLeaveListButtons(){
		// step implementation
		verifyPresent("teamLeaveList.submit.btn.loc");
		verifyPresent("teamLeaveList.approveAll.btn.loc");
		}
}
