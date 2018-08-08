package com.nest.steps;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.hamcrest.Matchers;

import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Validator;

public class NestVerifyApplyLeaveForMultipleDaysIncludingHolidaySteps{
	
//	VerifyHeaderOfPageUtility head=new VerifyHeaderOfPageUtility();
//	NestVerifyApplyLeaveForSingleDaySteps leaveDays=new NestVerifyApplyLeaveForSingleDaySteps();
	public static int workingDays = 0;
	
	/**
	 * @param searchTerm
	 *            : search term to be searched
	 * @throws InterruptedException 
	 */
	
	@QAFTestStep(description = "calculate the days between from and to date")
	public void calculateWorkingDays() throws InterruptedException {
		String startDate=NestVerifyApplyLeaveForSingleDaySteps.leaveDateFrom;
		String endDate=NestVerifyApplyLeaveForSingleDaySteps.leaveDateTo;
//		Thread.sleep(5000);
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
	    try
	    {
	      //Date start = sdf.parse(startdate);
	      Calendar start = Calendar.getInstance();
	      start.setTime(sdf.parse(startDate));
	      //Date end = sdf.parse(enddate);
	      Calendar end = Calendar.getInstance();
	      end.setTime(sdf.parse(endDate));
	      System.out.println(end);
	      while(!start.after(end))//removed ; (semi-colon)
	      {
	        //int day = start.getDay();
	        int day = start.get(Calendar.DAY_OF_WEEK);
	        //if ((day != Calendar.SATURDAY) || (day != Calendar.SUNDAY)) if it's sunday, != to Saturday is true
	        if ((day != Calendar.SATURDAY) && (day != Calendar.SUNDAY))
	        workingDays++;
	        //System.out.println(workingDays);//moved
	        start.add(Calendar.DATE, 1);//removed comment tags
	      }
	      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+workingDays);
	    }
	    catch(Exception e)
	    {
	      e.printStackTrace();
	    }
	}
	

	@QAFTestStep(description = "total leave count should not include the holidays on apply leave page")
	public void verifyLeaveCount(){
		// step implementation
		String totalLeaves=CommonStep.getText("applyLeave.totalLeave.txt.loc");
		Validator.verifyThat(totalLeaves, Matchers.containsString(Integer.toString(workingDays)));
	}
	
}
