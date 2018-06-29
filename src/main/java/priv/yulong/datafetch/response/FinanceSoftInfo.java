package priv.yulong.datafetch.response;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class FinanceSoftInfo {
	private final String softWare = "企业财务取数接口";
	private String softVersion;
	@JSONField(format = "yyyy-M-d")
	private Date startTime;
	@JSONField(format = "yyyy-M-d")
	private Date endTime;
	@JSONField(format = "yyyy-M-d")
	private Date createTime;

	public String getSoftVersion() {
		return softVersion;
	}

	public void setSoftVersion(String softVersion) {
		this.softVersion = softVersion;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSoftWare() {
		return softWare;
	}
}
