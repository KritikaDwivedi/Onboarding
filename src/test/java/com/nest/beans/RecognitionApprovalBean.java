package com.nest.beans;

import com.qmetry.qaf.automation.data.BaseFormDataBean;
import com.qmetry.qaf.automation.ui.annotations.UiElement;
import com.qmetry.qaf.automation.util.Randomizer;

public class RecognitionApprovalBean extends BaseFormDataBean {

	@UiElement(fieldLoc = "rnrRequest.comment.txt.loc", order = 1)
	@Randomizer
	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "RecognitionApprovalBean [comment=" + comment + "]";
	}

}