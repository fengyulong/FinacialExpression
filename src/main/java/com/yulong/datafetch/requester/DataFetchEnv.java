package com.yulong.datafetch.requester;

public class DataFetchEnv {
	private String recid;
	private String instance;
	private String unitCode;
	private String startTime;
	private String endTime;
	private boolean isIncludeUncharged;
	private String unitName;
	private String periodScheme;

	public String getRecid() {
		return recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public boolean isIncludeUncharged() {
		return isIncludeUncharged;
	}

	public void setIncludeUncharged(boolean isIncludeUncharged) {
		this.isIncludeUncharged = isIncludeUncharged;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getPeriodScheme() {
		return periodScheme;
	}

	public void setPeriodScheme(String periodScheme) {
		this.periodScheme = periodScheme;
	}

}
