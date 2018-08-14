package com.nest.steps;

import static com.qmetry.qaf.automation.step.CommonStep.click;

import java.awt.AWTException;

import org.hamcrest.Matchers;

import com.nest.utilities.CommonUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Validator;

public class NestVerifyUIMyPostsPageSteps {
	
	/**
	 * @param searchTerm
	 *            : search term to be searched
	 * @throws InterruptedException 
	 */
	@QAFTestStep(description = "I turn on {0}")
	public static void changeView(String view) throws InterruptedException {
		// step implementation
		ConfigurationManager.getBundle().setProperty("userView",view);
		click("home.view.btn.loc");
		CommonUtils.waitForPageToLoad(35000);
	}
	
	@QAFTestStep(description = "I navigate to {0} and {1}")
	public static void navigationToPage(String menuOption, String subMenuOption) throws InterruptedException
	{	// step implementation
		CommonUtils.navigationToMenuAndSubMenu((String)ConfigurationManager.getBundle().getProperty("userView"), menuOption, subMenuOption);
		CommonUtils.waitForPageToLoad(30000);
	}
	
	@QAFTestStep(description = "I navigate to {0} and {1} and {2}")
	public static void navigationToSubPage(String menuOption, String subMenuOption, String subMenuOption2) throws InterruptedException, AWTException
	{	// step implementation
		CommonUtils.waitForPageToLoad(50000);
		CommonUtils.navigationToMenuAndSubMenu((String)ConfigurationManager.getBundle().getProperty("userView"), menuOption, subMenuOption);
		CommonUtils.navigationToSubMenuAndSubMenu2((String)ConfigurationManager.getBundle().getProperty("userView"), subMenuOption2);
		CommonUtils.waitForPageToLoad(5000);
	}
	
	@QAFTestStep(description = "title of page should be {0}")
	public static void verifyTitileOfSubMenuPage(String title) throws InterruptedException, AWTException {
		// step implementation
		CommonUtils.waitForPageToLoad(5000);
		CommonUtils.verifyTitleOfPage(title);
	}
	
	@QAFTestStep(description = "navigation for the page should be {0}")
	public static void verifyNavigationOfSubMenuPage(String title){
		// step implementation
		String navigationText1=CommonStep.getText("myPosts.navigation1.lnk.loc");
		String navigationText2=CommonStep.getText("myPosts.navigation2.lnk.loc");
		Validator.verifyThat(navigationText1+navigationText2, Matchers.containsString(title));
	}
	
	@QAFTestStep(description = "two date picker should be present with label from and to")
	public static void verifyMyPostDateLabels(){
		// step implementation
		CommonStep.verifyPresent("myPosts.datefrom.txt.loc");
		CommonStep.verifyPresent("myPosts.dateto.txt.loc");
	}
	
	@QAFTestStep(description = "one text field should be present with label keyword")
	public static void verifyMyPostKeywordTextfield(){
		// step implementation
		CommonStep.verifyPresent("myPosts.keyword.txt.loc");
	}
	
	@QAFTestStep(description = "dropdown should be present for category")
	public static void verifyMyPostCategoryDropdown(){
		// step implementation
		CommonStep.verifyPresent("myPosts.category.dropdown.loc");
	}
	
	@QAFTestStep(description = "two buttons should be present for search and reset")
	public static void verifyMyPostSearchAndResetButtons(){
		// step implementation
		CommonStep.verifyPresent("myPosts.search.btn.loc");
		CommonStep.verifyPresent("myPosts.reset.btn.loc");
	}
	
	@QAFTestStep(description = "table should be present to show search results with columns title, category, posted on, action")
	public static void verifyMyPostTableToDisplaySearchResult(){
		// step implementation
		CommonStep.verifyPresent("myPosts.searchResult.tbl.loc");
	}
		
	@QAFTestStep(description = "date should be in {0} format")
	public static void verifyMyPostDateFormat(String format){
		// step implementation
		CommonStep.verifyAttribute("myPosts.datefrom.txt.loc", "uib-datepicker-popup", format);
	}
	
	@QAFTestStep(description = "hide filter icon should be on right upper side of page")
	public static void verifyMyPostHideFilter(){
		// step implementation
		CommonStep.verifyPresent("myPosts.filter.btn.loc");
	}
	
	@QAFTestStep(description = "pagination should be present on right lower end of page")
	public static void verifyMyPostPaginationButton(){
		// step implementation
		CommonStep.verifyPresent("myPosts.pagination.btn.loc");
	}
	
	@QAFTestStep(description = "action column should have edit and delete icons")
	public static void verifyMyPostActionWithEditAndDeleteIcons(){
		// step implementation
		CommonStep.verifyPresent("myPosts.edit.btn.loc");
		CommonStep.verifyPresent("myPosts.delete.btn.loc");
	}
	
	@QAFTestStep(description = "new post button should be present on just below the table")
	public static void verifyMyPostCreateButton(){
		// step implementation
		CommonStep.verifyPresent("myPosts.createPost.btn.loc");
	}
		
}
