package com.nest.steps;

import static com.qmetry.qaf.automation.step.CommonStep.get;
import static com.qmetry.qaf.automation.step.CommonStep.sendKeys;
import static com.qmetry.qaf.automation.step.CommonStep.waitForVisible;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.nest.utilities.CommonUtils;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.util.Validator;

public class NestValidLoginStep {
	/**
	 * @param searchTerm
	 *            : search term to be searched
	 */
	public static String user="";
	@QAFTestStep(description = "user launches the application")
	public static void launchSite() {
		get("/");
		new WebDriverTestBase().getDriver().manage().window().maximize();
		waitForVisible("login.username.txt.loc",5000);
	}
	
	@QAFTestStep(description = "user enters user name as {0} and password as {1}")
	public void loginCredentials(String username, String password) {
		// step implementation
		user=username;
		sendKeys(username, "login.username.txt.loc");
		sendKeys(password, "login.password.txt.loc");
	}
	
	@QAFTestStep(description = "clicks on the login button")
	public void loginButtonClick() {
		// step implementation
//		CommonStep.click("login.loginbtn.btn.loc");
		Actions act=new Actions(CommonUtils.driver);
		act.sendKeys(Keys.ENTER).perform();
	}
		
	@QAFTestStep(description = "user should land on the home page")
	public void verifyNestHomePage() throws InterruptedException {
		// step implementation
		CommonUtils.waitForPageToLoad(30000);
		CommonStep.verifyPresent("home.logout.btn.loc");
		
		String username[]=user.split("\\.");
		String userActual=(CommonStep.getText("home.user.info.loc")).toLowerCase();
		Validator.verifyTrue(userActual.contains(username[0]), "Not navigated to Home pge", "Successfully navigated to home page");
	}
}
