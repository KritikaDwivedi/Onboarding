package com.nest.steps;

import java.awt.AWTException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	@QAFTestStep(description = "following fields should be available {0}")
	public static void fieldsPresent(String fields)	
		{
		String locator[]= {"addTravelRequest.purposeOfTravel.txt.loc", "addTravelRequest.type.dropdown.loc","addTravelRequest.travelType.label.loc","addTravelRequest.journeyFrom.dropdown.loc","addTravelRequest.journeyTo.dropdown.loc","addTravelRequest.journeyFromDate.txt.loc", "addTravelRequest.journeyToDate.txt.loc","addTravelRequest.clientOrProject.txt.loc","addTravelRequest.cabBookingRequired.label.loc","addTravelRequest.hotelBookingRequired.label.loc","addTravelRequest.needCash.lebel.loc","addTravelRequest.moreInfo.txt.loc","addTravelRequest.idProof.btn.loc"};
		for(int i=0; i<13; i++)
		{
			CommonStep.verifyPresent(locator[i]);
		}
	}

	@QAFTestStep(description = "following fields should be mandatory {0} on {1} page")
	public static void mandatoryFieldsPresent(String mandatoryFields, String page) {
		List<String> mandatoryFieldsList = new ArrayList<String>(Arrays.asList(mandatoryFields.split(",")));
		for(int i=0 ; i< mandatoryFieldsList.size() ; i++)
		{	if(page.equals("Travel")) {
				QAFExtendedWebElement fieldName= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("addTravelRequest.mandatoryFields.label.loc"),mandatoryFieldsList.get(i)));
				fieldName.isDisplayed();
				}
			if(page.equals("New Post")) {
				QAFExtendedWebElement fieldName= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("newPost.mandatory.label.loc"),mandatoryFieldsList.get(i)));
				fieldName.isDisplayed();
				}
		}
	}

	@QAFTestStep(description = "I enter required data")
	public void addTravelEnterData() throws InterruptedException, ParseException {
		CommonStep.sendKeys((String)ConfigurationManager.getBundle().getProperty("travel.travel"),"addTravelRequest.purposeOfTravel.txt.loc");
		
		CommonStep.click("addTravelRequest.type.dropdown.loc");
		QAFExtendedWebElement fieldName= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("addTravelRequest.typeOptions.dropdown.loc"),(String)ConfigurationManager.getBundle().getProperty("travel.type")));
		fieldName.click();

		CommonStep.sendKeys((String)ConfigurationManager.getBundle().getProperty("travel.journeyFrom"),"addTravelRequest.journeyFrom.dropdown.loc");
		Actions act=new Actions(new WebDriverTestBase().getDriver());
		act.sendKeys(Keys.ENTER).perform();

		CommonStep.sendKeys((String)ConfigurationManager.getBundle().getProperty("travel.journeyTo"),"addTravelRequest.journeyTo.dropdown.loc");
		act.sendKeys(Keys.ENTER).perform();

		CommonStep.click("addTravelRequest.journeyFromDate.txt.loc");
		CommonUtils.datePicker((String)ConfigurationManager.getBundle().getProperty("travel.journeyStartDate"));
		act.sendKeys(Keys.ENTER).perform();

		CommonStep.click("addTravelRequest.journeyToDate.txt.loc");
		CommonUtils.datePicker((String)ConfigurationManager.getBundle().getProperty("travel.journeyEndDate"));
		act.sendKeys(Keys.ENTER).perform();

		CommonStep.click("addTravelRequest.clientOrProject.txt.loc");
		CommonStep.sendKeys((String)ConfigurationManager.getBundle().getProperty("travel.clientProjectName"),"addTravelRequest.clientOrProject.txt.loc");	
	}


	@QAFTestStep(description = "I click on submit button to submit travel request")
	public static void submitTravelRequest() {
		JavascriptExecutor js= (JavascriptExecutor)new WebDriverTestBase().getDriver();
		QAFExtendedWebElement submit= new QAFExtendedWebElement("addTravelRequest.submit.btn.loc");
		js.executeScript("arguments[0].scrollIntoView(true);", submit);
		submit.click();
	}

	@QAFTestStep(description = "travel request confirmation message should be dispalyed")
	public static void addTravelRequestConfirmation() {
		CommonStep.verifyPresent("addTravelRequest.confirmation.msg.loc");
		CommonStep.waitForVisible("addTravelRequest.confirmation.msg.loc",5000);
		CommonStep.waitForNotVisible("addTravelRequest.confirmation.msg.loc",5000);
	}
	
	@QAFTestStep(description = "I raise a travel request")
	public void raiseTravelRequest() throws InterruptedException, ParseException, AWTException{
		// step implementation
		CommonUtils.waitForPageToLoad(10000);
		NestVerifyUINewPostPageSteps.clickButton("New Travel Request");
		addTravelEnterData();
		submitTravelRequest();
		addTravelRequestConfirmation();
		}
}
