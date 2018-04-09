package com.yulong.datafetch.response;

public class FinanceSoftInfo {
	private final String softWare = "企业财务数据中心（EFDC）";
	private String softVersion;
	private String startTime;
	private String endTime;
	private String createTime;

	public String getSoftVersion() {
		return softVersion;
	}

	public void setSoftVersion(String softVersion) {
		this.softVersion = softVersion;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getSoftWare() {
		return softWare;
	}
}
