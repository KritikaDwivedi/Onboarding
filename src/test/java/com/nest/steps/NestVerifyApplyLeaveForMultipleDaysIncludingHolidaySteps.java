package com.nest.steps;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.hamcrest.Matchers;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Validator;

public class NestVerifyApplyLeaveForMultipleDaysIncludingHolidaySteps{

	//public static int workingDays = 0;

	@QAFTestStep(description = "I calculate the days between from and to date")
	public void calculateWorkingDays() throws InterruptedException {
		String startDate=(String)ConfigurationManager.getBundle().getProperty("leaveDateTo");
		String endDate=(String)ConfigurationManager.getBundle().getProperty("leaveDateTo");
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
	    try
	    {
	      Calendar start = Calendar.getInstance();
	      start.setTime(sdf.parse(startDate));

	      Calendar end = Calendar.getInstance();
	      end.setTime(sdf.parse(endDate));
	      int workingDays=0;
	      while(!start.after(end))//removed ; (semi-colon)
	      {
	        int day = start.get(Calendar.DAY_OF_WEEK);
	        if ((day != Calendar.SATURDAY) && (day != Calendar.SUNDAY))
	        {	
	        	ConfigurationManager.getBundle().setProperty("workingDays",workingDays++);
	        }
	        start.add(Calendar.DATE, 1);
	      }
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
		Validator.verifyThat(totalLeaves, Matchers.containsString(Integer.toString((int) ConfigurationManager.getBundle().getProperty("workingDays"))));
	}
	
}
