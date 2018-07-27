package com.nest.steps;

import static com.qmetry.qaf.automation.step.CommonStep.get;
import static com.qmetry.qaf.automation.step.CommonStep.sendKeys;
import static com.qmetry.qaf.automation.step.CommonStep.waitForVisible;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;

public class NestValidLoginStep {
	/**
	 * @param searchTerm
	 *            : search term to be searched
	 */
	@QAFTestStep(description = "user launches the application")
	public static void launchSite() {
		get("/");
		new WebDriverTestBase().getDriver().manage().window().maximize();
		waitForVisible("login.username.txt.loc",5000);
	}
	
	@QAFTestStep(description = "user enters user name as {0} and password as {1}")
	public void loginCredentials(String username, String password) {
		// step implementation
		sendKeys(username, "login.username.txt.loc");
		sendKeys(password, "login.password.txt.loc");
	}
	
	@QAFTestStep(description = "clicks on the Login button")
	public void loginButtonClick() {
		// step implementation
//		click("login.loginbtn.btn.loc");
		Actions act=new Actions(new WebDriverTestBase().getDriver());
		act.sendKeys(Keys.ENTER).perform();
	}
		
	@QAFTestStep(description = "user should land on the Home page")
	public void verifyNestHomePage() throws InterruptedException {
		// step implementation
		Thread.sleep(10000);
		CommonStep.verifyPresent("home.logout.btn.loc");
	}
}
