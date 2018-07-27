package com.nest.pages;

import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;

public class nestLogin extends WebDriverBaseTestPage<WebDriverTestPage>{
	
	@FindBy(locator="login.username.txt")
	private QAFWebElement username;
	
	@FindBy(locator="login.password.txt")
	private QAFWebElement password;
	
	public QAFWebElement getUsername() {
		return username;
	}

	public QAFWebElement getPassword() {
		return password;
	}

	public QAFWebElement getLoginbtn() {
		return loginbtn;
	}

	@FindBy(locator="login.loginbtn.btn")
	private QAFWebElement loginbtn;

	@Override
	protected void openPage(PageLocator arg0, Object... arg1) {
		// TODO Auto-generated method stub
		
	}

}
