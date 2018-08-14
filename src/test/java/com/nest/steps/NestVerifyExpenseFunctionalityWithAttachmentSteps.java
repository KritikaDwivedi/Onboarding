package com.nest.steps;

import java.io.IOException;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.Validator;

public class NestVerifyExpenseFunctionalityWithAttachmentSteps {

	@QAFTestStep(description = "I upload the file {0}")
	public void expenseFileUpload(String filepath) throws InterruptedException, IOException {
		// step implementation
			CommonStep.waitForVisible("newExpense.attach.btn.loc");
			String file[]=filepath.split("//");
			int len=file.length;
			ConfigurationManager.getBundle().setProperty("fileName",file[len-1]);
			QAFExtendedWebElement upload= new QAFExtendedWebElement("newExpense.uploadWindow.window.loc");
			upload.sendKeys(filepath);
	}
	
	@QAFTestStep(description = "attachment should be successfully attached for valid size and format")
	public void verifySuccessfulUpload() {
		// step implementation
		QAFExtendedWebElement attachment= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("newExpense.attachment.lnk.loc"),(String)ConfigurationManager.getBundle().getProperty("fileName")));
		Validator.verifyTrue(attachment.isDisplayed(), "Attachment is not successfully attached", "Attachment is successfully attached");
	}

}
