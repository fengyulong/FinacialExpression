package priv.yulong.datafetch.requester;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class DataFetchEnv {
	private String recid;
	private String instance;
	private String unitCode;
	@JSONField(format = "yyyy-M-d") 
	private Date startTime;
	@JSONField(format = "yyyy-M-d") 
	private Date endTime;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
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
