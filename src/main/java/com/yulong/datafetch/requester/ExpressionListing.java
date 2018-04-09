package com.yulong.datafetch.requester;

import java.util.List;

public class ExpressionListing {
	private List<FixExpression> fixExpressions;
	private List<FloatExpression> floatExpressions;
	private boolean isFormatNeeded;

	public List<FixExpression> getFixExpressions() {
		return fixExpressions;
	}

	public void setFixExpressions(List<FixExpression> fixExpressions) {
		this.fixExpressions = fixExpressions;
	}

	public List<FloatExpression> getFloatExpressions() {
		return floatExpressions;
	}

	public void setFloatExpressions(List<FloatExpression> floatExpressions) {
		this.floatExpressions = floatExpressions;
	}

	public boolean isFormatNeeded() {
		return isFormatNeeded;
	}

	public void setFormatNeeded(boolean isFormatNeeded) {
		this.isFormatNeeded = isFormatNeeded;
	}

}
