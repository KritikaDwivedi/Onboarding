package com.nest.steps;

import java.awt.AWTException;

import org.hamcrest.Matchers;

import com.nest.utilities.CommonUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.Validator;

public class NestVerifyUINewPostPageSteps {
	
	@QAFTestStep(description = "I click on {0} button")
	public static void clickButton(String button) throws InterruptedException, AWTException{
		// step implementation
		CommonUtils.buttonClick(button);
	}
	
	@QAFTestStep(description = "breadcrumb should be {0}")
	public static void verifyNavigationOfCreatePostPage(String title){
		// step implementation
		String navigationText1=CommonStep.getText("newPost.navigation1.lnk.loc");
		String navigationText2=CommonStep.getText("newPost.navigation2.lnk.loc");
		String navigationText3=CommonStep.getText("newPost.navigation3.lnk.loc");
		Validator.verifyThat(navigationText1+navigationText2+navigationText3, Matchers.containsString(title));
	}
	
	@QAFTestStep(description = "text fields should be present title, post url, image url, description")
	public static void verifyNewPostTextfield(){
		// step implementation
		CommonStep.verifyPresent("newPost.title.txt.loc");
		CommonStep.verifyPresent("newPost.postURL.txt.loc");
		CommonStep.verifyPresent("newPost.imageURL.txt.loc");
		CommonStep.verifyPresent("newPost.description.txt.loc");
	}
	
	@QAFTestStep(description = "two dropdowns should be present category and location")
	public static void verifyNewPostDropdown(){
		// step implementation
		CommonStep.verifyPresent("newPost.category.dropdown.loc");
		CommonStep.verifyPresent("newPost.location.dropdown.loc");
	}
	
	@QAFTestStep(description = "two buttons should be present submit and back")
	public static void verifyNewPostSubmitAndBackButtons(){
		// step implementation
		CommonStep.verifyPresent("newPost.submit.btn.loc");
		CommonStep.verifyPresent("newPost.back.btn.loc");
	}
	
	@QAFTestStep(description = "{0} , {1} , {2} , {3} , {4} should be compulsory fields")
	public static void verifyNewPostMandatoryFields(String title, String postURL, String category, String location, String description){
		// step implementation
		String column[]= {title, postURL, category, location, description};
		for(int i=0; i<4; i++)
		{
		QAFExtendedWebElement columnName= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("newPost.mandatory.label.loc"),column[i]));
		columnName.isDisplayed();
		}
	}
}