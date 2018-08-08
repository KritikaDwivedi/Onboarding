package com.nest.steps;

import java.text.ParseException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import com.nest.utilities.CommonUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;

public class NestAddTravelRequestSteps {

	@QAFTestStep(description = "following fields should be available on travel request page {0} , {1} , {2} , {3} , {4} , {5} , {6} , {7} , {8} , {9} , {10} , {11} , {12}")
	public static void addTravelFields(String purposeOfTravel, String type ,String travelType,String journeyFrom,String journeyTo,String journeyStartDate,String journeyEndDate,String clientName,String cabBooking,String hotelBooking,String needCash,String moreInfo,String idProof) {
		//String fields[]= {purposeOfTravel,  type , travelType, journeyFrom, journeyTo, journeyStartDate, journeyEndDate, clientName, cabBooking, hotelBooking, needCash, idProof, moreInfo};
		String locator[]= {"addTravelRequest.purposeOfTravel.txt.loc", "addTravelRequest.type.dropdown.loc","addTravelRequest.travelType.label.loc","addTravelRequest.journeyFrom.dropdown.loc","addTravelRequest.journeyTo.dropdown.loc","addTravelRequest.journeyFromDate.txt.loc", "addTravelRequest.journeyToDate.txt.loc","addTravelRequest.clientOrProject.txt.loc","addTravelRequest.cabBookingRequired.label.loc","addTravelRequest.hotelBookingRequired.label.loc","addTravelRequest.needCash.lebel.loc","addTravelRequest.moreInfo.txt.loc","addTravelRequest.idProof.btn.loc"};
		for(int i=0; i<13; i++)
		{
			CommonStep.verifyPresent(locator[i]);
		}
	}

	@QAFTestStep(description = "following fields should be mandatory fields {0} , {1} , {2} , {3} , {4} , {5} , {6} should be mandatory fields")
	public static void addTravelMandatoryFields(String purposeOfTravel, String type ,String journeyFrom,String journeyTo,String journeyStartDate,String journeyEndDate,String clientName) {
		String mandatoryfields[]= {purposeOfTravel,  type , journeyFrom, journeyTo, journeyStartDate, journeyEndDate, clientName};
		for(int i=0; i<7; i++)
		{
			QAFExtendedWebElement fieldName= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("addTravelRequest.mandatoryFields.label.loc"),mandatoryfields[i]));
			fieldName.isDisplayed();
		}
	}

	@QAFTestStep(description = "user enters required data for purpose of travel as {0} , type as {1} , journey from as {2} , journey to as {3} , journey start date as {4} , journey end date as {5} , client/project name as {6}")
	public void addTravelEnterData(String purposeOfTravel, String type ,String journeyFrom,String journeyTo,String journeyStartDate,String journeyEndDate,String clientName) throws InterruptedException, ParseException {
		CommonStep.sendKeys(purposeOfTravel,"addTravelRequest.purposeOfTravel.txt.loc");

		CommonStep.click("addTravelRequest.type.dropdown.loc");
		QAFExtendedWebElement fieldName= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("addTravelRequest.typeOptions.dropdown.loc"),type));
		fieldName.click();

		CommonStep.sendKeys(journeyFrom,"addTravelRequest.journeyFrom.dropdown.loc");
		Actions act=new Actions(new WebDriverTestBase().getDriver());
		act.sendKeys(Keys.ENTER).perform();

		CommonStep.sendKeys(journeyFrom,"addTravelRequest.journeyTo.dropdown.loc");
		act.sendKeys(Keys.ENTER).perform();

		CommonStep.click("addTravelRequest.journeyFromDate.txt.loc");
		CommonUtils.datePicker(journeyStartDate);
		act.sendKeys(Keys.ENTER).perform();

		CommonStep.click("addTravelRequest.journeyToDate.txt.loc");
		CommonUtils.datePicker(journeyEndDate);
		act.sendKeys(Keys.ENTER).perform();

		CommonStep.click("addTravelRequest.clientOrProject.txt.loc");
		CommonStep.sendKeys(clientName,"addTravelRequest.clientOrProject.txt.loc");	
	}


	@QAFTestStep(description = "user clicks on submit button to submit travel request")
	public static void submitTravelRequest() {
		JavascriptExecutor js= (JavascriptExecutor)new WebDriverTestBase().getDriver();
		QAFExtendedWebElement submit= new QAFExtendedWebElement("addTravelRequest.submit.btn.loc");
		js.executeScript("arguments[0].scrollIntoView(true);", submit);
		submit.click();
	}

	@QAFTestStep(description = "travel request confirmation message should be displayed")
	public static void addTravelRequestConfirmation() {
		CommonStep.verifyPresent("addTravelRequest.confirmation.msg.loc");
		CommonStep.waitForVisible("addTravelRequest.confirmation.msg.loc",5000);
		CommonStep.waitForNotVisible("addTravelRequest.confirmation.msg.loc",5000);
	}
}
