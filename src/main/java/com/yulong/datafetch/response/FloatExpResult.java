package com.yulong.datafetch.response;

import java.util.List;

public class FloatExpResult {
	private String flag;
	private int rowCount;
	private List<FixExpResult> colResults;
	private boolean valid = true;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public List<FixExpResult> getColResults() {
		return colResults;
	}

	public void setColResults(List<FixExpResult> colResults) {
		this.colResults = colResults;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
