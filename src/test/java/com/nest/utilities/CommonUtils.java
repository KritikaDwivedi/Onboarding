package com.nest.utilities;

import static com.qmetry.qaf.automation.step.CommonStep.click;

import java.awt.AWTException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Validator;

public class CommonUtils {
	
	//public static WebDriver driver=new WebDriverTestBase().getDriver();

	//Comment: Method to click any button
	public static void buttonClick(String buttonName) throws InterruptedException {
		// step implementation
		QAFExtendedWebElement button= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("utility.button.btn.loc"),buttonName));
		Actions act=new Actions(new WebDriverTestBase().getDriver());
		try 
		{
			act.moveToElement(button).click().build().perform();
		}
		catch(Exception e)
		{
			act.sendKeys(Keys.PAGE_DOWN).build().perform();
			button.click();
		}
	}

	//Comment: Method to wait for page to load completely
	public static void waitForPageToLoad(long time) {
		
		CommonStep.waitForNotVisible("utility.loader.img.loc",time);
	}

	//Comment:Scrolling to locator
	public static void scrollDownToView()
	{	
		Actions act=new Actions(new WebDriverTestBase().getDriver());
		act.sendKeys(Keys.PAGE_DOWN).perform();
	}

	//Comment:Scrolling to QAFExtendedWebElement
	public static void scrollUpToView() throws AWTException, InterruptedException
	{	Actions act=new Actions(new WebDriverTestBase().getDriver());
		act.sendKeys(Keys.PAGE_UP).perform();
	}

	//Comment: Method to navigate from menu to sub menu
	public static void navigationToMenuAndSubMenu(String view, String menuOption, String subMenuOption) throws InterruptedException {
		// step implementation
		CommonStep.waitForVisible("home.navigation.pane.loc", 10000);
		click("home.navigation.pane.loc");
		CommonStep.waitForVisible("utility.navigation.pane.loc");
		QAFExtendedWebElement menu= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("home.menu.options.loc"),view,menuOption));
		QAFExtendedWebElement submenu= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("home.submenu.options.loc"),view,subMenuOption));
		Actions act=new Actions(new WebDriverTestBase().getDriver());
		try
		{
			menu.click();
		}
		catch(Exception e)
		{
			act.sendKeys(Keys.PAGE_DOWN).moveToElement(menu).build().perform();
			menu.waitForVisible(5000);
			menu.click();
		}
		try
		{	submenu.waitForVisible(5000);
			submenu.click();
		}
		catch(Exception e)		
		{	
			try {
			act.sendKeys(Keys.PAGE_DOWN).moveToElement(submenu).build().perform();
			submenu.waitForVisible(5000);
			submenu.click();
			}
			catch(Exception e1)
			{
				act.sendKeys(Keys.PAGE_DOWN).moveToElement(submenu).build().perform();
				submenu.waitForVisible(5000);
				submenu.click();
			}
			
		}
	}

	//Comment: Method to navigate from sub menu to sub menu 2
	public static void navigationToSubMenuAndSubMenu2(String view, String subMenuOption2) throws InterruptedException, AWTException {
		// step implementation
		QAFExtendedWebElement subMenu2= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("home.submenu2.options.loc"),view,subMenuOption2));
		subMenu2.waitForVisible(5000);
		subMenu2.click();
	}

	//Comment: Method to verify page title
	public static void verifyTitleOfPage(String title) throws AWTException, InterruptedException{
		// step implementation
		QAFExtendedWebElement header;
		try
		{
			header= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("utility.headingContains.header.loc"),title));
			scrollUpToView();
		}

		catch(Exception e)
		{
			header= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("utility.heading.header.loc"),title));
			scrollUpToView();
		}
		Validator.verifyTrue(header.isDisplayed(), "Title of page is incorrect", "Title of page is correct");
	}

	//Comment: Method for selecting date from date field
	public static void datePicker(String date) throws InterruptedException, ParseException{
		// step implementation

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");  
	    Date date1 = new Date();  
		String currentDate=formatter.format(date1);
		String dateTime=date.replace("CurrentDate", currentDate);
		
		//Split the date time to get only the date part
		String date_dd_MM_yyyy[] = (dateTime.split(" ")[0]).split("-");

		//get the year difference between current year and year to set in calander
		int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2])- Calendar.getInstance().get(Calendar.YEAR);
		CommonStep.click("addTravelRequest.dateMidLink.lnk.loc");

		if(yearDiff!=0){
			//if you have to move next year
			if(yearDiff>0){
				for(int i=0;i< yearDiff;i++){
					System.out.println("Year Diff->"+i);
					CommonStep.click("addTravelRequest.dateNext.lnk.loc");
				}
			}

			//if you have to move previous year
			else if(yearDiff<0){
				for(int i=0;i< (yearDiff*(-1));i++){
					System.out.println("Year Diff->"+i);
					CommonStep.click("addTravelRequest.datePrevious.lnk.loc");
				}
			}
		}
		String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		int month=0;
		for(int i=0;i<12;i++)
		{
			if(date_dd_MM_yyyy[1].equals(monthNames[i]))
				month=i;
		}
		//Get all months from calendar to select correct one
		QAFExtendedWebElement months= new QAFExtendedWebElement("addTravelRequest.months.lnk.loc");
		List<QAFWebElement> list_AllMonthToBook = months.findElements("addTravelRequest.months.lnk.loc");
		list_AllMonthToBook.get(month-1).click();
		CommonStep.waitForVisible("addTravelRequest.date.calendar.loc");

		//get all dates from calendar to select correct one
		QAFExtendedWebElement dates= new QAFExtendedWebElement("addTravelRequest.date.calendar.loc");
		List<QAFWebElement> list_AllDateToBook = dates.findElements("addTravelRequest.date.calendar.loc");
		list_AllDateToBook.get(Integer.parseInt(date_dd_MM_yyyy[0])-1).click();

		///FOR TIME
		String time[]=(dateTime.split(" ")[1]).split(":");
		if(dateTime.split(" ")[2].equals("AM"))
		{	if(Integer.parseInt(time[0])!=12)
			{
			for(int i=0;i<Integer.parseInt(time[0]);i++)
				CommonStep.click("addTravelRequest.hrsIncrement.lnk.loc");
			}	
		}
		else
		{	
			int hrdiff=12-Integer.parseInt(time[0]);
			if(hrdiff==0)
				hrdiff=12;
			for(int i=0;i<hrdiff;i++)
				CommonStep.click("addTravelRequest.hrsDecrement.lnk.loc");

			if(Integer.parseInt(time[1])!=00)
			{
				for(int i=0;i<Integer.parseInt(time[1]);i++)
					CommonStep.click("addTravelRequest.minIncrement.lnk.loc");
			}		
		}
	}
}
