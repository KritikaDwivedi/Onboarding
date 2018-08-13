package com.nest.beans;

import com.qmetry.qaf.automation.data.BaseFormDataBean;
import com.qmetry.qaf.automation.ui.annotations.UiElement;
import com.qmetry.qaf.automation.util.RandomStringGenerator.RandomizerTypes;
import com.qmetry.qaf.automation.util.Randomizer;

public class RecognitionBean extends BaseFormDataBean {

	@UiElement(fieldLoc = "brightSpark.facedSituations.txt.loc", order = 1)
	@Randomizer(dataset= {"Performed well","Faced all difficult situations efficiently"})
	private String situations;

	@UiElement(fieldLoc = "brightSpark.solutionsProvided.txt.loc", order = 2)
	@Randomizer(dataset= {"Provided effective solutions","Provided solutions for all technical problems"})
	private String solutions;

	@UiElement(fieldLoc = "brightSpark.benifitsAccrued.txt.loc", order = 3)
	@Randomizer(type=RandomizerTypes.DIGITS_ONLY)
	private String benifits;

	@UiElement(fieldLoc = "brightSpark.rationale.txt.loc", order = 4)
	@Randomizer
	private String rationale;

	public String getSituations() {
		return situations;
	}

	public void setSituations(String situations) {
		this.situations = situations;
	}

	public String getSolutions() {
		return solutions;
	}

	public void setSolutions(String solutions) {
		this.solutions = solutions;
	}

	public String getBenifits() {
		return benifits;
	}

	public void setBenifits(String benifits) {
		this.benifits = benifits;
	}

	public String getRationale() {
		return rationale;
	}

	public void setRationale(String rationale) {
		this.rationale = rationale;
	}

	@Override
	public String toString() {
		return "RecognitionBean [situations=" + situations + ", solutions=" + solutions + ", benifits=" + benifits
				+ ", rationale=" + rationale + "]";
	}



}