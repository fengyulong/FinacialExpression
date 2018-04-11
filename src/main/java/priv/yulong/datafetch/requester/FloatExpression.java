package priv.yulong.datafetch.requester;

import java.util.List;

public class FloatExpression {
	private String flag;
	private String name;
	private int precision;
	private String expression;
	private List<FixExpression> colExpressions;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public List<FixExpression> getColExpressions() {
		return colExpressions;
	}

	public void setColExpressions(List<FixExpression> colExpressions) {
		this.colExpressions = colExpressions;
	}

}
