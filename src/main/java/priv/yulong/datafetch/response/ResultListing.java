package priv.yulong.datafetch.response;

import java.util.List;

public class ResultListing {
	private String unitCode;
	private String orgCode;
	private boolean isSuccess;
	private String errMsg;
	private List<FixExpResult> fixExpResults;
	private List<FloatExpResult> floatExpResults;
	private boolean isFormatNeeded;

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public List<FixExpResult> getFixExpResults() {
		return fixExpResults;
	}

	public void setFixExpResults(List<FixExpResult> fixExpResults) {
		this.fixExpResults = fixExpResults;
	}

	public List<FloatExpResult> getFloatExpResults() {
		return floatExpResults;
	}

	public void setFloatExpResults(List<FloatExpResult> floatExpResults) {
		this.floatExpResults = floatExpResults;
	}

	public boolean isFormatNeeded() {
		return isFormatNeeded;
	}

	public void setFormatNeeded(boolean isFormatNeeded) {
		this.isFormatNeeded = isFormatNeeded;
	}
}
