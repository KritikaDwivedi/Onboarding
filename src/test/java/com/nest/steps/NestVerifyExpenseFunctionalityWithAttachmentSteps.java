package com.nest.steps;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;

public class NestVerifyExpenseFunctionalityWithAttachmentSteps {
	/**
	 * @param searchTerm
	 *            : search term to be searched
	 * @throws InterruptedException 
	 */
	public static String fileName="";
	
	@QAFTestStep(description = "user clicks on browse button")
	public void expenseBrowseButtonClick() throws InterruptedException {
		// step implementation
		Thread.sleep(5000);
		CommonStep.click("newExpense.attach.btn.loc");
		Thread.sleep(15000);
	}

	@QAFTestStep(description = "uploads the file {0}")
	public void expenseFileUpload(String filepath) throws InterruptedException, IOException {
		// step implementation
		try {
			System.out.println("file path is"+filepath);
			String file[]=filepath.split("\\\\");
			int len=file.length;
			fileName=file[len-1];
			System.out.println("filename is "+fileName);
			String filepath2=filepath.replace("\\\\", "\\");
			StringSelection stringSelection = new StringSelection(filepath2);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
			//native key strokes for CTRL, V and ENTER keys
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(15000);
		}
		catch (Exception exp) {
			exp.printStackTrace();
		}
	}
	
	@QAFTestStep(description = "attachment should be successfully attached for valid size and format")
	public void verifySuccessfulUpload() {
		// step implementation
		QAFExtendedWebElement attachment= new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("newExpense.attachment.lnk.loc"),fileName));
		attachment.isDisplayed();
	}

}
