package com.mrhi.bsm.model.item;

import java.util.Date;

public class TendencyAnalysisVO {
	private String minDate;
	private String maxDate;
	private String categorize;
	
	public String getMinDate() {
		return minDate;
	}
	
	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}
	
	public String getMaxDate() {
		return maxDate;
	}
	
	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}
	
	public String getCategorize() {
		return categorize;
	}
	
	public void setCategorize(String categorize) {
		this.categorize = categorize;
	}
	
}
