package com.nest.steps;

import static com.qmetry.qaf.automation.step.CommonStep.clear;
import static com.qmetry.qaf.automation.step.CommonStep.click;
import static com.qmetry.qaf.automation.step.CommonStep.sendKeys;
import static com.qmetry.qaf.automation.step.CommonStep.verifyPresent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;

public class NestVerifyExpenseFunctionalityWithAttachmentSteps {
	/**
	 * @param searchTerm
	 *            : search term to be searched
	 * @throws InterruptedException 
	 */
	@QAFTestStep(description = "user clicks on Browse button")
	public void expenseBrowseButtonClick() throws InterruptedException {
		// step implementation
		click("newExpense.attach.btn.loc");
		Thread.sleep(5000);
	}
	
	@QAFTestStep(description = "selects file user clicks on Open button and drops a file from folder to Drop a File to attach area")
	public void expenseFileUpload() throws InterruptedException, IOException {
		// step implementation
//		String fileName="C:\\Users\\kritika.dwivedi\\Downloads\\resume_template\\IS_Arpita_Ray_PM.docx";
		String autoITExecutable = "C:\\User\\kritika.dwivedi\\Documents\\AutoIT\\FileUpload.exe"; //+ fileName;
		Runtime.getRuntime().exec(autoITExecutable);
	}
	
}
