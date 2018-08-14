package com.nest.steps;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;

import com.nest.utilities.CommonUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.Validator;

public class VerifyBrightSparkRejectFunctionalitySteps {
	
	@QAFTestStep(description = "there should be two rows containing {0} and {1}")
	public static void verifyRnRFirstTwoRows(String row1, String row2) {
		List<String> row1List = new ArrayList<String>(Arrays.asList(row1.split(",")));
		List<String> row2List = new ArrayList<String>(Arrays.asList(row2.split(",")));
		
		for(int i=0;i<row1List.size();i++) {
		QAFExtendedWebElement block= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("rnrRequest.firstRow.info.loc"),row1List.get(i)));
		block.isDisplayed();		}
		
		for(int i=0;i<row2List.size();i++) {
			QAFExtendedWebElement block= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("rnrRequest.secondRow.info.loc"),row2List.get(i)));
			block.isDisplayed();		}
		}
	
	@QAFTestStep(description = "manager status should be {0}")
	public static void verifyManagerStatus(String managerStatus){
		QAFExtendedWebElement status= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("rnrRequest.secondRow.info.loc"),"Manager Status"));
		String statusActual=status.getText();
		Validator.verifyThat(statusActual, Matchers.containsString(managerStatus));
		}
	

	@QAFTestStep(description = "there should be value addition and review comment block containing {0} and {1}")
	public static void verifyValueAdditionsAndReviewCommentBlock(String valueAdditions, String reviewComment) throws AWTException, InterruptedException {
		List<String> valueAdditionsList = new ArrayList<String>(Arrays.asList(valueAdditions.split(",")));
		for(int i=0;i<valueAdditionsList.size();i++) {
			QAFExtendedWebElement valueAdditionsFields= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("rnrRequest.valueAdditionsBlock.info.loc"),valueAdditionsList.get(i)));
			CommonUtils.scrollDownToView();
			valueAdditionsFields.isDisplayed();		}
		QAFExtendedWebElement reviewCommentsField= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("rnrRequest.ReviewCommentsBlock.info.loc"),reviewComment));
		CommonUtils.scrollDownToView();
		reviewCommentsField.isDisplayed();
		}
	
	@QAFTestStep(description = "reward card image should be displayed in reward block")
	public static void verifyRewardCard() throws AWTException, InterruptedException {
		CommonUtils.scrollDownToView();
		CommonStep.verifyPresent("rnrRequest.cardBlock.info.loc");
		}
	
	@QAFTestStep(description = "back button should be there")
	public static void verifyBackButton() throws AWTException, InterruptedException {
		CommonUtils.scrollDownToView();
		CommonStep.verifyPresent("rnrRequest.cardBlock.info.loc");
		}
	
	
}
