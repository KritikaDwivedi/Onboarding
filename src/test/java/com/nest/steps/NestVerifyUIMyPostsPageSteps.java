package com.nest.steps;

import static com.qmetry.qaf.automation.step.CommonStep.click;

import org.hamcrest.Matchers;

import com.nest.utilities.NavigateToPageUtility;
import com.nest.utilities.VerifyHeaderOfPageUtility;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Validator;

public class NestVerifyUIMyPostsPageSteps {
	
	NavigateToPageUtility navigate=new NavigateToPageUtility();
	VerifyHeaderOfPageUtility head=new VerifyHeaderOfPageUtility();
	public static String userView="My View";
	/**
	 * @param searchTerm
	 *            : search term to be searched
	 * @throws InterruptedException 
	 */
	@QAFTestStep(description = "user turns on {0}")
	public void ChangeView(String view) throws InterruptedException {
		// step implementation
		userView=view;
		click("home.view.btn.loc");
		Thread.sleep(20000);
	}
	
	@QAFTestStep(description = "navigates to {0} and {1}")
	public void navigationToPage(String menuOption, String subMenuOption) throws InterruptedException
	{	// step implementation
		navigate.navigationToMenuAndSubMenu(userView, menuOption, subMenuOption);
	}
	
	@QAFTestStep(description = "title of page should be {0}")
	public void verifyTitileOfSubMenuPage(String title) throws InterruptedException {
		// step implementation
		head.verifyHeaderOfPage(title);
	}
	
	@QAFTestStep(description = "navigation for the page should be {0}")
	public void verifyNavigationOfSubMenuPage(String title){
		// step implementation
		String navigationText1=CommonStep.getText("myPosts.navigation1.lnk.loc");
		String navigationText2=CommonStep.getText("myPosts.navigation2.lnk.loc");
		Validator.verifyThat(navigationText1+navigationText2, Matchers.containsString(title));
	}
	
	@QAFTestStep(description = "two date picker should be present with label From and To")
	public void verifyMyPostDateLabels(){
		// step implementation
		CommonStep.verifyPresent("myPosts.datefrom.txt.loc");
		CommonStep.verifyPresent("myPosts.dateto.txt.loc");
	}
	
	@QAFTestStep(description = "one text field should be present with label Keyword")
	public void verifyMyPostKeywordTextfield(){
		// step implementation
		CommonStep.verifyPresent("myPosts.keyword.txt.loc");
	}
	
	@QAFTestStep(description = "dropdown should be present for Category")
	public void verifyMyPostCategoryDropdown(){
		// step implementation
		CommonStep.verifyPresent("myPosts.category.dropdown.loc");
	}
	
	@QAFTestStep(description = "two Buttons should be present for Search and Reset")
	public void verifyMyPostSearchAndResetButtons(){
		// step implementation
		CommonStep.verifyPresent("myPosts.search.btn.loc");
		CommonStep.verifyPresent("myPosts.reset.btn.loc");
	}
	
	@QAFTestStep(description = "table should be present to show search results with columns Title, Category, Posted on, Action")
	public void verifyMyPostTableToDisplaySearchResult(){
		// step implementation
		CommonStep.verifyPresent("myPosts.searchResult.tbl.loc");
	}
		
	@QAFTestStep(description = "date should be in {0} format")
	public void verifyMyPostDateFormat(String format){
		// step implementation
		CommonStep.verifyAttribute("myPosts.datefrom.txt.loc", "uib-datepicker-popup", format);
	}
	
	@QAFTestStep(description = "hide filter icon should be on right upper side of page")
	public void verifyMyPostHideFilter(){
		// step implementation
		CommonStep.verifyPresent("myPosts.filter.btn.loc");
	}
	
	@QAFTestStep(description = "pagination should be present on right lower end of page")
	public void verifyMyPostPaginationButton(){
		// step implementation
		CommonStep.verifyPresent("myPosts.pagination.btn.loc");
	}
	
	@QAFTestStep(description = "action column should have edit and delete icons")
	public void verifyMyPostActionWithEditAndDeleteIcons(){
		// step implementation
		CommonStep.verifyPresent("myPosts.edit.btn.loc");
		CommonStep.verifyPresent("myPosts.delete.btn.loc");
	}
	
	@QAFTestStep(description = "New Post Button should be present on just below the table")
	public void verifyMyPostCreateButton(){
		// step implementation
		CommonStep.verifyPresent("myPosts.createPost.btn.loc");
	}
		
}
