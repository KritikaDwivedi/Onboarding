package com.nest.steps;

import static com.qmetry.qaf.automation.step.CommonStep.get;
import static com.qmetry.qaf.automation.step.CommonStep.*;
import static com.qmetry.qaf.automation.step.CommonStep.waitForVisible;

import java.awt.AWTException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.nest.utilities.CommonUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.util.Validator;

public class NestValidLoginStep {
	/**
	 * @param searchTerm
	 *            : search term to be searched
	 */
	//public static String user="";
	@QAFTestStep(description = "I launch the application")
	public static void launchSite() {
		get("/");
		new WebDriverTestBase().getDriver().manage().window().maximize();
		waitForVisible("login.username.txt.loc",5000);
	}
	
	@QAFTestStep(description = "I enter user name as {0} and password as {1}")
	public static void loginCredentials(String username, String password) {
		// step implementation
		ConfigurationManager.getBundle().setProperty("user",username);
		//user=username;
		sendKeys(username, "login.username.txt.loc");
		sendKeys(password, "login.password.txt.loc");
		click("login.username.txt.loc");
	}
	
	@QAFTestStep(description = "I click on the login button")
	public static void loginButtonClick() {
		// step implementation
		CommonUtils.scrollDownToView();
		CommonStep.click("login.loginbtn.btn.loc");
//		Actions act=new Actions(CommonUtils.driver);
//		act.sendKeys(Keys.ENTER).perform();
	}
		
	@QAFTestStep(description = "I should land on the home page")
	public static void verifyNestHomePage() throws InterruptedException {
		// step implementation
		CommonUtils.waitForPageToLoad(50000);
		CommonStep.verifyPresent("home.logout.btn.loc");
		
		String username[]=((String) ConfigurationManager.getBundle().getProperty("user")).split("\\.");
		String userActual=(CommonStep.getText("home.user.info.loc")).toLowerCase();
		Validator.verifyTrue(userActual.contains(username[0]), "Not navigated to Home pge", "Successfully navigated to home page");
	}
	
	@QAFTestStep(description = "I login with {0} and {1}")
	public static void nestLogin(String userName, String password) throws InterruptedException {
		loginCredentials(userName,password);
		loginButtonClick();
		verifyNestHomePage();
		
	}
	
}
